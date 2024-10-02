package com.cherryblossom.atom.client;

import com.cherryblossom.atom.common.blocks.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class AtomClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Инициализация рендеров при старте клиента
        clientSetup();
    }

    public static void clientSetup() {

        // Прозрачность руд
        ModBlocks.ORES.values().forEach(map -> map.values().forEach(block ->
                BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout())
        ));
        // Прозрачность CONSTRUCTIONE
        ModBlocks.CONSTRUCTIONE.values().forEach(map -> map.values().forEach(block ->
                BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout())
        ));
        // Прозрачность постеров
        ModBlocks.POSTERS.values().forEach(block ->
                BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout())
        );
    }
}
