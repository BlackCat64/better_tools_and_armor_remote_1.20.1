package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.Screen;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class CrystalliteRubyArmorTooltipProcedure {
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
		if (itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_RUBY_HELMET.get() || itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_RUBY_CHESTPLATE.get()
				|| itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_RUBY_LEGGINGS.get() || itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_RUBY_BOOTS.get()) {
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == itemstack.getItem()
					|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == itemstack.getItem()
					|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == itemstack.getItem()
					|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == itemstack.getItem()) {
				tooltip.add(Component
						.literal(("\u00A72 " + new java.text.DecimalFormat("##.#").format((((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getValue() / 0.1) * 100) + "% Movement Speed")));
			} else {
				tooltip.add(Component.literal("\u00A79+10% Speed"));
			}
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal("\u00A77Full-set bonus:"));
				tooltip.add(Component.literal("\u00A79+10% Speed"));
			} else {
				tooltip.add(Component.literal("\u00A78Press Shift for details"));
			}
		}
	}
}
