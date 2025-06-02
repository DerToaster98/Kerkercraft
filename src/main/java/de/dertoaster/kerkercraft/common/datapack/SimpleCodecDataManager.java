package de.dertoaster.kerkercraft.common.datapack;

import com.mojang.serialization.Codec;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.stream.Collectors;

public class SimpleCodecDataManager<T> extends SimpleJsonResourceReloadListener<T> {

    protected Map<ResourceLocation, T> data = Map.of();

    /**
     * Initialize a data manager with the given folder name, codec, and merger
     *
     * @param folderName The name of the folder to load data from,
     *                   e.g. "cheeses" would load data from "data/modid/cheeses" for all modids.
     *                   Can include subfolders, e.g. "cheeses/sharp"
     * @param codec      A codec that will be used to parse jsons. See drullkus's codec primer for help on creating these:
     *                   https://gist.github.com/Drullkus/1bca3f2d7f048b1fe03be97c28f87910
     */
    public SimpleCodecDataManager(String folderName, Codec<T> codec) {
        super(codec, FileToIdConverter.json(folderName));
    }

    @Nullable
    public T getData(final ResourceLocation id) {
        return this.data.getOrDefault(id, null);
    }

    @Override
    protected void apply(Map<ResourceLocation, T> loadedObjects, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        this.data = (Map)loadedObjects.entrySet().stream().collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
