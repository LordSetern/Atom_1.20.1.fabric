package com.lset.atom.common.entity.LivingEntity;

import com.lset.atom.Atom;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class LivingRenderGeo extends GeoEntityRenderer<LivingPlayerGeo> {

    public LivingRenderGeo(EntityRendererFactory.Context context){
        super(context,new LivingModelGeo());
    }

    @Override
    public Identifier getTexture(LivingPlayerGeo animatable) {
        return new Identifier(Atom.MOD_ID, "textures/entity/player_gecko.png");
    }
}
