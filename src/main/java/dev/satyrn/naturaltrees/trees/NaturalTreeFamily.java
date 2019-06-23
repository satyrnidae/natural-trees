package dev.satyrn.naturaltrees.trees;

import com.ferreusveritas.dynamictrees.trees.TreeFamily;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public abstract class NaturalTreeFamily extends TreeFamily {
    public NaturalTreeFamily(ResourceLocation name) {
        super(name);
    }

    @Override
    public TreeFamily setPrimitiveLog(IBlockState block) {
        return super.setPrimitiveLog(block);
    }

    @Override
    public TreeFamily setStick(ItemStack stick) { return super.setStick(stick); }
}
