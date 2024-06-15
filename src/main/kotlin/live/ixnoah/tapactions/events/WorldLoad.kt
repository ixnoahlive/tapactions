package live.ixnoah.tapactions.events

import CommandQueue
import live.ixnoah.tapactions.TapActions
import live.ixnoah.tapactions.actions.GeneralActions
import live.ixnoah.tapactions.actions.HudActions
import net.minecraft.client.Minecraft
import net.minecraft.entity.boss.BossStatus
import net.minecraft.event.ClickEvent
import net.minecraft.event.HoverEvent
import net.minecraft.util.ChatComponentText
import net.minecraft.util.ChatStyle
import net.minecraftforge.event.entity.EntityJoinWorldEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class WorldLoad {
    @SubscribeEvent
    fun onEvent(event: EntityJoinWorldEvent) {
        if (event.entity != Minecraft.getMinecraft().thePlayer) return

        BossStatus.statusBarTime = 0 // clears the bossbar
        CommandQueue.clearQueue()
        GeneralActions.hasStatedIdentity = false

        HudActions.modifyTablist = false
        HudActions.tablistFooter = "No valid footer found :("

        if (TapActions.outdated) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(
                ChatComponentText("\n§e§l[!]§f TapActions is quite outdated, houses may break! §e§nClick to update§r! §7(${TapActions.MOD_VER} => ${TapActions.newerVersion})\n")
                    .setChatStyle(ChatStyle()
                        .setChatClickEvent(ClickEvent(ClickEvent.Action.OPEN_URL, "https://modrinth.com/mod/tapactions/versions"))
                        .setChatHoverEvent(HoverEvent(HoverEvent.Action.SHOW_TEXT, ChatComponentText("§7https://modrinth.com/mod/tapactions")))
                    )
            )

            TapActions.outdated = false
        }
    }
}
