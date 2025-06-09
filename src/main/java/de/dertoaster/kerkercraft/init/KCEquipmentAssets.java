package de.dertoaster.kerkercraft.init;

import de.dertoaster.kerkercraft.Kerkercraft;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

public interface KCEquipmentAssets extends EquipmentAssets {

    ResourceKey<? extends Registry<EquipmentAsset>> REGISTRY_ID = ResourceKey.createRegistryKey(Kerkercraft.prefix("equipment_asset"));

    static ResourceKey<EquipmentAsset> register(String id) {
        return ResourceKey.create(REGISTRY_ID, Kerkercraft.prefix("id"));
    }

    // TODO: Can we do it like this???


    ResourceKey<EquipmentAsset> TORTOISE = register("tortoise");
    ResourceKey<EquipmentAsset> BULL = register("bull");
    ResourceKey<EquipmentAsset> SLIME = register("slime");
    ResourceKey<EquipmentAsset> HEAVY_IRON = register("heavy_iron");
    ResourceKey<EquipmentAsset> HEAVY_DIAMOND = register("heavy_diamond");
    ResourceKey<EquipmentAsset> HEAVY_NETHERITE = register("heavy_netherite");
    ResourceKey<EquipmentAsset> KING = register("king_armor");
    ResourceKey<EquipmentAsset> DYABLE_GOLD = register("dyable_gold");
    ResourceKey<EquipmentAsset> DYABLE_IRON = register("dyable_iron");
    ResourceKey<EquipmentAsset> DYABLE_DIAMOND = register("dyable_diamond");
    ResourceKey<EquipmentAsset> DYABLE_NETHERITE = register("dyable_netherite");
    ResourceKey<EquipmentAsset> CLOTH = register("cloth");

}
