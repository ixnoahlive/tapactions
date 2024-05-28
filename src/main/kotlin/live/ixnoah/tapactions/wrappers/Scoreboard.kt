package live.ixnoah.tapactions.wrappers

import net.minecraft.client.Minecraft
import net.minecraft.scoreboard.ScoreObjective
import net.minecraft.scoreboard.Scoreboard

class Scoreboard {
    fun getScoreboard(): Scoreboard? {
        return Minecraft.getMinecraft().theWorld.scoreboard
    }

    fun getSidebar(): ScoreObjective? {
        return getScoreboard()?.getObjectiveInDisplaySlot(1)
    }

    fun getTitle(): String? {
        return getSidebar()?.displayName
    }

    fun setTitle(title: String) {
        getSidebar()?.displayName = title
    }
}