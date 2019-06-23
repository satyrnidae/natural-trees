package dev.satyrn.naturaltrees.client;

import com.ferreusveritas.dynamictrees.api.client.ModelHelper;
import com.ferreusveritas.dynamictrees.blocks.BlockDynamicLeaves;
import com.ferreusveritas.dynamictrees.blocks.LeavesPaging;
import com.ferreusveritas.dynamictrees.client.BlockColorMultipliers;

import com.ferreusveritas.dynamictrees.trees.TreeFamily;
import com.progwml6.natura.common.client.LeavesColorizer;
import com.progwml6.natura.overworld.block.leaves.BlockRedwoodLeaves;
import com.progwml6.natura.overworld.block.logs.BlockOverworldLog;
import com.progwml6.natura.overworld.block.logs.BlockOverworldLog2;
import dev.satyrn.naturaltrees.CommonProxy;
import dev.satyrn.naturaltrees.NaturalTrees;
import dev.satyrn.naturaltrees.NaturalTreesContent;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

  @Override
  public void registerModels() {
    super.registerModels();

    for(TreeFamily tree : NaturalTreesContent.naturalTrees) {
      ModelHelper.regModel(tree.getDynamicBranch());
      ModelHelper.regModel(tree.getCommonSpecies().getSeed());
      ModelHelper.regModel(tree);
    }
  }

  @Override
  public void init(FMLInitializationEvent event) {
    super.init(event);
    registerColorHandlers();
  }

  private void registerColorHandlers() {
    final BlockDynamicLeaves amaranth = (BlockDynamicLeaves)NaturalTreesContent.leaves.get("amaranth").getDynamicLeavesState().getBlock();
    ModelHelper.regColorHandler(amaranth, (state, worldIn, pos, tintIndex) -> LeavesColorizer.getOverworldLeavesColorForPos(worldIn, pos, BlockOverworldLog.LogType.AMARANTH));
  }

  @Override
  public void preInit(FMLPreInitializationEvent event) {
    super.preInit(event);
    registerJsonColorHandlers();
  }

  protected void registerJsonColorHandlers() {
    // Overworld Leaves 1
    BlockColorMultipliers.register(new ResourceLocation(NaturalTrees.MOD_ID, "maple"),
            (state, worldIn, pos, tintIndex) -> LeavesColorizer.getOverworldLeavesColorForPos(worldIn, pos, BlockOverworldLog.LogType.MAPLE));
    BlockColorMultipliers.register(new ResourceLocation(NaturalTrees.MOD_ID, "amaranth"),
            (state, worldIn, pos, tintIndex) -> LeavesColorizer.getOverworldLeavesColorForPos(worldIn, pos, BlockOverworldLog.LogType.AMARANTH));
    BlockColorMultipliers.register(new ResourceLocation(NaturalTrees.MOD_ID, "silverbell"),
            (state, worldIn, pos, tintIndex) -> LeavesColorizer.getOverworldLeavesColorForPos(worldIn, pos, BlockOverworldLog.LogType.SILVERBELL));
    BlockColorMultipliers.register(new ResourceLocation(NaturalTrees.MOD_ID, "tiger"),
            ((state, worldIn, pos, tintIndex) -> LeavesColorizer.getOverworldLeavesColorForPos(worldIn, pos, BlockOverworldLog.LogType.TIGER)));

    // Overworld Leaves 2
    BlockColorMultipliers.register(new ResourceLocation(NaturalTrees.MOD_ID, "willow"),
            (state, worldIn, pos, tintIndex) -> LeavesColorizer.getSecondOverworldLeavesColorForPos(worldIn, pos, BlockOverworldLog2.LogType.WILLOW));
    BlockColorMultipliers.register(new ResourceLocation(NaturalTrees.MOD_ID, "eucalyptus"),
            (state, worldIn, pos, tintIndex) -> LeavesColorizer.getSecondOverworldLeavesColorForPos(worldIn, pos, BlockOverworldLog2.LogType.EUCALYPTUS));
    BlockColorMultipliers.register(new ResourceLocation(NaturalTrees.MOD_ID, "hopseed"),
            (state, worldIn, pos, tintIndex) -> LeavesColorizer.getSecondOverworldLeavesColorForPos(worldIn, pos, BlockOverworldLog2.LogType.HOPSEED));
    BlockColorMultipliers.register(new ResourceLocation(NaturalTrees.MOD_ID, "sakura"),
            (state, worldIn, pos, tintIndex) -> LeavesColorizer.getSecondOverworldLeavesColorForPos(worldIn, pos, BlockOverworldLog2.LogType.SAKURA));

    // Redwood
    BlockColorMultipliers.register(new ResourceLocation(NaturalTrees.MOD_ID, "redwood"),
            (state, worldIn, pos, tintIndex) -> LeavesColorizer.getRedwoodLeavesColorForPos(worldIn, pos, BlockRedwoodLeaves.RedwoodType.NORMAL));

    // Nether
    BlockColorMultipliers.register(new ResourceLocation(NaturalTrees.MOD_ID, "nether"),
            (state, worldIn, pos, tintIndex) -> LeavesColorizer.leaves2Color);
  }
}
