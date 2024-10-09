package com.lset.atom.common.entity.player;

import com.lset.atom.Atom;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class PlayerGeoRenderer extends GeoEntityRenderer<CustomEntityTest> {

    public PlayerGeoRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new CustomPlayerGeoModel());  // Устанавливаем кастомную модель
    }

    @Override
    public Identifier getTextureLocation(CustomEntityTest animatable) {
        return new Identifier(Atom.MOD_ID,"textures/entity/player_gecko.png");
    }

    @Override
    public void render(
            CustomEntityTest entity, float entityYaw, float partialTick
                ,MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
        if(entity.isBaby()){
            poseStack.scale(0.4f,0.4f,0.4f);
        }


        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
