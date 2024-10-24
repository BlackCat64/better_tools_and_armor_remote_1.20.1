package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Display;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModParticleTypes;

import java.util.Comparator;

public class FrozenEffectParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		Entity display = null;
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (BetterToolsModParticleTypes.ICE_PARTICLE.get()), x, (y + entity.getBbHeight()), z, 1, 0.33, 0.5, 0.33, 0.015);
		entity.setTicksFrozen(135);
		entity.makeStuckInBlock(Blocks.AIR.defaultBlockState(), new Vec3(0.25, 0.05, 0.25));
		display = (Entity) world.getEntitiesOfClass(Display.BlockDisplay.class, AABB.ofSize(new Vec3(x, y, z), 3, 3, 3), e -> true).stream().sorted(new Object() {
			Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
				return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
			}
		}.compareDistOf(x, y, z)).findFirst().orElse(null);
		if (display instanceof Display.BlockDisplay) {
			if (display.getPersistentData().getBoolean("freeze_effect")) {
				{
					Entity _ent = display;
					if (!_ent.level().isClientSide() && _ent.getServer() != null) {
						_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
								_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), "tp @s @e[nbt={ForgeData:{is_frozen:1b}},limit=1,sort=nearest]");
					}
				}
				{
					Entity _ent = display;
					_ent.setYRot(0);
					_ent.setXRot(0);
					_ent.setYBodyRot(_ent.getYRot());
					_ent.setYHeadRot(_ent.getYRot());
					_ent.yRotO = _ent.getYRot();
					_ent.xRotO = _ent.getXRot();
					if (_ent instanceof LivingEntity _entity) {
						_entity.yBodyRotO = _entity.getYRot();
						_entity.yHeadRotO = _entity.getYRot();
					}
				}
				if (((Entity) world.getEntitiesOfClass(LivingEntity.class, AABB.ofSize(new Vec3((display.getX()), (display.getY()), (display.getZ())), 1, 1, 1), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf((display.getX()), (display.getY()), (display.getZ()))).findFirst().orElse(null)) == null) {
					if (!display.level().isClientSide())
						display.discard();
				}
			}
		}
	}
}
