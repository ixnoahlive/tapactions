package live.ixnoah.tapactions.events

import CommandQueue
import live.ixnoah.tapactions.actions.WorldActions
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent

class ClientTick {
    private var tick = 0;

    @SubscribeEvent
    fun onEvent(event: ClientTickEvent) {
        if (tick > 20) tick = 0
        tick += 1

        CommandQueue.onTick(tick)
        WorldActions.particlesRendered = 0
    }
}