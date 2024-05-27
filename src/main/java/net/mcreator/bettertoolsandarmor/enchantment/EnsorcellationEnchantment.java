
package net.mcreator.bettertoolsandarmor.enchantment;

import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.EquipmentSlot;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

public class EnsorcellationEnchantment extends Enchantment {
	public EnsorcellationEnchantment(EquipmentSlot... slots) {
		super(Enchantment.Rarity.RARE, EnchantmentCategory.BOW, slots);
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack itemstack) {
		return Ingredient.of(new ItemStack(BetterToolsModItems.ELECTRIC_STAFF.get()), new ItemStack(BetterToolsModItems.ICE_STAFF.get()), new ItemStack(BetterToolsModItems.FIRE_STAFF.get()), new ItemStack(BetterToolsModItems.WARDEN_STAFF.get()),
				new ItemStack(BetterToolsModItems.GUARDIAN_STAFF.get())).test(itemstack);
	}
}
