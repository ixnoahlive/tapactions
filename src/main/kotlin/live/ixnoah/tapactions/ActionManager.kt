package live.ixnoah.tapactions

import net.minecraft.client.Minecraft
import net.minecraft.util.ChatComponentText
import org.apache.logging.log4j.LogManager

object ActionManager {
    private val registers = mutableMapOf<String, Action>()

    fun registerAction(name: String, callback: (MutableMap<String, String>) -> Unit, paramsRequired: MutableList<String>? = mutableListOf(), params: MutableMap<String, String>? = mutableMapOf(), description: String? = "No description" ) {
        if (name == "void" || !name.contains(":")) throw IllegalArgumentException("Illegal name, must include a namespace (e.g. mymod:myaction) and may not be void.")
        if (registers.containsKey(name)) throw IllegalArgumentException("Duplicate action name, name must be unique!")

        registers[name] = Action( name, callback, paramsRequired, params, description )
    }

    // Keep in mind, this is way more interactive with the user and thus should be treated carefully.
    // Please do not throw exceptions unless absolutely necessary, instead opt to *warn peacefully* in the console.

    fun runAction(name: String, params: MutableMap<String, String>?, devMode: Boolean?) {
        val action = registers[name]
            ?: return LogManager.getLogger(TapActions.MOD_ID).warn("Could not find '$name', aborted action!")

        val definedParams = params?.keys
        val missingParams = mutableListOf<String>()

        if (definedParams != null && action.paramsRequired?.isNotEmpty() == true) {
            action.paramsRequired.forEach { paramName ->
                if (!definedParams.contains(paramName)) missingParams.add(paramName)
            }
        }

        if (missingParams.isNotEmpty() && devMode == true)
            return Minecraft.getMinecraft().thePlayer.addChatMessage(
                ChatComponentText("§e§lWARN§f TapAction '$name' is missing params ${missingParams.joinToString(",")}")
            )

        action.callback(params ?: mutableMapOf())
    }
}