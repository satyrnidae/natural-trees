package dev.satyrn.naturaltrees;


import com.ferreusveritas.dynamictrees.api.WorldGenRegistry;
import com.ferreusveritas.dynamictrees.api.treedata.ILeavesProperties;
import com.ferreusveritas.dynamictrees.blocks.LeavesPaging;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.trees.TreeFamily;
import com.progwml6.natura.overworld.NaturaOverworld;
import com.progwml6.natura.overworld.block.logs.BlockOverworldLog;
import dev.satyrn.naturaltrees.trees.TreeAmaranth;
import dev.satyrn.naturaltrees.worldgen.NaturalTreesBiomeDataBasePopulator;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

@Mod.EventBusSubscriber(modid = NaturalTrees.MOD_ID)
public class NaturalTreesContent {

    public static ArrayList<TreeFamily> naturalTrees = new ArrayList<>();

    public static TreeAmaranth treeAmaranth;

    public static Map<String, ILeavesProperties> leaves;

    @GameRegistry.ObjectHolder("natura:overworld_logs")
    public static final Block NATURA_OVERWORLD_LOG = null;

    @GameRegistry.ObjectHolder("natura:overworld_logs2")
    public static final Block NATURA_OVERWORLD_LOG2 = null;

    @GameRegistry.ObjectHolder("natura:nether_logs")
    public static final Block NATURA_NETHER_LOG = null;



    public static void preInit(FMLPreInitializationEvent event) {
        leaves = LeavesPaging.build(new ResourceLocation(NaturalTrees.MOD_ID, "leaves/natura.json"));
    }

    @SubscribeEvent
    public static void registerDataBasePopulators(final WorldGenRegistry.BiomeDataBasePopulatorRegistryEvent event) {
        event.register(new NaturalTreesBiomeDataBasePopulator());
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        ArrayList<Item> items = new ArrayList<>();
        naturalTrees.forEach(tree -> tree.getRegisterableItems(items));

        registry.registerAll(items.toArray(new Item[0]));

        treeAmaranth.setPrimitiveLog(NaturaOverworld.overworldLog.getDefaultState().withProperty(BlockOverworldLog.TYPE, BlockOverworldLog.LogType.AMARANTH));
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerBlocks(final RegistryEvent.Register<Block> event) {

        treeAmaranth = new TreeAmaranth();

        Collections.addAll(naturalTrees, treeAmaranth);

        naturalTrees.forEach(tree -> tree.registerSpecies(Species.REGISTRY));


        final IForgeRegistry<Block> registry = event.getRegistry();

        ArrayList<Block> blocks = new ArrayList<>();
        naturalTrees.forEach(tree -> tree.getRegisterableBlocks(blocks));

        blocks.addAll(LeavesPaging.getLeavesMapForModId(NaturalTrees.MOD_ID).values());

        registry.registerAll(blocks.toArray(new Block[0]));
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        NaturalTrees.proxy.registerModels();
    }
}
