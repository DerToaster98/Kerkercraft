package de.dertoaster.kerkercraft.world.item.armor;

import de.dertoaster.kerkercraft.client.renderer.geo.armor.KCGeoArmorRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

public class KCGeoArmorItem extends Item implements GeoItem {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public KCGeoArmorItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        // We dont need armor animations, unnecessary af
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private KCGeoArmorRenderer<? extends KCGeoArmorItem, ?> renderer;

            @Override
            public @Nullable <S extends HumanoidRenderState> GeoArmorRenderer<?, ?> getGeoArmorRenderer(@Nullable S renderState, ItemStack itemStack, EquipmentSlot equipmentSlot, EquipmentClientInfo.LayerType type, @Nullable HumanoidModel<S> original) {
                if (this.renderer == null) {
                    // TODO: Is this safe to do?
                    this.renderer = new KCGeoArmorRenderer<>(BuiltInRegistries.ITEM.getKey(itemStack.getItem()));
                }
                return this.renderer;
            }
        });
    }

}
