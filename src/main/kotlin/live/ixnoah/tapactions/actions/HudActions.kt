package live.ixnoah.tapactions.actions

import live.ixnoah.tapactions.ActionManager
import net.minecraft.entity.boss.BossStatus

object HudActions {
    private fun actionBossbar( params : MutableMap<String, String>) {
        if (params["no-clear"] == null) {
            BossStatus.statusBarTime = Int.MAX_VALUE
            BossStatus.healthScale = 1f
            BossStatus.bossName = ""
        }

        var maxHealth = 100

        if (params["name"] != null) BossStatus.bossName = params["name"]
        if (params["max_health"]?.toIntOrNull() != null) maxHealth = params["max_health"]?.toInt() ?: 100

        var healthScale = ( params["health"]?.toInt() ?: 100 ).toFloat() / maxHealth.toFloat()
        if (healthScale > 1 || healthScale < 0) healthScale = 1f

        if (params["no-clear"] == null
            || params["health"] != null
            || params["max_health"] != null) BossStatus.healthScale = healthScale

        if (params["duration"]?.toFloatOrNull() != null) BossStatus.statusBarTime = ((params["duration"]?.toFloat() ?: 1f) * 60).toInt()
    }

    fun deploy() {
        ActionManager.registerAction("tap:bossbar", ::actionBossbar)
    }
}