package live.ixnoah.tapactions.mixin;

import javax.annotation.Nullable;
import live.ixnoah.tapactions.actions.AccessorGuiAchievement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.achievement.GuiAchievement;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GuiAchievement.class)
public class MixinGuiAchievement implements AccessorGuiAchievement {
    @Shadow private String achievementTitle;
    @Shadow private String achievementDescription;
    @Shadow private long notificationTime;
    @Shadow private boolean permanentNotification;
    @Shadow private Achievement theAchievement;

    @Override
    public void tapactions_displayToast(@Nullable String title, @Nullable String desc, ItemStack stack) {
        if (title != null && !title.isEmpty()) this.achievementTitle = title;
        if (desc != null && !desc.isEmpty()) this.achievementDescription = desc;

        this.notificationTime = Minecraft.getSystemTime();

        Achievement ach = new Achievement("achievement.custom", "custom", 0, 0, stack, AchievementList.openInventory);
        ach.initIndependentStat();

        this.theAchievement = ach;
    }
}
