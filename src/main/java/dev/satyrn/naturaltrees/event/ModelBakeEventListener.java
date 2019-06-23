package dev.satyrn.naturaltrees.event;

import com.ferreusveritas.dynamictrees.models.bakedmodels.BakedModelBlockRooty;
import dev.satyrn.naturaltrees.NaturalTrees;
import dev.satyrn.naturaltrees.NaturalTreesContent;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = NaturalTrees.MOD_ID)
public class ModelBakeEventListener {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onModelBakeEvent(ModelBakeEvent event) {
        Block[] roots = { NaturalTreesContent.blockRootyTaintedSoil};

        for(Block root : roots) {
            IBakedModel rootModel = event.getModelRegistry().getObject(new ModelResourceLocation(Objects.requireNonNull(root.getRegistryName()), "normal"));
            if(rootModel != null) {
                BakedModelBlockRooty override = new BakedModelBlockRooty(rootModel);
                event.getModelRegistry().putObject(new ModelResourceLocation(root.getRegistryName(), "normal"), override);
            }
        }
    }

}
