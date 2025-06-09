package de.dertoaster.kerkercraft.world.item.weapon.melee;

import de.dertoaster.kerkercraft.init.KCAttributeModifiers;
import de.dertoaster.kerkercraft.world.entity.projectile.ThrownSpear;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;

public class SpearItem extends TridentItem {

    public SpearItem(Properties p_43381_) {
        super(p_43381_);
    }

    public static ItemAttributeModifiers createAttributes() {
        return createAttributes(8.0F, -2.9000000953674316F);
    }

    public static ItemAttributeModifiers createAttributes(ToolMaterial material, float attackDamage, float attackSpeed) {
        return createAttributes(attackDamage + material.attackDamageBonus(), attackSpeed);
    }

    public static ItemAttributeModifiers createAttributes(float attackDamage, float attackSpeed) {
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(BASE_ATTACK_DAMAGE_ID, 8.0, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ATTACK_SPEED,
                        new AttributeModifier(BASE_ATTACK_SPEED_ID, -2.9000000953674316, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ENTITY_INTERACTION_RANGE,
                        new AttributeModifier(KCAttributeModifiers.ATTACK_RANGE_MODIFIER, 1.5, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                        EquipmentSlotGroup.MAINHAND
                )
                .build();
    }

    @Override
    public Projectile asProjectile(Level p_338505_, Position p_338277_, ItemStack p_338353_, Direction p_338220_) {
        ThrownSpear thrownSpear = new ThrownSpear(p_338505_, p_338277_.x(), p_338277_.y(), p_338277_.z(), p_338353_.copyWithCount(1));
        thrownSpear.pickup = AbstractArrow.Pickup.ALLOWED;
        return thrownSpear;
    }
}
