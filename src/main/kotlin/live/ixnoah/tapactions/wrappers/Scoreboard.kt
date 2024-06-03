package live.ixnoah.tapactions.wrappers

import net.minecraft.client.Minecraft
import net.minecraft.scoreboard.ScoreObjective
import net.minecraft.scoreboard.Scoreboard as MCScoreboard

object Scoreboard {
    fun getScoreboard(): MCScoreboard? {
        return Minecraft.getMinecraft().theWorld.scoreboard
    }

    fun getSidebar(): ScoreObjective? {
        return getScoreboard()?.getObjectiveInDisplaySlot(1)
    }

    fun setTitle(title: String) {
        getSidebar()?.displayName = title
    }
}