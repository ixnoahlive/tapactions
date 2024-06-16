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
//                TODO: caret notation
//                part.startsWith("^") -> {
//                    val offset = part.drop(1).toDoubleOrNull() ?: 0.0
//                    coordinates[coordinateKey] = when (coordinateKey) {
//                        "x" -> player.posX + offset
//                        "y" -> player.posY + offset
//                        else -> player.posZ + offset
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