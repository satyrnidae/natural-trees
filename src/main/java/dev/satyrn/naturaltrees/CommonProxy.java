package dev.satyrn.naturaltrees;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		NaturalTreesContent.preInit(event);
	}

	public void init(FMLInitializationEvent event) {
	}

	protected void registerModels() {}
}
