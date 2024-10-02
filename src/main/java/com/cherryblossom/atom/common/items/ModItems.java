package com.cherryblossom.atom.common.items;

import com.cherryblossom.atom.Atom;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
public class ModItems {

    //Регестрация добавленных предметов
    public static final Item TEST = registerItem("test", new Item(new FabricItemSettings()));

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
