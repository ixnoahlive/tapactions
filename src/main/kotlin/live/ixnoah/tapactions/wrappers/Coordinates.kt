package live.ixnoah.tapactions.wrappers

import net.minecraft.client.Minecraft
import net.minecraft.client.entity.EntityPlayerSP

object Coordinates {
    fun parseCoordinates(input: String, player: EntityPlayerSP): Map<String, Double> {
        val parts = input.split(" ").take(3)
        val coordinates = mutableMapOf<String, Double>()

        val rotations = arrayOf(player.rotationYaw, player.rotationPitch, player.rotationYawHead)

        for ((index, part) in parts.withIndex()) {
            val coordinateKey = when (index) {
                0 -> "x"
                1 -> "y"
                else -> "z"
            }

            when {
                part.startsWith("~") -> {
                    val offset = part.drop(1).toDoubleOrNull() ?: 0.0
                    coordinates[coordinateKey] = when (coordinateKey) {
                        "x" -> player.posX + offset
                        "y" -> player.posY + offset
                        else -> player.posZ + offset
                    }
                }
//                part.startsWith("^") -> {
//                    val offset = part.drop(1).toDoubleOrNull() ?: 0.0
//
//                    val pitchAdjusted = Math.toRadians((player.rotationPitch + 90).toDouble())
//                    val yawAdjusted = Math.toRadians((player.rotationYaw - 90).toDouble())
//
//                    coordinates[coordinateKey] = when (coordinateKey) {
//                        "x" -> player.posX + offset * Math.sin(pitchAdjusted) * Math.cos(yawAdjusted)
//                        "y" -> player.posY + Math.cos(pitchAdjusted)
//                        else -> player.posZ + Math.sin(pitchAdjusted) * Math.sin(yawAdjusted)
//                    }
//                }
                else -> {
                    coordinates[coordinateKey] = part.toDoubleOrNull() ?: 0.0
                }
            }
        }

        return coordinates
    }
}
