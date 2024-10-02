package com.cherryblossom.atom.common;

import com.cherryblossom.atom.Atom;
import com.cherryblossom.atom.common.blocks.ModBlocks;
import com.cherryblossom.atom.common.items.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    //Регестрация креативных таблиц
    public static final ItemGroup ATOM_ITEMS_TAB = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Atom.MOD_ID,"atom_items_tab"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("atom_itemsgroup"))
                    .icon(() -> new ItemStack(ModBlocks.BLOCK_STALIN))
                    .entries((displayContext, entries) ->
                            //Одиночное добавление предметов
                            entries.add(ModItems.TEST)
                    ).build());

    public static final ItemGroup ATOM_BLOCKS_TAB = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Atom.MOD_ID, "atom_blocks_tab"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("atom_blocksgroup"))
                    .icon(() -> new ItemStack(ModBlocks.BLOCK_STALIN))
                    .entries((displayContext, entries) ->
                    {
                        //Массовое добавление предметов с 2 регистрами
                        ModBlocks.ORES.forEach((key, map) -> map.forEach((subkey, ores) -> entries.add(ores)));
                    }
                    ).build());

    public static final ItemGroup ATOM_DECO_TAB = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Atom.MOD_ID, "atom_deco_tab"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("atom_decogroup"))
                    .icon(() -> new ItemStack(ModBlocks.BLOCK_STALIN))
                    .entries((displayContext, entries) ->
                    {
                        //Массовое добавление предметов с 1 регистром
                        ModBlocks.POSTERS.forEach((key, posters) -> entries.add(posters));
                        ModBlocks.CONSTRUCTIONE.forEach((key, map) -> map.forEach((subkey, ores) -> entries.add(ores)));
                    }
                    ).build());

    //Конец регистрации
    public static void registerItemGroups(){
        Atom.LOGGER.info("Registering item groups for" + Atom.MOD_ID);
    }
}
