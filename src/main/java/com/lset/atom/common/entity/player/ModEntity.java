package com.lset.atom.common.entity.player;

import com.lset.atom.Atom;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntity {
    public static final EntityType<CustomEntityTest>NOPLAYER = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(Atom.MOD_ID,"player_test"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE,CustomEntityTest::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 1.8f))
                    .build()
    );
}
