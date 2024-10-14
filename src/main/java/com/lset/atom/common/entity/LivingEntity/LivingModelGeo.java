package com.lset.atom.common.entity.LivingEntity;

import com.lset.atom.Atom;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class LivingModelGeo extends GeoModel<LivingPlayerGeo> {

    @Override
    public Identifier getModelResource(LivingPlayerGeo animatable) {
        return new Identifier(Atom.MOD_ID, "geo/player_gecko.geo.json"); // Путь к вашей модели
    }

    @Override
    public Identifier getTextureResource(LivingPlayerGeo animatable) {
        return new Identifier(Atom.MOD_ID, "textures/entity/player_gecko.png"); // Путь к вашей текстуре
    }

    @Override
    public Identifier getAnimationResource(LivingPlayerGeo animatable) {
        return new Identifier(Atom.MOD_ID, "animations/player.move.animation.json"); // Путь к анимациям
    }

    @Override
    public void setCustomAnimations(LivingPlayerGeo animatable, long instanceId, AnimationState<LivingPlayerGeo> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("bonehead");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}