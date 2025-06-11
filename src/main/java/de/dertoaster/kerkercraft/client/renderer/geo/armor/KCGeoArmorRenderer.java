package de.dertoaster.kerkercraft.client.renderer.geo.armor;

import de.dertoaster.kerkercraft.client.model.geo.armor.KCDefaultedArmorGeoModel;
import de.dertoaster.kerkercraft.world.item.armor.KCGeoArmorItem;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class KCGeoArmorRenderer<T extends KCGeoArmorItem, R extends HumanoidRenderState & GeoRenderState> extends GeoArmorRenderer<T, R> {

    // TODO: Add armor trim support?
    public KCGeoArmorRenderer(final ResourceLocation modelID) {
        super(new KCDefaultedArmorGeoModel<>(modelID));
    }

}
