package live.ixnoah.tapactions

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import live.ixnoah.tapactions.actions.GeneralActions
import live.ixnoah.tapactions.actions.HudActions
import live.ixnoah.tapactions.actions.WorldActions
import live.ixnoah.tapactions.commands.CreateActionCommand
import live.ixnoah.tapactions.events.ClientTick
import live.ixnoah.tapactions.events.WorldLoad
import live.ixnoah.tapactions.misc.Version
import net.minecraftforge.client.ClientCommandHandler
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import java.io.InputStreamReader
import java.net.URL


@Mod(modid = "tapactions", useMetadata = true)
class TapActions {
    companion object ModInfo {
        const val MOD_ID = "tapactions"
        val MOD_VER = Version("0.2.1")

        var outdated = false
        var newerVersion = MOD_VER
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        // Commands
        ClientCommandHandler.instance.registerCommand(CreateActionCommand())

        // Events
        MinecraftForge.EVENT_BUS.register(WorldLoad())
        MinecraftForge.EVENT_BUS.register(ClientTick())

        // Deploy actions
        GeneralActions.deploy()
        WorldActions.deploy()
        HudActions.deploy()

        val thread = Thread {
            try {
                val url = URL("https://api.modrinth.com/v2/project/ZtvPWsL6/version?featured=true")
                val apiResponse = Gson().fromJson(InputStreamReader(url.openStream()), JsonArray::class.java)

                val latestVersion = apiResponse.get(0) as JsonObject
                val LATEST_VER = Version(latestVersion.get("version_number").asString)

                if (MOD_VER.lessThan(LATEST_VER)) outdated = true
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
        thread.start()
    }
}
