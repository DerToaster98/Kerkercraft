package de.dertoaster.kerkercraft;

import com.mojang.logging.LogUtils;
import de.dertoaster.kerkercraft.common.IMainMod;
import de.dertoaster.kerkercraft.common.ISubProjectMain;
import de.dertoaster.kerkercraft.common.KCConstants;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

import java.util.List;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(KCConstants.MODID)
public class Kerkercraft implements IMainMod
{
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "kerkercraft" namespace

    public static final boolean isWorkspaceEnvironment = !Kerkercraft.class.getResource("")
            .getProtocol()
            .equals("jar");

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Kerkercraft(IEventBus modEventBus, ModContainer modContainer)
    {
        List<ISubProjectMain> children = this.getSubModMains();
        children.forEach(child -> {
            child.onModConstruction(modEventBus);
            LOGGER.debug("Loaded child class " + child.getClass().getName());
        });
        children.forEach(child -> {
            child.registerEventHandlerObjects(NeoForge.EVENT_BUS::register);
            child.getEventListenersToReigster().forEach(listener -> {
                modEventBus.addListener(listener);
            });
        });

    }

    public static final ResourceLocation prefix(final String path) {
        return ResourceLocation.fromNamespaceAndPath(KCConstants.MODID, path);
    }

    public static final ResourceLocation prefixAttributeModifier(final String path) {
        return ResourceLocation.fromNamespaceAndPath(KCConstants.MODID, "attribute_modifier/" + path);
    }

	/*public static final ResourceLocation prefixStructureTemplateId(final String path) {
		return new ResourceLocation(MODID_STRUCTURES, path);
	}*/

    static final String ANIMATION_SUFFIX = ".animation.json";
    public static final ResourceLocation prefixAnimation(final String path) {
        String pathToUse = path;
        if(!path.endsWith(ANIMATION_SUFFIX)) {
            pathToUse = path + ANIMATION_SUFFIX;
        }
        return prefix("animations/" + pathToUse);
    }

    public static final ResourceLocation prefixEntityAnimation(final String path) {
        return prefixAnimation("entity/" + path);
    }

    public static final ResourceLocation prefixBlockAnimation(final String path) {
        return prefixAnimation("block/" + path);
    }

    public static ResourceLocation prefixArmorAnimation(final String path) {
        return prefixAnimation("armor/" + path);
    }

    public static ResourceLocation prefixAssesEnforcementManager(String string) {
        return prefix("asset_manager/" + string);
    }

    public static ResourceLocation prefixAssetFinder(String string) {
        return prefix("asset_finder/" + string);
    }

}
