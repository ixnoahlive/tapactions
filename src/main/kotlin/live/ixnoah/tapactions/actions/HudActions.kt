package live.ixnoah.tapactions.actions

import live.ixnoah.tapactions.ActionManager
import net.minecraft.client.Minecraft
import net.minecraft.entity.boss.BossStatus
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import kotlin.concurrent.thread

interface AccessorGuiAchievement {
    fun tapactions_displayToast(title: String, desc: String, stack: ItemStack)
}

object HudActions {
    private fun actionBossbar( params : MutableMap<String, String> ) {
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

    var modifyTablist = false
    var tablistFooter = "No valid footer found :("
    private fun actionTablist( params: MutableMap<String, String> ) {
        modifyTablist = true
        if (params["restore"] != null) modifyTablist = false // This will reset it ASAP

        tablistFooter = params["footer"]!!.replace(Regex("&(?=[0-9a-fk-o])"), "§").replace("\\n", "\n")
    }

    private fun actionToast( params: MutableMap<String, String> ) {
        val guiAchievement = Minecraft.getMinecraft().guiAchievement
        val guiAchievementAcc = guiAchievement as AccessorGuiAchievement

        guiAchievementAcc.tapactions_displayToast(
            params["title"]?.replace(Regex("&(?=[0-9a-fk-o])"), "§") ?: "",
            params["desc"]?.replace(Regex("&(?=[0-9a-fk-o])"), "§") ?: "",
            ItemStack(Item.getByNameOrId(params["item"] ?: "minecraft:spruce_door"))
        )
    }

    fun deploy() {
        ActionManager.registerAction("tap:toast", ::actionToast)

        ActionManager.registerAction("tap:bossbar", ::actionBossbar)
        ActionManager.registerAction("tap:tablist", ::actionTablist, mutableListOf("footer"))
    }
}