package live.ixnoah.tapactions.actions

import live.ixnoah.tapactions.ActionManager
import net.minecraft.client.Minecraft
import net.minecraft.util.EnumParticleTypes

object WorldActions {
    private val actionParticle = { params: MutableMap<String, String> ->
        val particleType = params["particle"]?.let { EnumParticleTypes.valueOf(it) }

        if (particleType !== null) {
            Minecraft.getMinecraft().theWorld.spawnParticle(
                particleType,
                params["x"]?.toDoubleOrNull()  ?: 0.00,
                params["y"]?.toDoubleOrNull()  ?: 0.00,
                params["z"]?.toDoubleOrNull()  ?: 0.00,
                params["ox"]?.toDoubleOrNull() ?: 0.00,
                params["oy"]?.toDoubleOrNull() ?: 0.00,
                params["oz"]?.toDoubleOrNull() ?: 0.00,
            )
        }
    }

    fun deploy() {
        ActionManager.registerAction(
            "tap:particle",
            actionParticle,
            mutableListOf(
                "particle",
                "x", "y", "z",
            ))
    }
}