package live.ixnoah.tapactions.mixin;

import live.ixnoah.tapactions.actions.HudActions;
import live.ixnoah.tapactions.misc.TitleHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.server.S45PacketTitle;
import net.minecraft.network.play.server.S47PacketPlayerListHeaderFooter;
import net.minecraft.util.ChatComponentText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetHandlerPlayClient.class)
public class MixinNetHandlerPlayClient {
    @Inject(method = "handleTitle", at = @At("HEAD"), cancellable = true)
    public void handleTitle(S45PacketTitle packetIn, CallbackInfo ci) {
        TitleHandler.INSTANCE.handleTitle(packetIn, ci);
    }

    @Inject(method = "handlePlayerListHeaderFooter", at = @At("TAIL"))
    public void handlePlayerListHeaderFooter(S47PacketPlayerListHeaderFooter event, CallbackInfo ci) {
        if (HudActions.INSTANCE.getModifyTablist()) {
            Minecraft.getMinecraft().ingameGUI.getTabList().setFooter(
                    new ChatComponentText("\n" + HudActions.INSTANCE.getTablistFooter() + "\n")
            );
        }
    }
}