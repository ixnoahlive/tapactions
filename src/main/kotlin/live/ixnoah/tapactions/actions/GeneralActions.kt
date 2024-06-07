package live.ixnoah.tapactions.actions

import CommandQueue
import live.ixnoah.tapactions.ActionManager
import live.ixnoah.tapactions.wrappers.Scoreboard

object GeneralActions {
    private val nameRegex = Regex("^[0-9A-z '\"!@#\$%^*?]{0,20}\$")
    var hasStatedIdentity = false

    /** This can be used to show the identity of a house, like name, links & description! */
    private val actionIdentity = { params : MutableMap<String, String> ->
        if (!hasStatedIdentity) {
            hasStatedIdentity = true
            val paramName = params["name"] ?: "Housing"

            if (nameRegex.containsMatchIn(paramName)) {
                Scoreboard.setTitle("§e§l" + paramName.uppercase())
            }

            if (params["run"] !== null) {
                CommandQueue.pushCommand("house:${params["run"]}")
            }
        }
    }

    private val actionVisibility = { params : MutableMap<String, String> ->
        val paramVis : Int? = params["max"]?.toIntOrNull()

        if (paramVis !== null) {
            CommandQueue.pushCommand("visibility $paramVis")
        }
    }

    fun deploy() {
        ActionManager.registerAction("tap:identity", actionIdentity)
        ActionManager.registerAction("tap:visibility", actionVisibility)
    }
}