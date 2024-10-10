package com.lset.atom.common.items;

import com.lset.atom.Atom;
import com.lset.atom.common.entity.ModEntities;
import com.lset.atom.common.types.IZERO;
import com.lset.atom.util.Helpers;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Map;

public class ModItems {

    //Регестрация добавленных предметов
    public static final Item TEST = registerItem("test", new Item(new FabricItemSettings()));
    public static final Map<IZERO, Item> ITEMSZERO = Helpers.mapOfKeys(IZERO.class, type ->
                    registerItem(("izero/" + type.name()),
                            new Item(new FabricItemSettings())));

    public static final Item PLAYER_SPAWN_EGG = registerItem("player_spawn_player",
            new SpawnEggItem(ModEntities.NOPLAYER,0x057E36,0x1D0D00,
                    new FabricItemSettings()));

    //Helpers
    //Шаблон регистрации
    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(Atom.MOD_ID, name), item);
    }
    //Конец регистрации
    public static void registerModItems(){
        Atom.LOGGER.info("Registering mod items for" + Atom.MOD_ID);
    }
}
