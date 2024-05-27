
package net.mcreator.bettertoolsandarmor.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;

import java.util.List;

public class LightPolishedCoalWallBlock extends WallBlock {
	public LightPolishedCoalWallBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).sound(SoundType.METAL).strength(2.5f, 6f).requiresCorrectToolForDrops().dynamicShape().forceSolidOn());
	}

	@Override
	public void appendHoverText(ItemStack itemstack, BlockGetter level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, level, list, flag);
		list.add(Component.literal("\u00A77Gray Metallic Building Block"));
	}
}
