package com.lset.atom.common.entity.LivingEntity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Arm;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

public class LivingPlayerGeo extends LivingEntity implements GeoEntity {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    private final DefaultedList<ItemStack> armorItems = DefaultedList.ofSize(4, ItemStack.EMPTY);  // Броня
    private final DefaultedList<ItemStack> handItems = DefaultedList.ofSize(2, ItemStack.EMPTY);  // Предметы в руках

    public LivingPlayerGeo (EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType,world);
    }

    public static DefaultAttributeContainer.Builder setAttributes(){
        return LivingEntity.createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0)  // Здоровье
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0)  // Урон атаки
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25);  // Скорость движения
    }
    // Реакция на получение урона
    @Override
    public boolean damage(DamageSource source, float amount) {
        return super.damage(source, amount);
    }
    // Воспроизведение звука при получении урона
    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_COW_HURT;
    }
    // Воспроизведение звука при смерти
    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_COW_DEATH;
    }
    // Звук шага


    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_COW_STEP, 0.15F, 1.0F);
    }
    // Поведение при атаке игрока
    @Override
    public void onAttacking(Entity target) {
        super.onAttacking(target);
    }
    @Override
    protected void dropLoot(DamageSource source, boolean causedByPlayer) {
        super.dropLoot(source, causedByPlayer);
        // Добавь лут, который будет дропаться
    }

    // Методы для работы с экипировкой

    // Возвращает элементы брони
    @Override
    public Iterable<ItemStack> getArmorItems() {
        return this.armorItems;
    }

    // Возвращает элемент экипировки в указанном слоте
    @Override
    public ItemStack getEquippedStack(EquipmentSlot slot) {
        switch (slot.getType()) {
            case HAND:
                return this.handItems.get(slot.getEntitySlotId());
            case ARMOR:
                return this.armorItems.get(slot.getEntitySlotId());
            default:
                return ItemStack.EMPTY;
        }
    }

    // Одевает предмет в слот (рука/броня)
    @Override
    public void equipStack(EquipmentSlot slot, ItemStack stack) {
        switch (slot.getType()) {
            case HAND:
                this.handItems.set(slot.getEntitySlotId(), stack);
                break;
            case ARMOR:
                this.armorItems.set(slot.getEntitySlotId(), stack);
                break;
        }
    }

    // Возвращает главную руку (левая/правая)
    @Override
    public Arm getMainArm() {
        return Arm.RIGHT;  // Можно вернуть LEFT, если хотите, чтобы существо было левшой
    }
    // Синхронизация с клиентом (для отправки изменений экипировки)
    @Override
    protected void processEquippedStack(ItemStack stack) {
        super.processEquippedStack(stack);
    }
    // Управления анимациями
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllersRegistrar) {
        controllersRegistrar.add(new AnimationController<>(this,"controller",0,this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
        if(tAnimationState.isMoving()) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.move.new", Animation.LoopType.LOOP));
        }
        else {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.idle", Animation.LoopType.LOOP));
        }
        return PlayState.CONTINUE;
    }


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

}
