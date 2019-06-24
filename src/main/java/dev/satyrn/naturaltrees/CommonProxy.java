package dev.satyrn.naturaltrees;

import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.lang.reflect.Field;
import java.util.List;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		NaturalTreesContent.preInit(event);
	}

	public void init(FMLInitializationEvent event) {
		NaturalTreesContent.init(event);
	}

	protected void registerModels() {}
}
