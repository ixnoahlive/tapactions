package live.ixnoah.tapactions.actions

import live.ixnoah.tapactions.ActionManager
import live.ixnoah.tapactions.wrappers.Scoreboard

class GeneralActions {
    private val nameRegex = Regex("^[0-9A-z '\"!@#\$%^*?]{0,20}\$")

    /** This can be used to show the identity of a house, like name, links & description! :D */
    private val actionIdentity = { params : MutableMap<String, String> ->
        val paramName = params["name"] ?: "Housing"

        if (nameRegex.containsMatchIn(paramName)) {
            Scoreboard.setTitle("§e§l" + paramName.uppercase())
        }
    }

    fun deploy() {
        ActionManager.registerAction("tap:identity", actionIdentity)
    }
}