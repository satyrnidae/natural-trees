package dev.satyrn.naturaltrees.trees;

import com.ferreusveritas.dynamictrees.blocks.BlockBranch;
import com.ferreusveritas.dynamictrees.blocks.BlockBranchBasic;
import com.ferreusveritas.dynamictrees.blocks.BlockBranchThick;
import com.ferreusveritas.dynamictrees.trees.TreeFamily;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public abstract class NaturalTreeFamily extends TreeFamily {

    NaturalTreeFamily(ResourceLocation name) {
        super(name);
    }

    @Override
    public TreeFamily setPrimitiveLog(IBlockState block) {
        return super.setPrimitiveLog(block);
    }

    @Override
    public TreeFamily setStick(ItemStack stick) { return super.setStick(stick); }

    @Override
    public BlockBranch createBranch() {
        BlockBranchBasic branch = (BlockBranchBasic)super.createBranch();
        branch = branch.setFireSpreadSpeed(this.getBranchFireSpreadSpeed())
                    .setFlammability(this.getBranchFlammability());

        if(this.isThick()) {
            ((BlockBranchThick)branch).otherBlock =
                    (BlockBranchThick)((BlockBranchThick)branch).otherBlock
                            .setFireSpreadSpeed(this.getBranchFireSpreadSpeed())
                            .setFlammability(this.getBranchFlammability());
        }

        return branch;
    }

    /**
     * The flammability of the generated branch blocks.  Defaults to 5.
     */
    protected int getBranchFlammability() {
        return 5;
    }

    /**
     * The fire spread speed of the generated branch blocks.  Defaults to 5.
     */
    protected int getBranchFireSpreadSpeed() {
        return 5;
    }


}
