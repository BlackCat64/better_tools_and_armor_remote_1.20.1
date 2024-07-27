
package net.mcreator.bettertoolsandarmor.enchantment;

import net.minecraftforge.common.crafting.CompoundIngredient;

import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

public class KarmaCurseEnchantment extends Enchantment {
	public KarmaCurseEnchantment(EquipmentSlot... slots) {
		super(Enchantment.Rarity.RARE, EnchantmentCategory.WEAPON, slots);
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack itemstack) {
		return CompoundIngredient
				.of(Ingredient.of(ItemTags.create(new ResourceLocation("minecraft:swords"))), Ingredient.of(ItemTags.create(new ResourceLocation("minecraft:axes"))), Ingredient.of(ItemTags.create(new ResourceLocation("better_tools:daggers"))))
				.test(itemstack);
	}

	@Override
	public boolean isCurse() {
		return true;
	}
}
