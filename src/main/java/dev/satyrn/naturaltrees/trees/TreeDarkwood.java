package dev.satyrn.naturaltrees.trees;

import com.ferreusveritas.dynamictrees.blocks.BlockRooty;
import com.ferreusveritas.dynamictrees.systems.featuregen.FeatureGenFruit;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.trees.TreeFamily;
import dev.satyrn.naturaltrees.NaturalTrees;
import dev.satyrn.naturaltrees.NaturalTreesContent;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

public class TreeDarkwood extends NaturalTreeFamily {

    public class TreeDarkwoodSpecies extends Species {
        public TreeDarkwoodSpecies(TreeFamily treeFamily) {
            super(treeFamily.getName(), treeFamily, NaturalTreesContent.leaves.get("darkwood"));

            // Essentially the same as an apple tree
            setBasicGrowingParameters(0.4f, 10.0f, 1, 4, 0.7f);

            envFactor(BiomeDictionary.Type.NETHER, 1.1f);
            envFactor(BiomeDictionary.Type.DRY, 1.0f);
            envFactor(BiomeDictionary.Type.COLD, 0.25F);
            envFactor(BiomeDictionary.Type.HOT, 1.0f);

            clearAcceptableSoils();
            addAcceptableSoil(Blocks.NETHERRACK, Blocks.SOUL_SAND);

            generateSeed();
            addGenFeature(new FeatureGenFruit(NaturalTreesContent.blockChalkyApple).setRayDistance(4));
        }

        @Override
        public boolean isBiomePerfect(Biome biome) {
            return biome == Biomes.HELL;
        }

        @Override
        public BlockRooty getRootyBlock() {
            return NaturalTreesContent.blockRootyTaintedSoil;
        }


    }

    public TreeDarkwood() {
        super(new ResourceLocation(NaturalTrees.MOD_ID, "darkwood"));
        NaturalTreesContent.leaves.get("darkwood").setTree(this);
    }

    @Override
    public void createSpecies() {
        setCommonSpecies(new TreeDarkwoodSpecies(this));
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
    protected int getBranchFireSpreadSpeed() {
        return 0;
    }

    @Override
    protected int getBranchFlammability() {
        return 0;
    }
}
