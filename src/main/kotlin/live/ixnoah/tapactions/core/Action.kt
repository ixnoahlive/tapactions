package live.ixnoah.tapactions.core

class Action (
    val name: String,
    val callback: (MutableMap<String, String>) -> Unit,
    val paramsRequired: MutableList<String>?,

    val params: MutableMap<String, String>? = null,
    val description: String? = null
)