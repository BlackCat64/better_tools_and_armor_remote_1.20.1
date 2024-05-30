
package net.mcreator.bettertoolsandarmor.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.util.RandomSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;

import net.mcreator.bettertoolsandarmor.procedures.IceStaffProjectileParticlesProcedure;
import net.mcreator.bettertoolsandarmor.procedures.IceStaffProjectileMissesProcedure;
import net.mcreator.bettertoolsandarmor.procedures.IceStaffProjectileFromDispenserHitsProcedure;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModEntities;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class IceStaffProjectileFromDispenserEntity extends AbstractArrow implements ItemSupplier {
	public static final ItemStack PROJECTILE_ITEM = new ItemStack(BetterToolsModItems.ICE_STAFF.get());

	public IceStaffProjectileFromDispenserEntity(PlayMessages.SpawnEntity packet, Level world) {
		super(BetterToolsModEntities.ICE_STAFF_PROJECTILE_FROM_DISPENSER.get(), world);
	}

	public IceStaffProjectileFromDispenserEntity(EntityType<? extends IceStaffProjectileFromDispenserEntity> type, Level world) {
		super(type, world);
	}

	public IceStaffProjectileFromDispenserEntity(EntityType<? extends IceStaffProjectileFromDispenserEntity> type, double x, double y, double z, Level world) {
		super(type, x, y, z, world);
	}

	public IceStaffProjectileFromDispenserEntity(EntityType<? extends IceStaffProjectileFromDispenserEntity> type, LivingEntity entity, Level world) {
		super(type, entity, world);
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public ItemStack getItem() {
		return PROJECTILE_ITEM;
	}

	@Override
	protected ItemStack getPickupItem() {
		return PROJECTILE_ITEM;
	}

	@Override
	protected void doPostHurtEffects(LivingEntity entity) {
		super.doPostHurtEffects(entity);
		entity.setArrowCount(entity.getArrowCount() - 1);
	}

	@Override
	public void onHitEntity(EntityHitResult entityHitResult) {
		super.onHitEntity(entityHitResult);
		IceStaffProjectileFromDispenserHitsProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), entityHitResult.getEntity(), this);
	}

	@Override
	public void onHitBlock(BlockHitResult blockHitResult) {
		super.onHitBlock(blockHitResult);
		IceStaffProjectileMissesProcedure.execute(this.level(), blockHitResult.getBlockPos().getX(), blockHitResult.getBlockPos().getY(), blockHitResult.getBlockPos().getZ(), this.getOwner(), this);
	}

	@Override
	public void tick() {
		super.tick();
		IceStaffProjectileParticlesProcedure.execute(this.level(), this);
		if (this.inGround)
			this.discard();
	}

	public static IceStaffProjectileFromDispenserEntity shoot(Level world, LivingEntity entity, RandomSource source) {
		return shoot(world, entity, source, 1f, 0.2, 0);
	}

	public static IceStaffProjectileFromDispenserEntity shoot(Level world, LivingEntity entity, RandomSource random, float power, double damage, int knockback) {
		IceStaffProjectileFromDispenserEntity entityarrow = new IceStaffProjectileFromDispenserEntity(BetterToolsModEntities.ICE_STAFF_PROJECTILE_FROM_DISPENSER.get(), entity, world);
		entityarrow.shoot(entity.getViewVector(1).x, entity.getViewVector(1).y, entity.getViewVector(1).z, power * 2, 0);
		entityarrow.setSilent(true);
		entityarrow.setCritArrow(false);
		entityarrow.setBaseDamage(damage);
		entityarrow.setKnockback(knockback);
		world.addFreshEntity(entityarrow);
		world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.arrow.shoot")), SoundSource.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
		return entityarrow;
	}

	public static IceStaffProjectileFromDispenserEntity shoot(LivingEntity entity, LivingEntity target) {
		IceStaffProjectileFromDispenserEntity entityarrow = new IceStaffProjectileFromDispenserEntity(BetterToolsModEntities.ICE_STAFF_PROJECTILE_FROM_DISPENSER.get(), entity, entity.level());
		double dx = target.getX() - entity.getX();
		double dy = target.getY() + target.getEyeHeight() - 1.1;
		double dz = target.getZ() - entity.getZ();
		entityarrow.shoot(dx, dy - entityarrow.getY() + Math.hypot(dx, dz) * 0.2F, dz, 1f * 2, 12.0F);
		entityarrow.setSilent(true);
		entityarrow.setBaseDamage(0.2);
		entityarrow.setKnockback(0);
		entityarrow.setCritArrow(false);
		entity.level().addFreshEntity(entityarrow);
		entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.arrow.shoot")), SoundSource.PLAYERS, 1, 1f / (RandomSource.create().nextFloat() * 0.5f + 1));
		return entityarrow;
	}
}
