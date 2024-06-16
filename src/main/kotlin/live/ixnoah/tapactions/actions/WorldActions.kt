package live.ixnoah.tapactions.actions

import live.ixnoah.tapactions.ActionManager
import live.ixnoah.tapactions.wrappers.Coordinates
import net.minecraft.client.Minecraft
import net.minecraft.util.EnumParticleTypes
import kotlin.math.floor

object WorldActions {
    var particlesRendered = 0
    private fun actionParticle(params: MutableMap<String, String>) {
        if (particlesRendered > 100) return
        if (EnumParticleTypes.entries.none { it.name == params["type"]?.uppercase() }) return

        val particleType = EnumParticleTypes.valueOf(params["type"]!!.uppercase())

        var pos = Coordinates.parseCoordinates(params["pos"]!!, Minecraft.getMinecraft().thePlayer)
        if (params["round"] != null) pos = pos.mapValues { (floor(it.value) + 0.5) }

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