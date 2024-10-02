package com.cherryblossom.atom.common.blocks;

import com.cherryblossom.atom.Atom;
import com.cherryblossom.atom.common.types.*;
import com.cherryblossom.atom.util.Helpers;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LadderBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Map;

public class ModBlocks {

    public static final Block BLOCK_STALIN = registerBlock("block_stalin",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));

    //Массовая регистрация Постеров 1 регистор//
    public static final Map<Posters, Block> POSTERS =
            Helpers.mapOfKeys(Posters.class, type ->
                    registerBlock(("decorations/posters/" + type.name()),
                            new LadderBlock(FabricBlockSettings.copyOf(Blocks.LADDER)
                                    .noCollision())));

    //Массовая регистрация Руды 2 регистра//
    public static final Map<Rock, Map<Ore, Block>> ORES =
            Helpers.mapOfKeys(Rock.class, rock ->
                    Helpers.mapOfKeys(Ore.class, ore ->
                            registerBlock(("ore/" + ore.name() + "/" + rock.name()),
                                    new Block(FabricBlockSettings.copyOf(Blocks.STONE)))));

    //Регестрация Металлических блоков и их вариаций, Спиздил с TFC Очень круто
    public static final Map<Colors, Map<MetallFrame, Block>> CONSTRUCTIONE =
            Helpers.mapOfKeys(Colors.class, col ->
                    Helpers.mapOfKeys(MetallFrame.class, type ->
                            registerBlock(("metal/" + type.name() + "/" + col.name()),
                                    new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)
                                            .noCollision()))));


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
        Atom.LOGGER.info("Registering mod blocks for" + Atom.MOD_ID);
    }
}
