package live.ixnoah.tapactions.mixin;

import live.ixnoah.tapactions.misc.TitleHandler;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.server.S45PacketTitle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetHandlerPlayClient.class)
public class MixinNetHandlerPlayClient {
    @Inject(method = "handleTitle", at = @At("HEAD"), cancellable = true)
    public void handleTitle(S45PacketTitle packetIn, CallbackInfo ci) {
        TitleHandler.handleTitle(packetIn, ci);
    }
}
