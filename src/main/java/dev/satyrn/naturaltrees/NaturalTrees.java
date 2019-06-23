package dev.satyrn.naturaltrees;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "naturaltrees", version = "@MODVERSION@", dependencies="after:dynamictrees;after:mantle;after:natura")
public class NaturalTrees {

    public static final String MOD_ID = "naturaltrees";

    @Mod.Instance("naturaltrees")
    public static NaturalTrees instance;

    @SidedProxy(modId = "naturaltrees", clientSide = "dev.satyrn.naturaltrees.client.ClientProxy", serverSide = "dev.satyrn.naturaltrees.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }
}
