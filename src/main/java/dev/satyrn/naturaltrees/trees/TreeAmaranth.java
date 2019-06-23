package dev.satyrn.naturaltrees.trees;

import com.ferreusveritas.dynamictrees.ModTrees;
import com.ferreusveritas.dynamictrees.api.TreeRegistry;
import com.ferreusveritas.dynamictrees.growthlogic.ConiferLogic;
import com.ferreusveritas.dynamictrees.systems.featuregen.FeatureGenClearVolume;
import com.ferreusveritas.dynamictrees.systems.featuregen.FeatureGenConiferTopper;
import com.ferreusveritas.dynamictrees.systems.featuregen.FeatureGenFlareBottom;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.trees.TreeFamily;

import com.progwml6.natura.overworld.NaturaOverworld;
import com.progwml6.natura.overworld.block.logs.BlockOverworldLog;
import dev.satyrn.naturaltrees.NaturalTrees;
import dev.satyrn.naturaltrees.NaturalTreesContent;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

// Amaranth Trees
public class TreeAmaranth extends TreeFamily {

    public class TreeAmaranthSpecies extends Species {

        public TreeAmaranthSpecies(TreeFamily treeFamily) {
            super(treeFamily.getName(), treeFamily, NaturalTreesContent.leaves.get("amaranth"));

            setBasicGrowingParameters(0.6f, 18.0f, 3, 4, 0.75f);
            setGrowthLogicKit(new ConiferLogic(5.0f).setHeightVariation(3).setHorizontalLimiter(3.0f));

            envFactor(BiomeDictionary.Type.COLD, 0.15f);
            envFactor(BiomeDictionary.Type.DRY, 0.10f);
            envFactor(BiomeDictionary.Type.HOT, 1.1f);
            envFactor(BiomeDictionary.Type.WET, 1.1f);

            setupStandardSeedDropping();
            addGenFeature(new FeatureGenClearVolume(6));
            addGenFeature(new FeatureGenConiferTopper(getLeavesProperties()));
            addGenFeature(new FeatureGenFlareBottom());
        }

        @Override
        public boolean isBiomePerfect(Biome biome) {
            return BiomeDictionary.hasType(biome, BiomeDictionary.Type.JUNGLE);
        }

        @Override
        public boolean isThick() {
            return true;
        }
    }

    public TreeAmaranth() {
        super(new ResourceLocation(NaturalTrees.MOD_ID, "amaranth"));
        NaturalTreesContent.leaves.get("amaranth").setTree(this);
    }

    @Override
    public TreeFamily setPrimitiveLog(IBlockState block) {
        return super.setPrimitiveLog(block);
    }

    @Override
    public void createSpecies() {
        setCommonSpecies(new TreeAmaranthSpecies(this));
        getCommonSpecies().generateSeed();
    }

    @Override
    public boolean autoCreateBranch() {
        return true;
    }

    @Override
    public boolean isThick() {
        return true;
    }
}
