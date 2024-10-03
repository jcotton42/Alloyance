package me.jcotton42.alloyance

import com.mojang.logging.LogUtils
import me.jcotton42.alloyance.datagen.generateData
import me.jcotton42.alloyance.registration.*
import net.minecraft.client.Minecraft
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.fml.event.lifecycle.FMLDedicatedServerSetupEvent
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.neoforge.forge.runForDist

/**
 * Main mod class. Should be an `object` declaration annotated with `@Mod`.
 * The modid should be declared in this object and should match the modId entry
 * in mods.toml.
 *
 * An example for blocks is in the `blocks` package of this mod.
 */
@Mod(Alloyance.ID)
object Alloyance {
    const val ID = "alloyance"

    // the logger for our mod
    val LOGGER = LogUtils.getLogger()

    init {
        LOGGER.info("Hello world!")

        AlloyanceBlocks.register(MOD_BUS)
        AlloyanceCreativeTabs.register(MOD_BUS)
        AlloyanceDataComponents.register(MOD_BUS)
        AlloyanceDataMaps.register(MOD_BUS)
        AlloyanceItems.register(MOD_BUS)
        AlloyanceMenuTypes.register(MOD_BUS)
        AlloyanceRecipes.register(MOD_BUS)
        AlloyanceSounds.register(MOD_BUS)
        MOD_BUS.addListener(::generateData)
        MOD_BUS.addListener(::registerCapabilities)

        val obj = runForDist(
            clientTarget = {
                MOD_BUS.addListener(Alloyance::onClientSetup)
                Minecraft.getInstance()
            },
            serverTarget = {
                MOD_BUS.addListener(Alloyance::onServerSetup)
                "test"
            })

        println(obj)
    }

    /**
     * This is used for initializing client specific
     * things such as renderers and keymaps
     * Fired on the mod specific event bus.
     */
    private fun onClientSetup(event: FMLClientSetupEvent) {
        LOGGER.info("Initializing client...")
    }

    /**
     * Fired on the global Forge bus.
     */
    private fun onServerSetup(event: FMLDedicatedServerSetupEvent) {
        LOGGER.info("Server starting...")
    }
}
