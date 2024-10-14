package com.lset.atom;

import com.lset.atom.common.ModItemGroups;
import com.lset.atom.common.blocks.ModBlocks;
import com.lset.atom.common.command.ModCommands;
import com.lset.atom.common.entity.LivingEntity.LivingPlayerGeo;
import com.lset.atom.common.entity.ModEntities;
import com.lset.atom.common.entity.customplayer.CustomPlayerAttributes;
import com.lset.atom.common.entity.tiger.TigerAttributes;
import com.lset.atom.common.items.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Atom implements ModInitializer {
	public static final String MOD_ID = "atom";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		//Регистрация контента
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		registerModEntities();
		registerModCommands();
	}
	public static void registerModEntities() {
		//Регистрация сущностей
		ModEntities.registerModEntities();
		FabricDefaultAttributeRegistry.register(ModEntities.TIGER, TigerAttributes.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.NOPLAYER, CustomPlayerAttributes.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.LIVINGPLAYER, LivingPlayerGeo.setAttributes());
	}
	public static void registerModCommands() {
		//Регистрация комманд
		CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> {
			ModCommands.register(dispatcher);
		}));
	}
}