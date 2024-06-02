package live.ixnoah.tapactions.misc

import live.ixnoah.tapactions.ActionManager
import net.minecraft.network.play.server.S45PacketTitle
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

class TitleHandler {
    companion object {
        @JvmStatic
        fun handleTitle(packetIn: S45PacketTitle, ci: CallbackInfo) {
            if (packetIn.type.toString() !== "TITLE") return;

            var messageContent = packetIn.message.unformattedText
            if (!messageContent.startsWith("␁")) return;

            ci.cancel()

            messageContent = messageContent.replace("␁", "")
            var components = messageContent.split('␟')

            val actionName = components[0] ?: "void"
            components = components.drop(1)

            val actionParams = mutableMapOf<String, String>()

            components.map { paramData ->
                val keyValue = paramData.replace('=', '␃').split('␃')
                actionParams[keyValue[0]] = keyValue[1]
            }

            ActionManager.runAction(actionName, actionParams, true)

        }
    }
}