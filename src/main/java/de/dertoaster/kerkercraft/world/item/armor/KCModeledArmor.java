package de.dertoaster.kerkercraft.world.item.armor;

import net.minecraft.client.model.Model;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

// TODO: Can we do it like this?
public class KCModeledArmor extends Item implements IClientItemExtensions {

    private Model armorModel = null;

    public KCModeledArmor(Properties properties) {
        super(properties);
    }

    public void setArmorModel(Model model) {
        if (this.armorModel == null) {
            this.armorModel = model;
        } else {
            throw new IllegalStateException("Armor model is already bound!");
        }
    }

    @Override
    public Model getHumanoidArmorModel(ItemStack itemStack, EquipmentClientInfo.LayerType layerType, Model original) {
        Model mine = this.armorModel;
        if (mine != null) {
            return mine;
        }
        return null;
    }
}
