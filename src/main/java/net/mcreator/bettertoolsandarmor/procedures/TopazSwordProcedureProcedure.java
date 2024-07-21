package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.ParticleTypes;

import java.util.List;
import java.util.Comparator;

public class TopazSwordProcedureProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		Entity closest = null;
		double chance = 0;
		double range = 0;
		double particle_x = 0;
		double particle_y = 0;
		double particle_z = 0;
		double increment_x = 0;
		double increment_y = 0;
		double increment_z = 0;
		chance = 1;
		range = 3;
		if (world.getLevelData().isThundering() == true) {
			chance = chance * 1.5;
			range = range * 2;
		}
		if (entity instanceof LivingEntity && ((LivingEntity) sourceentity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.LUCK) != null) {
			chance = chance + ((LivingEntity) sourceentity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.LUCK).getValue() * 0.05;
		}
		if (Math.random() < chance) {
			{
				final Vec3 _center = new Vec3(x, y, z);
				List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate((range * 2) / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
				for (Entity entityiterator : _entfound) {
					if (entityiterator instanceof LivingEntity && !(entityiterator == sourceentity || entityiterator == entity)) {
						increment_x = (entityiterator.getX() - entity.getX()) / 16;
						increment_y = (entityiterator.getY() - entity.getY()) / 16;
						increment_z = (entityiterator.getZ() - entity.getZ()) / 16;
						particle_x = entity.getX();
						particle_y = entity.getY() + 2;
						particle_z = entity.getZ();
						for (int index0 = 0; index0 < 16; index0++) {
							world.addParticle(ParticleTypes.WAX_ON, particle_x, particle_y, particle_z, 0.1, 0.1, 0.1);
							particle_x = particle_x + increment_x;
							particle_y = particle_y + increment_y;
							particle_z = particle_z + increment_z;
						}
						entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.PLAYER_ATTACK), sourceentity), 1);
						if (world instanceof ServerLevel _level)
							_level.sendParticles(ParticleTypes.ELECTRIC_SPARK, (entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ()), 8, 0.25, 1, 0.25, 0.01);
						break;
					}
				}
			}
		}
	}
}
