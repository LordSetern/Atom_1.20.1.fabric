package com.lset.atom.common.blocks;

import com.lset.atom.Atom;
import com.lset.atom.common.types.*;
import com.lset.atom.util.Helpers;
import com.mojang.authlib.GameProfile;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LadderBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoEntity;

import java.util.Map;

public class ModBlocks {

    public static final Block BLOCK_STALIN = registerBlock("block_stalin",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));

    //Массовая регистрация Постеров 1 регистор
    public static final Map<BILDPosters, Block> POSTERS =
            Helpers.mapOfKeys(BILDPosters.class, type ->
                    registerBlock(("decorations/posters/" + type.name()),
                            new LadderBlock(FabricBlockSettings.copyOf(Blocks.LADDER)
                                    .noCollision())));

    //Массовая регистрация Минералов и их базы 2 регистра
    public static final Map<Ore, Map<OreBaze, Block>> ORES =
            Helpers.mapOfKeys(Ore.class, type ->
                    Helpers.mapOfKeys(OreBaze.class, oreBaze ->
                            registerBlock(("ore/" + type.name() + "/" + oreBaze.name()),
                                    new Block(FabricBlockSettings.copyOf(Blocks.STONE)))));

    //Регестрация Металлических блоков и их вариаций 2 регистра
    public static final Map<BILDMetallType, Map<BILDMetallMaterial, Block>> CONSTRUCTIONE =
            Helpers.mapOfKeys(BILDMetallType.class, type ->
                    Helpers.mapOfKeys(BILDMetallMaterial.class, material ->
                            registerBlock(("metal/" + type.name() + "/" + material.name()),
                                    new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)
                                            .nonOpaque()))));

    //Helpers
    //Модель регистрации переменные->блок//
    public static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Atom.MOD_ID, name), block);
    }
    //Модель регистрации блок->предмет//
    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, new Identifier(Atom.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }
    //Конец регистрации
    public static void registerModBlocks(){
        Atom.LOGGER.info("Registering mod blocks for " + Atom.MOD_ID);
    }
}