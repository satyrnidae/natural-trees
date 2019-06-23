package dev.satyrn.naturaltrees;


import com.ferreusveritas.dynamictrees.api.TreeRegistry;
import com.ferreusveritas.dynamictrees.api.WorldGenRegistry;
import com.ferreusveritas.dynamictrees.api.treedata.ILeavesProperties;
import com.ferreusveritas.dynamictrees.blocks.BlockFruit;
import com.ferreusveritas.dynamictrees.blocks.LeavesPaging;
import com.ferreusveritas.dynamictrees.blocks.LeavesProperties;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.trees.TreeFamily;
import com.progwml6.natura.Natura;
import com.progwml6.natura.nether.NaturaNether;
import com.progwml6.natura.nether.block.logs.BlockNetherLog;
import com.progwml6.natura.overworld.NaturaOverworld;
import com.progwml6.natura.overworld.block.logs.BlockOverworldLog;
import com.progwml6.natura.shared.NaturaCommons;
import dev.satyrn.naturaltrees.blocks.BlockRootyTaintedSoil;
import dev.satyrn.naturaltrees.trees.TreeAmaranth;
import dev.satyrn.naturaltrees.trees.TreeDarkwood;
import dev.satyrn.naturaltrees.worldgen.NaturalTreesBiomeDataBasePopulator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = NaturalTrees.MOD_ID)
public class NaturalTreesContent {

    public static ArrayList<TreeFamily> naturalTrees = new ArrayList<>();

    public static TreeAmaranth treeAmaranth;

    public static TreeDarkwood treeDarkwood;

    public static Map<String, ILeavesProperties> leaves;

    public static BlockFruit blockChalkyApple;
    public static BlockRootyTaintedSoil blockRootyTaintedSoil;

    public static void preInit(FMLPreInitializationEvent event) {
        leaves = LeavesPaging.build(new ResourceLocation(NaturalTrees.MOD_ID, "leaves/natura.json"));
        leaves.put("saguaro", new LeavesProperties(null, ItemStack.EMPTY, TreeRegistry.findCellKit("bare")));

        blockChalkyApple = new BlockFruit(NaturalTrees.MOD_ID + ":chalkyAppleFruit");
        blockRootyTaintedSoil = new BlockRootyTaintedSoil(false);
    }

    public static void init(FMLInitializationEvent event) {
        ArrayList<Block> darkwoodBlocks = new ArrayList<>();
        treeDarkwood.getRegisterableBlocks(darkwoodBlocks);

        darkwoodBlocks.forEach(block -> {
            Blocks.FIRE.setFireInfo(block, 0, 0);
            System.out.println(block.getFlammability(null, null, null));
        });


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
        treeAmaranth.setStick(NaturaCommons.amaranth_stick);
        treeDarkwood.setPrimitiveLog(NaturaNether.netherLog.getDefaultState().withProperty(BlockNetherLog.TYPE, BlockNetherLog.LogType.DARKWOOD));
        treeDarkwood.setStick(NaturaCommons.darkwood_stick);
        blockChalkyApple.setDroppedItem(new ItemStack(NaturaCommons.edibles, 1, 10)); // chalky apple
        Objects.requireNonNull(Species.REGISTRY.getValue(new ResourceLocation(NaturalTrees.MOD_ID, "darkwood"))).addAcceptableSoil(NaturaNether.netherTaintedSoil);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerBlocks(final RegistryEvent.Register<Block> event) {

        treeAmaranth = new TreeAmaranth();
        treeDarkwood = new TreeDarkwood();

        Collections.addAll(naturalTrees, treeAmaranth, treeDarkwood);

        naturalTrees.forEach(tree -> tree.registerSpecies(Species.REGISTRY));

        final IForgeRegistry<Block> registry = event.getRegistry();

        ArrayList<Block> blocks = new ArrayList<>();
        naturalTrees.forEach(tree -> tree.getRegisterableBlocks(blocks));

        blocks.addAll(LeavesPaging.getLeavesMapForModId(NaturalTrees.MOD_ID).values());

        registry.registerAll(blocks.toArray(new Block[0]));
        registry.registerAll(
                blockChalkyApple,
                blockRootyTaintedSoil
        );
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        NaturalTrees.proxy.registerModels();
    }
}
