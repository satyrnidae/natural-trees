package dev.satyrn.naturaltrees.blocks;

import com.ferreusveritas.dynamictrees.blocks.BlockRooty;
import com.progwml6.natura.nether.NaturaNether;
import dev.satyrn.naturaltrees.NaturalTrees;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import java.util.Random;

public class BlockRootyTaintedSoil extends BlockRooty {
    static String name = NaturalTrees.MOD_ID + ":rootytaintedsoil";

    public BlockRootyTaintedSoil(boolean isTileEntity) {
        this(name + (isTileEntity ? "species" : ""), isTileEntity);
    }

    public BlockRootyTaintedSoil(String name, boolean isTileEntity) {
        super(name, Material.GROUND, isTileEntity);
        setSoundType(SoundType.GROUND);
    }

    @Override
    public IBlockState getMimic(IBlockAccess access, BlockPos pos) {
        return NaturaNether.netherTaintedSoil.getDefaultState();
    }

    @Override
    public IBlockState getDecayBlockState(IBlockAccess access, BlockPos pos) {
        return NaturaNether.netherTaintedSoil.getDefaultState();
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(NaturaNether.netherTaintedSoil);
    }

    @Override
    public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
        return layer == BlockRenderLayer.CUTOUT_MIPPED || layer == BlockRenderLayer.SOLID;
    }
}
