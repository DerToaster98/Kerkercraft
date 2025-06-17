package de.dertoaster.kerkercraft.client.model.armor;

import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;

public class HeavyArmorModel<S extends HumanoidRenderState> extends HumanoidArmorModel<S> {

    public HeavyArmorModel(ModelPart p_270765_) {
        super(p_270765_);
    }

    public static MeshDefinition createBodyLayer(CubeDeformation cubeDeformation) {
        MeshDefinition meshdefinition = HumanoidArmorModel.createMesh(cubeDeformation, 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();

        // TODO: Add our additional cubes

        return meshdefinition;
    }

}
