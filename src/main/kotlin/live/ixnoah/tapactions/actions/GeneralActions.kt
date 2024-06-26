package live.ixnoah.tapactions.actions

import CommandQueue
import live.ixnoah.tapactions.core.ActionManager
import live.ixnoah.tapactions.wrappers.Scoreboard
import net.minecraft.client.Minecraft
import net.minecraft.event.ClickEvent
import net.minecraft.event.HoverEvent
import net.minecraft.util.ChatComponentText
import net.minecraft.util.ChatStyle

object GeneralActions {
    private val nameRegex = Regex("^[0-9A-z '\"!@#\$%^*?]{0,20}\$")
    var hasStatedIdentity = false

    /** This can be used to show the identity of a house, like name, links & description! */
    private fun actionIdentity( params : MutableMap<String, String>) {
        if (!hasStatedIdentity) {
            hasStatedIdentity = true
            val paramName = params["name"] ?: "Housing"

            if (nameRegex.containsMatchIn(paramName)) {
                Scoreboard.setTitle("§e§l" + paramName.uppercase())
            }

            if (params["run"] != null) {
                CommandQueue.pushCommand("house:${params["run"]}")
            }
        }
    }

    private fun actionVisibility ( params : MutableMap<String, String> ) {
        val paramVis : Int? = params["max"]?.toIntOrNull()

        if (paramVis != null) {
            CommandQueue.pushCommand("visibility $paramVis")
        }
    }

    private fun actionVisitHouse( params: MutableMap<String, String> ) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(
            ChatComponentText("§eThis house wants you to visit §b${params["house"]}§e! Click to visit!")
                // This is fucking awful but Minecraft devs has forced my hand
                .setChatStyle(ChatStyle().setChatHoverEvent(
                    HoverEvent(HoverEvent.Action.SHOW_TEXT, ChatComponentText("§7/visit ${params["owner"]} ${params["house"]}"))
                ).setChatClickEvent(ClickEvent(ClickEvent.Action.RUN_COMMAND, "/visit ${params["owner"]} ${params["house"]}"))
                )
        )
    }

    fun deploy() {
        ActionManager.registerAction("tap:identity", ::actionIdentity)
        ActionManager.registerAction("tap:visibility", ::actionVisibility, mutableListOf("max"))
        ActionManager.registerAction("tap:visit", ::actionVisitHouse, mutableListOf("owner", "house"))
    }
}