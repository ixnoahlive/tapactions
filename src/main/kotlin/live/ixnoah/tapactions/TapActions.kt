package live.ixnoah.tapactions

import live.ixnoah.tapactions.actions.GeneralActions
import live.ixnoah.tapactions.commands.CreateCommand
import net.minecraft.client.Minecraft
import net.minecraftforge.client.ClientCommandHandler
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import java.util.logging.LogManager

@Mod(modid = "tapactions", useMetadata = true)
class TapActions {
    companion object ModInfo {
        const val MOD_ID = "tapactions"
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        try {
            val resource: net.minecraft.client.resources.IResource = Minecraft.getMinecraft().resourceManager
                .getResource(net.minecraft.util.ResourceLocation("test:test.txt"))
            org.apache.commons.io.IOUtils.copy(resource.inputStream, java.lang.System.out)
        } catch (e: java.io.IOException) {
            throw java.lang.RuntimeException(e)
        }
        println("^_^")

        // Registers
        ClientCommandHandler.instance.registerCommand(CreateCommand())

        // Deploy actions
        GeneralActions().deploy()
    }
}
