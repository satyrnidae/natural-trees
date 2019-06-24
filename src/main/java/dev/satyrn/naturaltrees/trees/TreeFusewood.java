package dev.satyrn.naturaltrees.trees;

import com.ferreusveritas.dynamictrees.blocks.BlockBranch;
import com.ferreusveritas.dynamictrees.blocks.BlockRooty;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.trees.TreeFamily;
import dev.satyrn.naturaltrees.NaturalTrees;
import dev.satyrn.naturaltrees.NaturalTreesContent;
import dev.satyrn.naturaltrees.blocks.BlockFusewoodBranch;
import net.minecraft.block.BlockNetherrack;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

public class TreeFusewood extends NaturalTreeFamily {

    public class TreeFusewoodSpecies extends Species {
        public TreeFusewoodSpecies(TreeFamily treeFamily) {
            super(treeFamily.getName(), treeFamily, NaturalTreesContent.leaves.get("fusewood"));

            //Short, stocky, low-branched
            setBasicGrowingParameters(0.2f, 8.0f, 1, 2, 0.7f);

            envFactor(BiomeDictionary.Type.COLD, 0.1f);
            envFactor(BiomeDictionary.Type.HOT, 1.25f);
            envFactor(BiomeDictionary.Type.WET, 0.1f);
            envFactor(BiomeDictionary.Type.DRY, 1.25f);

            clearAcceptableSoils();
            addAcceptableSoil(Blocks.NETHERRACK, Blocks.SOUL_SAND);

            generateSeed();
            setupStandardSeedDropping();
        }

        @Override
        public boolean isBiomePerfect(Biome biome) {
            return biome == Biomes.HELL;
        }

        @Override
        public BlockRooty getRootyBlock() {
            return NaturalTreesContent.blockRootyTaintedSoil;
        }

        @Override
        public boolean isThick() {
            return true;
        }
    }

    public TreeFusewood() {
        super(new ResourceLocation(NaturalTrees.MOD_ID, "fusewood"));
        NaturalTreesContent.leaves.get("fusewood").setTree(this);
    }

    @Override
    public void createSpecies() {
        this.setCommonSpecies(new TreeFusewoodSpecies(this));
    }

    @Override
    public boolean isThick() {
        return true;
    }

    @Override
    public boolean autoCreateBranch() {
        return true;
    }

    @Override
    public BlockBranch createBranch() {
        return new BlockFusewoodBranch();
    }
}
