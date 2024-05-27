package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class GuardianStaffTooltipProcedure {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		execute(event, event.getEntity(), event.getItemStack(), event.getToolTip());
	}

	public static void execute(Entity entity, ItemStack itemstack, List<Component> tooltip) {
		execute(null, entity, itemstack, tooltip);
	}

	private static void execute(@Nullable Event event, Entity entity, ItemStack itemstack, List<Component> tooltip) {
		if (entity == null || tooltip == null)
			return;
		double damage = 0;
		double radius = 0;
		damage = 6;
		radius = 3;
		if (entity.isInWaterRainOrBubble()) {
			damage = damage * 2;
			radius = radius * 2;
		}
		if (itemstack.getItem() == BetterToolsModItems.GUARDIAN_STAFF.get()) {
			tooltip.add(Component.literal("\u00A77Staff Effects:"));
			tooltip.add(Component.literal(("\u00A72 " + ("" + damage).replace(".0", "") + " Water Pulse Damage")));
			tooltip.add(Component.literal(("\u00A72 " + ("" + radius).replace(".0", "") + " Block Radius")));
		}
	}
}
