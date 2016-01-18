package com.pahimar.ee3;

import com.pahimar.ee3.array.AlchemyArrayRegistry;
import com.pahimar.ee3.command.CommandEE;
import com.pahimar.ee3.exchange.CachedOreDictionary;
import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.handler.*;
import com.pahimar.ee3.init.*;
import com.pahimar.ee3.knowledge.AbilityRegistry;
import com.pahimar.ee3.knowledge.TransmutationKnowledgeRegistry;
import com.pahimar.ee3.network.Network;
import com.pahimar.ee3.proxy.IProxy;
import com.pahimar.ee3.recipe.AludelRecipeManager;
import com.pahimar.ee3.recipe.RecipeRegistry;
import com.pahimar.ee3.reference.Files;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Reference;
import com.pahimar.ee3.reference.Settings;
import com.pahimar.ee3.test.EnergyValueMappingsTestSuite;
import com.pahimar.ee3.util.LogHelper;
import com.pahimar.ee3.util.SerializationHelper;
import com.pahimar.ee3.util.TileEntityDataHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.io.File;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, certificateFingerprint = Reference.FINGERPRINT, version = Reference.MOD_VERSION, dependencies = Reference.DEPENDENCIES, guiFactory = Reference.GUI_FACTORY_CLASS)
public class EquivalentExchange3
{
    @Mod.Instance(Reference.MOD_ID)
    public static EquivalentExchange3 instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void invalidFingerprint(FMLFingerprintViolationEvent event) {

        if (Reference.FINGERPRINT.equals("@FINGERPRINT@")) {
            LogHelper.info(Messages.NO_FINGERPRINT_MESSAGE);
        } else {
            LogHelper.warn(Messages.INVALID_FINGERPRINT_MESSAGE);
        }
    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {

        SerializationHelper.initModDataDirectories();
        TransmutationKnowledgeRegistry.getInstance();
        AbilityRegistry.getInstance().loadAbilityRegistryFromFile(Settings.Abilities.onlyLoadFile);
        event.registerServerCommand(new CommandEE());
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        Files.Global.init(event);
        Network.init();
        proxy.registerKeybindings();
        ModItems.init();
        ModBlocks.init();
        EnergyValues.addDefaultEnergyValues();
        AlchemyArrays.registerAlchemyArrays();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
        TileEntities.init();
        proxy.initRenderingAndTextures();
        proxy.registerEventHandlers();
        CraftingHandler.init();
        Recipes.init();
        GameRegistry.registerFuelHandler(new FuelHandler());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

        CachedOreDictionary.getInstance();
        Abilities.initNotLearnables();
    }

    @Mod.EventHandler
    public void onServerStopping(FMLServerStoppingEvent event) {

        WorldEventHandler.hasInitilialized = false;
        EnergyValueRegistry.getInstance().save();
        TransmutationKnowledgeRegistry.getInstance().clear();
        AbilityRegistry.getInstance().save();
    }

    @Mod.EventHandler
    public void handleMissingMappingEvent(FMLMissingMappingsEvent event) {

        for (FMLMissingMappingsEvent.MissingMapping mapping : event.get()) {
            if (mapping.type == GameRegistry.Type.ITEM) {
                if (mapping.name.equals("EE3:alchemicalTome")) {
                    mapping.remap(ModItems.alchenomicon);
                }
            }
        }
    }

    public EnergyValueRegistry getEnergyValueRegistry() {
        return EnergyValueRegistry.getInstance();
    }

    public RecipeRegistry getRecipeRegistry() {
        return RecipeRegistry.getInstance();
    }

    public AludelRecipeManager getAludelRecipeManager() {
        return AludelRecipeManager.getInstance();
    }

    public AbilityRegistry getAbilityRegistry() {
        return AbilityRegistry.getInstance();
    }

    public AlchemyArrayRegistry getAlchemyArrayRegistry() {
        return AlchemyArrayRegistry.getInstance();
    }

    public TransmutationKnowledgeRegistry getTransmutationKnowledgeRegistry() {
        return TransmutationKnowledgeRegistry.getInstance();
    }

    public TileEntityDataHelper getTileEntityDataHelper() {
        return TileEntityDataHelper.getInstance();
    }

    public void runEnergyValueMappingTest(File file) {
        runEnergyValueMappingTest(file, false);
    }

    public void runEnergyValueMappingTest(File file, boolean strict) {
        new EnergyValueMappingsTestSuite(file).runTestSuite(strict);
    }
}
