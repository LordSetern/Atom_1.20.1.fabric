package com.lset.atom.common.entity.customplayer;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class CustomPlayerModel extends GeoModel<CustomPlayerAttributes> {

    @Override
    public Identifier getModelResource(CustomPlayerAttributes animatable) {
        return new Identifier("atom", "geo/player_gecko.geo.json"); // Путь к вашей модели
    }

    @Override
    public Identifier getTextureResource(CustomPlayerAttributes animatable) {
        return new Identifier("atom", "textures/entity/player_gecko.png"); // Путь к вашей текстуре
    }

    @Override
    public Identifier getAnimationResource(CustomPlayerAttributes animatable) {
        return new Identifier("atom", "animations/player.move.animation.json"); // Путь к анимациям
    }

    @Override
    public void setCustomAnimations(CustomPlayerAttributes animatable, long instanceId, AnimationState<CustomPlayerAttributes> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("bonehead");

        if(head != null){
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch()* MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw()* MathHelper.RADIANS_PER_DEGREE);
        }

    }

    @Override
    public boolean crashIfBoneMissing() {
        return false; // Измените на true, если хотите краш при отсутствии костей
    }
}


