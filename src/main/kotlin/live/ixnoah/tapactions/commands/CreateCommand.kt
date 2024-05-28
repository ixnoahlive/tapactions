package live.ixnoah.tapactions.commands

import net.minecraft.client.Minecraft
import net.minecraft.command.CommandBase
import net.minecraft.command.CommandException
import net.minecraft.command.ICommandSender
import net.minecraft.util.ChatComponentText

class CreateCommand: CommandBase() {
    private val splitter = " | "

    override fun getCommandName(): String {
        return "createaction"
    }

    override fun getCommandUsage(sender: ICommandSender?): String {
        return ""
    }

    @Throws(CommandException::class)
    override fun processCommand(sender: ICommandSender, args: Array<out String>?) {
        if (args?.size!! < 1) return sender.addChatMessage(ChatComponentText("§cUsage: /createaction <name> param=value | ..."))

        val name = args.first()

        val params = args.drop(1).joinToString(" ").replace(splitter, "␟")

        Minecraft.getMinecraft().thePlayer.sendChatMessage(
            "␁$name␟$params"
        )
    }

    override fun canCommandSenderUseCommand(sender: ICommandSender?): Boolean {
        return true
    }
}