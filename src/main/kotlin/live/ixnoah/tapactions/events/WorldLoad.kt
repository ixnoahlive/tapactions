package live.ixnoah.tapactions.events

import CommandQueue
import live.ixnoah.tapactions.actions.GeneralActions
import net.minecraft.client.Minecraft
import net.minecraft.entity.boss.BossStatus
import net.minecraftforge.event.entity.EntityJoinWorldEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class WorldLoad {
    @SubscribeEvent
    fun onEvent(event: EntityJoinWorldEvent) {
        if (event.entity !== Minecraft.getMinecraft().thePlayer) return

        BossStatus.statusBarTime = 0 // clears the bossbar
        CommandQueue.clearQueue()
        GeneralActions.hasStatedIdentity = false
    }
}
