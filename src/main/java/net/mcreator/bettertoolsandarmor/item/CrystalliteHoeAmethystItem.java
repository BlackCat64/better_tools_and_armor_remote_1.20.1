
package net.mcreator.bettertoolsandarmor.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.HoeItem;
import net.minecraft.network.chat.Component;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import java.util.List;

public class CrystalliteHoeAmethystItem extends HoeItem {
	public CrystalliteHoeAmethystItem() {
		super(new Tier() {
			public int getUses() {
				return 1800;
			}

			public float getSpeed() {
				return 10f;
			}

			public float getAttackDamageBonus() {
				return 1.5f;
			}

			public int getLevel() {
				return 4;
			}

			public int getEnchantmentValue() {
				return 20;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(BetterToolsModItems.CRYSTALLITE_SHARDS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_GEM.get()), new ItemStack(Items.AMETHYST_SHARD));
			}
		}, 0, 0f, new Item.Properties().fireResistant());
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, level, list, flag);
		list.add(Component.literal("\u00A77Upgrade: \u00A7dAmethyst"));
		list.add(Component.literal("\u00A77Ability:"));
		list.add(Component.literal("\u00A7dEcholocation - Right-click while crouching to point towards nearby ores/loot"));
	}
}
