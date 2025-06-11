package de.dertoaster.kerkercraft.client.model.geo.armor;

import de.dertoaster.kerkercraft.world.item.armor.KCGeoArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedGeoModel;

public class KCDefaultedArmorGeoModel<T extends KCGeoArmorItem> extends DefaultedGeoModel<T> {
    /**
     * Create a new instance of this model class
     * <p>
     * The asset path should be the truncated relative path from the base folder
     * <p>
     * E.G.
     * <pre>{@code new ResourceLocation("myMod", "animals/red_fish")}</pre>
     *
     * @param assetSubpath
     */
    public KCDefaultedArmorGeoModel(ResourceLocation assetSubpath) {
        super(assetSubpath);
    }

    @Override
    protected String subtype() {
        return "armor";
    }

}
