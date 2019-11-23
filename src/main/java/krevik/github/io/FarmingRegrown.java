package krevik.github.io;

import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Krevik
 */
@Mod(ModReference.MOD_ID)
public final class FarmingRegrown {

    public static final Logger FARMING_LOG = LogManager.getLogger(ModReference.MOD_ID);

    public FarmingRegrown() {
        //ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigHandler.CONFIG_SPEC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::loadComplete);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::serverStarting);
    }

    private void setup(final FMLCommonSetupEvent event) {
    }


    private void loadComplete(final FMLLoadCompleteEvent event){

    }

    private void serverStarting(final FMLServerStartingEvent event){
    }


    private void setupClient(final FMLClientSetupEvent event) {

    }

}
