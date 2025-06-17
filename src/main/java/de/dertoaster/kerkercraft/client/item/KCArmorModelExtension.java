package de.dertoaster.kerkercraft.client.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Function;

@OnlyIn(Dist.CLIENT)
public class KCArmorModelExtension implements IClientItemExtensions {

    // TODO: Use LazyLoading object
    // TODO: Different suppliers for inner and outer model
    // TODO: support for different layertypes

    private Model armorModel;
    private final ModelLayerLocation modelLayerLocation;
    private final Function<ModelPart, Model> modelConstructor;

    public KCArmorModelExtension(ModelLayerLocation model, Function<ModelPart, Model> modelConstructor) {
        this.modelLayerLocation = model;
        this.modelConstructor = modelConstructor;
    }

    @Override
    public Model getHumanoidArmorModel(ItemStack itemStack, EquipmentClientInfo.LayerType layerType, Model original) {
        // TODO: Add support for different layer types!
        if (this.armorModel == null) {
            this.armorModel = this.modelConstructor.apply(Minecraft.getInstance().getEntityModels().bakeLayer(this.modelLayerLocation));
        }
        return this.armorModel;
    }
}
