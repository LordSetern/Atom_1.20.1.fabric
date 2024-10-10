package com.lset.atom.common;

import com.lset.atom.Atom;
import com.lset.atom.common.blocks.ModBlocks;
import com.lset.atom.common.items.ModItems;
import com.lset.atom.common.types.*;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    //Регестрация креативных таблиц
    public static final ItemGroup ATOM_BLOCK_TAB = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Atom.MOD_ID, "atom_block_tab"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("atom_blocksgroup"))
                    .icon(() -> new ItemStack(ModBlocks.ORES.get(Ore.coal).get(OreBaze.stone)))
                    .entries((displayContext, entries) ->
                            {
                                entries.add(ModItems.TEST);
                                //Массовое добавление предметов с 2 регистрами
                                ModBlocks.ORES.forEach((key, map) -> map.forEach((subkey, ores) -> entries.add(ores)));
                                ModBlocks.CONSTRUCTIONE.forEach((key, map) -> map.forEach((subkey, ores) -> entries.add(ores)));
                            }
                    ).build());

    public static final ItemGroup ATOM_DECO_TAB = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Atom.MOD_ID, "atom_deco_tab"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("atom_decogroup"))
                    .icon(() -> new ItemStack(ModBlocks.POSTERS.get(BILDPosters.stalin)))
                    .entries((displayContext, entries) ->
                            {
                                entries.add(ModItems.TEST);
                                //Массовое добавление предметов с 1 регистром
                                ModBlocks.POSTERS.forEach((key, posters) -> entries.add(posters));
                            }
                    ).build());

    public static final ItemGroup ATOM_ITEMS_TAB = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Atom.MOD_ID,"atom_items_tab"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("atom_itemsgroup"))
                    .icon(() -> new ItemStack(ModItems.ITEMSZERO.get(IZERO.dedf)))
                    .entries((displayContext, entries) ->
                    {
                        entries.add(ModItems.TEST);
                        //Массовое добавление предметов с 1 регистром
                        ModItems.ITEMSZERO.forEach((key, zero) -> entries.add(zero));
                    }
                    ).build());

    public static final ItemGroup ATOM_MAGIC_TAB = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Atom.MOD_ID, "atom_magic_tab"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("atom_magicgroup"))
                    .icon(() -> new ItemStack(ModBlocks.BLOCK_STALIN))
                    .entries((displayContext, entries) ->
                    {
                        entries.add(ModItems.TEST);
                    }
                    ).build());

    //Конец регистрации
    public static void registerItemGroups(){
        Atom.LOGGER.info("Registering mod creative_tabs for " + Atom.MOD_ID);
    }
}
