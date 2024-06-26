package live.ixnoah.tapactions.actions

import live.ixnoah.tapactions.core.ActionManager
import net.minecraft.client.Minecraft
import net.minecraft.util.EnumParticleTypes

object WorldActions {
    var particlesRendered = 0
    private fun actionParticle(params: MutableMap<String, String>) {
        if (particlesRendered > 100) return
        if (EnumParticleTypes.entries.none { it.name == params["type"]?.uppercase() }) return

        val particleType = EnumParticleTypes.valueOf(params["type"]!!.uppercase())

        val xyz = params["pos"]?.split(" ")?.map { item -> item.toDoubleOrNull() }
        val pos = mutableMapOf(
            "x" to (xyz?.get(0) ?: 0.00),
            "y" to (xyz?.get(1) ?: 0.00),
            "z" to (xyz?.get(2) ?: 0.00),
        )

        Minecraft.getMinecraft().theWorld.spawnParticle(
                particleType,
                pos["x"]!!, pos["y"]!!, pos["z"]!!,
                0.00, 0.00, 0.00
        )

        particlesRendered++
    }

    fun deploy() {
        ActionManager.registerAction("tap:particle", ::actionParticle, mutableListOf("type", "pos" ))
    }
}