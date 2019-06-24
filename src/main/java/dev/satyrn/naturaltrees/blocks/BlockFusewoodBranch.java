package dev.satyrn.naturaltrees.blocks;

import com.ferreusveritas.dynamictrees.blocks.BlockBranchThick;
import dev.satyrn.naturaltrees.NaturalTrees;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockFusewoodBranch extends BlockBranchThick {
    private static String name = NaturalTrees.MOD_ID + ":fusewoodbranch";

    public BlockFusewoodBranch() {
        super(name);

        otherBlock = new BlockFusewoodBranch(this.getDefaultState().getMaterial(), name + "x", true);
        otherBlock.otherBlock = this;

        this.setFlammability(0);
        this.setFireSpreadSpeed(0);
    }

    private BlockFusewoodBranch(Material material, String s, boolean b) {
        super(material, s, b);

        this.setFlammability(0);
        this.setFireSpreadSpeed(0);
    }

    @Override
    public void futureBreak(IBlockState state, World world, BlockPos cutPos, EntityLivingBase entity) {
        super.futureBreak(state, world, cutPos, entity);
        spawnExplosion(world, cutPos);
    }

    private void spawnExplosion(World world, BlockPos pos) {
        switch(world.getDifficulty()) {
            case HARD:
                world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 2.0f, false);
                break;
            case NORMAL:
            case EASY:
                world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 1.75f, false);
                break;
            case PEACEFUL:
                break;
        }
    }
}
