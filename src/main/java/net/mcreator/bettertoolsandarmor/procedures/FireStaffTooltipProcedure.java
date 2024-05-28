package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.Screen;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModEnchantments;

import javax.annotation.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class FireStaffTooltipProcedure {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		execute(event, event.getItemStack(), event.getToolTip());
	}

	public static void execute(ItemStack itemstack, List<Component> tooltip) {
		execute(null, itemstack, tooltip);
	}

	private static void execute(@Nullable Event event, ItemStack itemstack, List<Component> tooltip) {
		if (tooltip == null)
			return;
		double explosion_power = 0;
		double cooldown = 0;
		if (itemstack.getItem() == BetterToolsModItems.FIRE_STAFF.get()) {
			if (Screen.hasShiftDown()) {
				explosion_power = 0.5 * itemstack.getEnchantmentLevel(BetterToolsModEnchantments.ENSORCELLATION.get());
				cooldown = 10 - 1.5 * itemstack.getEnchantmentLevel(BetterToolsModEnchantments.SWIFT_CAST.get());
				tooltip.add(Component.literal("\u00A77Staff Effects:"));
				tooltip.add(Component.literal("\u00A72 10s Burn Time"));
				if (EnchantmentHelper.getItemEnchantmentLevel(BetterToolsModEnchantments.ENSORCELLATION.get(), itemstack) != 0) {
					tooltip.add(Component.literal(("\u00A72 " + ("" + explosion_power).replace(".0", "") + " Explosion Power")));
				}
				tooltip.add(Component.literal(("\u00A7c " + ("" + cooldown).replace(".0", "") + "s Cooldown on hit")));
				tooltip.add(Component.literal("\u00A74Effect doubles in the Nether"));
			} else {
				tooltip.add(Component.literal("\u00A78Press Shift for details"));
			}
		}
	}
}
