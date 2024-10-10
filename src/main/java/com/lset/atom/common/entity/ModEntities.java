package com.lset.atom.common.entity;

import com.lset.atom.Atom;
import com.lset.atom.common.entity.tiger.TigerAttributes;
import com.lset.atom.common.entity.customplayer.CustomPlayerAttributes;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModEntities {

    public static final EntityType<TigerAttributes> TIGER = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Atom.MOD_ID, "tiger"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, TigerAttributes::new)
                    .dimensions(EntityDimensions.fixed(1.5f, 1.75f))
                    .build()
    );
    public static final EntityType<CustomPlayerAttributes> NOPLAYER = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Atom.MOD_ID,"player_test"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CustomPlayerAttributes::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 1.8f))
                    .build()
    );

    //Helpers
    //Конец регистрации
    public static void registerModEntities(){
        Atom.LOGGER.info("Registering mod entities for " + Atom.MOD_ID);
    }
}
