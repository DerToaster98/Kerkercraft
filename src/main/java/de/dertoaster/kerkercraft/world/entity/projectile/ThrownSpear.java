package de.dertoaster.kerkercraft.world.entity.projectile;

import de.dertoaster.kerkercraft.init.KCEntityTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

// Was meant to extend ThrownTrident but that will cause issues with the constructors of the super class of ThrownTrident...
// Maybe change it later?
public class ThrownSpear extends AbstractArrow {

    private static final EntityDataAccessor<Byte> ID_LOYALTY;
    private static final EntityDataAccessor<Boolean> ID_FOIL;
    private static final float WATER_INERTIA = 0.99F;
    private static final boolean DEFAULT_DEALT_DAMAGE = false;
    public boolean dealtDamage = false;
    public int clientSideReturnTridentTickCount;

    public ThrownSpear(EntityType<? extends ThrownSpear> p_37561_, Level p_37562_) {
        super(p_37561_, p_37562_);
    }

    public ThrownSpear(Level level, LivingEntity shooter, ItemStack pickupItemStack) {
        super(KCEntityTypes.THROWN_SPEAR.get(), shooter, level, pickupItemStack, (ItemStack)null);
        this.entityData.set(ID_LOYALTY, this.getLoyaltyFromItem(pickupItemStack));
        this.entityData.set(ID_FOIL, pickupItemStack.hasFoil());
    }

    public ThrownSpear(Level level, double x, double y, double z, ItemStack pickupItemStack) {
        super(KCEntityTypes.THROWN_SPEAR.get(), x, y, z, level, pickupItemStack, pickupItemStack);
        this.entityData.set(ID_LOYALTY, this.getLoyaltyFromItem(pickupItemStack));
        this.entityData.set(ID_FOIL, pickupItemStack.hasFoil());
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        Entity entity = result.getEntity();

        // TODO: Replace with actual damage from spear!
        float baseDamage = 2.0F;

        Entity effectiveOwner = this.getOwner();
        effectiveOwner = effectiveOwner == null ? this : effectiveOwner;
        DamageSource damagesource = this.damageSources().trident(this, effectiveOwner);
        Level var7 = this.level();
        if (var7 instanceof ServerLevel serverlevel1) {
            baseDamage = EnchantmentHelper.modifyDamage(serverlevel1, this.getWeaponItem(), entity, damagesource, baseDamage);
        }

        this.dealtDamage = true;
        if (entity.hurtOrSimulate(damagesource, baseDamage)) {
            if (entity.getType() == EntityType.ENDERMAN) {
                return;
            }

            var7 = this.level();
            if (var7 instanceof ServerLevel serverlevel1) {
                EnchantmentHelper.doPostAttackEffectsWithItemSourceOnBreak(serverlevel1, entity, damagesource, this.getWeaponItem(), (p_375964_) -> {
                    this.kill(serverlevel1);
                });
            }

            if (entity instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity)entity;
                this.doKnockback(livingentity, damagesource);
                this.doPostHurtEffects(livingentity);
            }
        }

        this.deflect(ProjectileDeflection.REVERSE, entity, this.getOwner(), false);
        this.setDeltaMovement(this.getDeltaMovement().multiply(0.02, 0.2, 0.02));
        this.playSound(SoundEvents.TRIDENT_HIT, 1.0F, 1.0F);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.dealtDamage = compound.getBooleanOr("DealtDamage", false);
        this.entityData.set(ID_LOYALTY, this.getLoyaltyFromItem(this.getPickupItemStackOrigin()));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("DealtDamage", this.dealtDamage);
    }


    protected void defineSynchedData(SynchedEntityData.Builder p_326249_) {
        super.defineSynchedData(p_326249_);
        p_326249_.define(ID_LOYALTY, (byte)0);
        p_326249_.define(ID_FOIL, false);
    }

    public void tick() {
        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
        }

        Entity entity = this.getOwner();
        int i = (Byte)this.entityData.get(ID_LOYALTY);
        if (i > 0 && (this.dealtDamage || this.isNoPhysics()) && entity != null) {
            if (!this.isAcceptibleReturnOwner()) {
                Level var4 = this.level();
                if (var4 instanceof ServerLevel) {
                    ServerLevel serverlevel = (ServerLevel)var4;
                    if (this.pickup == Pickup.ALLOWED) {
                        this.spawnAtLocation(serverlevel, this.getPickupItem(), 0.1F);
                    }
                }

                this.discard();
            } else {
                if (!(entity instanceof Player) && this.position().distanceTo(entity.getEyePosition()) < (double)entity.getBbWidth() + 1.0) {
                    this.discard();
                    return;
                }

                this.setNoPhysics(true);
                Vec3 vec3 = entity.getEyePosition().subtract(this.position());
                this.setPosRaw(this.getX(), this.getY() + vec3.y * 0.015 * (double)i, this.getZ());
                double d0 = 0.05 * (double)i;
                this.setDeltaMovement(this.getDeltaMovement().scale(0.95).add(vec3.normalize().scale(d0)));
                if (this.clientSideReturnTridentTickCount == 0) {
                    this.playSound(SoundEvents.TRIDENT_RETURN, 10.0F, 1.0F);
                }

                ++this.clientSideReturnTridentTickCount;
            }
        }

        super.tick();
    }

    private boolean isAcceptibleReturnOwner() {
        Entity entity = this.getOwner();
        return entity != null && entity.isAlive() ? !(entity instanceof ServerPlayer) || !entity.isSpectator() : false;
    }

    public boolean isFoil() {
        return (Boolean)this.entityData.get(ID_FOIL);
    }

    @Nullable
    protected EntityHitResult findHitEntity(Vec3 startVec, Vec3 endVec) {
        return this.dealtDamage ? null : super.findHitEntity(startVec, endVec);
    }

    protected void hitBlockEnchantmentEffects(ServerLevel p_344953_, BlockHitResult p_346320_, ItemStack p_344999_) {
        Vec3 vec3 = p_346320_.getBlockPos().clampLocationWithin(p_346320_.getLocation());
        Entity var6 = this.getOwner();
        LivingEntity var10002;
        if (var6 instanceof LivingEntity livingentity) {
            var10002 = livingentity;
        } else {
            var10002 = null;
        }

        EnchantmentHelper.onHitBlock(p_344953_, p_344999_, var10002, this, (EquipmentSlot)null, vec3, p_344953_.getBlockState(p_346320_.getBlockPos()), (p_375966_) -> {
            this.kill(p_344953_);
        });
    }

    public ItemStack getWeaponItem() {
        return this.getPickupItemStackOrigin();
    }

    protected boolean tryPickup(Player p_150196_) {
        return super.tryPickup(p_150196_) || this.isNoPhysics() && this.ownedBy(p_150196_) && p_150196_.getInventory().add(this.getPickupItem());
    }

    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(Items.TRIDENT);
    }

    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.TRIDENT_HIT_GROUND;
    }

    public void playerTouch(Player entity) {
        if (this.ownedBy(entity) || this.getOwner() == null) {
            super.playerTouch(entity);
        }

    }

    private byte getLoyaltyFromItem(ItemStack stack) {
        Level var3 = this.level();
        byte var10000;
        if (var3 instanceof ServerLevel serverlevel) {
            var10000 = (byte) Mth.clamp(EnchantmentHelper.getTridentReturnToOwnerAcceleration(serverlevel, stack, this), 0, 127);
        } else {
            var10000 = 0;
        }

        return var10000;
    }

    public void tickDespawn() {
        int i = (Byte)this.entityData.get(ID_LOYALTY);
        if (this.pickup != Pickup.ALLOWED || i <= 0) {
            super.tickDespawn();
        }

    }

    protected float getWaterInertia() {
        return 0.99F;
    }

    public boolean shouldRender(double x, double y, double z) {
        return true;
    }

    static {
        ID_LOYALTY = SynchedEntityData.defineId(ThrownTrident.class, EntityDataSerializers.BYTE);
        ID_FOIL = SynchedEntityData.defineId(ThrownTrident.class, EntityDataSerializers.BOOLEAN);
    }
}
