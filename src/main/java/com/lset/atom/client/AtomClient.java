package com.lset.atom.client;

import com.lset.atom.common.blocks.ModBlocks;
import com.lset.atom.common.entity.LivingEntity.LivingRenderGeo;
import com.lset.atom.common.entity.ModEntities;
import com.lset.atom.common.entity.tiger.TigerRenderer;
import com.lset.atom.common.entity.customplayer.CustomPlayerRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;


public class AtomClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Инициализация рендеров при старте клиента
        clientRenderBlock();
        playerModelInitialize();

    }

    public static void clientRenderBlock() {

        // Прозрачность руд
        ModBlocks.ORES.values().forEach(map -> map.values().forEach(block ->
                BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout())
        ));
        // Прозрачность CONSTRUCTION
        ModBlocks.CONSTRUCTIONE.values().forEach(map -> map.values().forEach(block ->
                BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout())
        ));
        // Прозрачность постеров
        ModBlocks.POSTERS.values().forEach(block ->
                BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout())
        );
    }
    public static void playerModelInitialize() {

        EntityRendererRegistry.register(ModEntities.TIGER, TigerRenderer::new);
        EntityRendererRegistry.register(ModEntities.NOPLAYER, CustomPlayerRenderer::new);
        EntityRendererRegistry.register(ModEntities.LIVINGPLAYER, LivingRenderGeo::new);
    }
}
