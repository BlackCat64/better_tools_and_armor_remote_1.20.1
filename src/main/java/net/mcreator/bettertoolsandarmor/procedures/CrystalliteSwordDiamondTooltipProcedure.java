package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.Screen;

import javax.annotation.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class CrystalliteSwordDiamondTooltipProcedure {
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
		if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:precision_weapons")))) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal("\u00A77When target is on full HP:"));
				if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:crystallite_axes")))) {
					tooltip.add(Component.literal("\u00A79+2 Attack Damage"));
				} else {
					tooltip.add(Component.literal("\u00A79+2.5 Attack Damage"));
				}
			} else {
				tooltip.add(Component.literal("\u00A78Press Shift for details"));
			}
		}
	}
}
