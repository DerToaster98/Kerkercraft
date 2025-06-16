package de.dertoaster.kerkercraft.common.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class ItemUtil {

    public static float getAttackDamageOf(ItemStack itemStack, DamageSources damageSources, LivingEntity user, Level level, Entity target) {
        return getAttackDamageOf(itemStack, damageSources, user, level, target, false, 1.0F);
    }

    public static float getAttackDamageOf(ItemStack itemStack, DamageSource damageSource, LivingEntity user, Level level, Entity target) {
        return getAttackDamageOf(itemStack, damageSource, user, level, target, false, 1.0F);
    }

    public static float getAttackDamageOf(ItemStack itemStack, DamageSources damageSources, LivingEntity user, Level level, Entity target, boolean isCriticalHit, float criticalMultiplier) {
        DamageSource damagesource;
        if (user instanceof Player player) {
            damagesource = (DamageSource) Optional.ofNullable(itemStack.getItem().getDamageSource(user)).orElse(damageSources.playerAttack(player));
        } else {
            damagesource = (DamageSource) Optional.ofNullable(itemStack.getItem().getDamageSource(user)).orElse(damageSources.mobAttack(user));
        }
        return getAttackDamageOf(itemStack, damagesource, user, level, target, isCriticalHit, criticalMultiplier);
    }

    public static float getAttackDamageOf(ItemStack itemStack, DamageSource damageSource, LivingEntity user, Level level, Entity target, boolean isCriticalHit, float criticalMultiplier) {
        float resultingDamage = 0F;

        float additionalEnchantmentDamage = 0.0F;
        if (level instanceof ServerLevel) {
            additionalEnchantmentDamage = EnchantmentHelper.modifyDamage((ServerLevel) level, itemStack, target, damageSource, resultingDamage) - resultingDamage;
        }
        // Represents percentual attack strength
        //float f2 = this.getAttackStrengthScale(0.5F);
        float attackStrengthScale = 1.0F;
        resultingDamage *= 0.2F + attackStrengthScale * attackStrengthScale * 0.8F;
        additionalEnchantmentDamage *= attackStrengthScale;

        if (resultingDamage > 0.0F || additionalEnchantmentDamage > 0.0F) {
            boolean flag3 = attackStrengthScale > 0.9F;

            resultingDamage += itemStack.getItem().getAttackDamageBonus(target, resultingDamage, damageSource);
            // Multiplier for jumping crit
            //boolean flag1 = flag3 && user.fallDistance > 0.0 && !user.onGround() && !user.onClimbable() && !user.isInWater() && !user.hasEffect(MobEffects.BLINDNESS) && !user.isPassenger() && target instanceof LivingEntity && !user.isSprinting();

            if (isCriticalHit) {
                resultingDamage *= criticalMultiplier;
            }

            resultingDamage += additionalEnchantmentDamage;
        }

        return resultingDamage;
    }

}
