package de.dertoaster.kerkercraft.common.datapack;

import com.mojang.serialization.Codec;
import de.dertoaster.kerkercraft.common.registration.RegistrationIDSupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.Map;

public class IDSupplierCodecDataManager<T extends Object & RegistrationIDSupplier> extends SimpleCodecDataManager<T> {

    protected Codec<T> byNameCodec = this.createByNameCodec();

    /**
     * Initialize a data manager with the given folder name, codec, and merger
     *
     * @param folderName The name of the folder to load data from,
     *                   e.g. "cheeses" would load data from "data/modid/cheeses" for all modids.
     *                   Can include subfolders, e.g. "cheeses/sharp"
     * @param codec      A codec that will be used to parse jsons. See drullkus's codec primer for help on creating these:
     *                   https://gist.github.com/Drullkus/1bca3f2d7f048b1fe03be97c28f87910
     */
    public IDSupplierCodecDataManager(String folderName, Codec<T> codec) {
        super(folderName, codec);
    }

    protected Codec<T> createByNameCodec() {
        return DatapackLoaderHelper.byNameCodec(this.data::get);
    }

    public Codec<T> byNameCodec() {
        return this.byNameCodec ;
    }

    @Override
    protected void apply(Map<ResourceLocation, T> processedData, ResourceManager resourceManager, ProfilerFiller profiler) {
        super.apply(processedData, resourceManager, profiler);

        this.data.entrySet().forEach(entry -> {
            if (entry.getValue() instanceof RegistrationIDSupplier ris) {
                ris.setId(entry.getKey());
            }
        });
    }

}
