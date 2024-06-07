import net.minecraft.client.Minecraft

object CommandQueue {
    private var queue = mutableListOf<String>()
    
    fun pushCommand(command: String) {
        queue.add(command)
    }
    
    fun runNextCommand() {
        if (queue.size == 0) return
        
        val command = queue.first()
        queue.drop(1)

        Minecraft.getMinecraft().thePlayer.sendChatMessage("/" + command)
    }

    fun clearQueue() {
        queue.clear()
    }
}
