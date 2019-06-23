package dev.satyrn.naturaltrees.worldgen;

import com.ferreusveritas.dynamictrees.api.worldgen.IBiomeDataBasePopulator;
import com.ferreusveritas.dynamictrees.worldgen.BiomeDataBase;
import com.ferreusveritas.dynamictrees.worldgen.BiomeDataBasePopulatorJson;
import dev.satyrn.naturaltrees.NaturalTrees;
import net.minecraft.util.ResourceLocation;

public class NaturalTreesBiomeDataBasePopulator implements IBiomeDataBasePopulator {

    public static final String RESOURCE_LOCATION = "worldgen/default.json";

    private final BiomeDataBasePopulatorJson populator;

    public NaturalTreesBiomeDataBasePopulator() {
        this.populator = new BiomeDataBasePopulatorJson(new ResourceLocation(NaturalTrees.MOD_ID, RESOURCE_LOCATION));
    }

    @Override
    public void populate(BiomeDataBase biomeDataBase) {
        this.populator.populate(biomeDataBase);
    }
}
