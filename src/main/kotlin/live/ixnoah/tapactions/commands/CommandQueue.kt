import net.minecraft.client.Minecraft

object CommandQueue {
    private var queue = mutableListOf<String>()
    
    fun pushCommand(command: String) {
        queue.add(command)
    }
    
    fun runNextCommand() {
        if (queue.size == 0) return
        
        val command = queue.first()
        queue = queue.drop(1).toMutableList()

        Minecraft.getMinecraft().thePlayer.sendChatMessage("/$command")
    }

    fun onTick(tick: Int) {
        if (tick == 1) runNextCommand()
    }

    fun clearQueue() {
        queue.clear()
    }
}
