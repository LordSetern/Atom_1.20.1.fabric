package com.lset.atom.mixin;

import com.lset.atom.common.entity.ModEntities;
import com.lset.atom.common.entity.customplayer.CustomPlayerAttributes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin {

    @Inject(method = "render(Lnet/minecraft/client/network/AbstractClientPlayerEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At("HEAD"), cancellable = true)
    public void renderPlayerAsCow(AbstractClientPlayerEntity player, float yaw, float tickDelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, CallbackInfo info) {

        // Отключение стандартного рендера игрока
        info.cancel();

        // Создание сущности коровы в мире игрока
        CustomPlayerAttributes cow = new CustomPlayerAttributes(ModEntities.NOPLAYER, player.getWorld());



        // Синхронизация всех данных игрока с сущностью коровы
        syncEntityWithPlayer(cow, player);

        // Рендеринг коровы вместо игрока
        renderMorph(cow, matrixStack, tickDelta, vertexConsumerProvider, light);
    }

    // Метод для синхронизации данных игрока с коровой
    public void syncEntityWithPlayer(CustomPlayerAttributes cow, AbstractClientPlayerEntity player) {
        //Поворот головы Влево-право
        cow.prevHeadYaw = player.prevHeadYaw;
        cow.headYaw = player.getHeadYaw();
        //Поворот головы Вверх-Вниз
        cow.prevPitch = player.prevPitch;
        cow.setPitch(player.getPitch());
        //Поворот тела
        cow.prevBodyYaw = player.prevBodyYaw;
        cow.bodyYaw = player.bodyYaw;
        //Управление анимациями

    }

    // Метод для рендера коровы
    public void renderMorph(Entity entityToRender, MatrixStack matrixStack, float tickDelta, VertexConsumerProvider vertexConsumerProvider, int light) {
        matrixStack.push();
        EntityRenderer<? super Entity> entityRenderer = MinecraftClient.getInstance().getEntityRenderDispatcher().getRenderer(entityToRender);
        entityRenderer.render(entityToRender, 0, tickDelta, matrixStack, vertexConsumerProvider, light);
        matrixStack.pop();
    }
}
