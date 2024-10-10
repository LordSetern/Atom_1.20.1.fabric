package com.lset.atom.common.entity.player;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;


public class CustomEntityTest extends AnimalEntity implements GeoEntity {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    protected CustomEntityTest(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }
    //Атрибуты существа
    public static DefaultAttributeContainer.Builder setAttributes(){
        return AnimalEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH,16.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE,4.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED,2.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED,0.1f);

    }
    //Таргеты на что-либо , чем меньше тем выше приоритет
    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this,1.2D,false));
        this.goalSelector.add(3, new WanderAroundFarGoal(this,0.75F,1));

        this.goalSelector.add(4, new LookAroundGoal(this));

        this.goalSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class,true));
        this.goalSelector.add(2, new ActiveTargetGoal<>(this, MerchantEntity.class,true));
        this.goalSelector.add(3, new ActiveTargetGoal<>(this, ChickenEntity.class,true));
    }
    //принимает в себя ModEntity
    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntity.NOPLAYER.create(world);
    }
    //регистрация контроллера
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this,"controller",0,this::predicate));
    }
    //Применение анимаций
    private PlayState predicate(AnimationState<CustomEntityTest> customEntityTestAnimationState) {
        if(customEntityTestAnimationState.isMoving()){
            customEntityTestAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.move.new",Animation.LoopType.LOOP));
        }
        else {
            customEntityTestAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.idle", Animation.LoopType.LOOP));
        }
        return PlayState.CONTINUE;
    }
    //возвращает анимации
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}