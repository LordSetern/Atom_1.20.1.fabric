package com.lset.atom.common.entity.tiger;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class TigerModel extends GeoModel<TigerAttributes> {
    @Override
    public Identifier getModelResource(TigerAttributes animatable) {
        return new Identifier("atom", "geo/tiger.geo.json");
    }

    @Override
    public Identifier getTextureResource(TigerAttributes animatable) {
        return new Identifier("atom", "textures/entity/tiger.png");
    }

    @Override
    public Identifier getAnimationResource(TigerAttributes animatable) {
        return new Identifier("atom", "animations/tiger.animation.json");
    }

    @Override
    public void setCustomAnimations(TigerAttributes animatable, long instanceId, AnimationState<TigerAttributes> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}
