package de.dertoaster.kerkercraft.world.item.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class BackpackContent {

    public static final Codec<BackpackContent> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.list(ContentEntry.CODEC, 0, 54)
                            .fieldOf("content")
                            .forGetter(BackpackContent::getContent),
                    Codec.intRange(1, 54)
                            .fieldOf("size")
                            .forGetter(BackpackContent::getSize)
            ).apply(instance, BackpackContent::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, BackpackContent> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.fromCodec(
                    Codec.list(ContentEntry.CODEC, 0, 54)
            ),
            BackpackContent::getContent,
            ByteBufCodecs.INT,
            BackpackContent::getSize,
            BackpackContent::new
    );

    protected final int size;
    protected final NonNullList<ItemStack> content;

    public BackpackContent(int capacity) {
        this(NonNullList.<ItemStack>withSize(capacity, ItemStack.EMPTY), capacity);
    }

    public BackpackContent(NonNullList<ItemStack> content) {
        this(content, content.size());
    }

    public BackpackContent(NonNullList<ItemStack> content, int capacity) {
        this.size = capacity;
        this.content = content;
        if (this.content.size() > this.getSize()) {
            while (this.content.size() > capacity) {
                this.content.removeLast();
            }
        }
    }

    protected BackpackContent(List<ContentEntry> content, int capacity) {
        NonNullList<ItemStack> argList = NonNullList.withSize(capacity, ItemStack.EMPTY);
        for (ContentEntry entry : content) {
            if (entry.index() < argList.size()) {
                argList.set(entry.index(), entry.content());
            }
        }
        this.content = argList;
        this.size = capacity;
    }

    public int getSize() {
        return size;
    }

    public List<ContentEntry> getContent() {
        List<ContentEntry> list = new ArrayList<>(this.getSize());
        for(int i = 0; i < this.content.size() && i < this.getSize(); i++) {
            ItemStack itemstack = this.content.get(i);
            list.add(new ContentEntry(i, itemstack));
        }
        return list;
    }


    public record ContentEntry(int index, ItemStack content) {
        public static final Codec<ContentEntry> CODEC = RecordCodecBuilder.create(
                contentEntryInstance -> contentEntryInstance.group(
                        Codec.intRange(0, 54)
                                .fieldOf("slotIndex")
                                .forGetter(ContentEntry::index),
                        ItemStack.OPTIONAL_CODEC
                                .fieldOf("itemstack")
                                .forGetter(ContentEntry::content)
                ).apply(contentEntryInstance, ContentEntry::new)
        );
    }

}
