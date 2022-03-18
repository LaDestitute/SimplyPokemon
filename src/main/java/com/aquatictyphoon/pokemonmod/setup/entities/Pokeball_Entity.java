package com.aquatictyphoon.pokemonmod.setup.entities;

import com.aquatictyphoon.pokemonmod.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

import static com.aquatictyphoon.pokemonmod.setup.Registration.POKEBALL;
import static com.aquatictyphoon.pokemonmod.setup.Registration.POKE_BALL;

public class Pokeball_Entity extends ThrowableItemProjectile {

    public Pokeball_Entity(EntityType<Pokeball_Entity> type, Level level) {
        super(type, level);
    }

    public Pokeball_Entity(LivingEntity entity, Level level, InteractionHand pHand) {
        super(POKE_BALL.get(), entity, level);
        this.setOwner(entity);
       // PokeballItem = entity.getItemInHand(pHand);
    }

    public ItemStack stack = ItemStack.EMPTY;


    protected Item getDefaultItem() {
        return (POKEBALL.get());
    }

    //Helper
    public static boolean containsEntity(@Nonnull ItemStack stack) {
        return stack.hasTag() && stack.getTag().contains("entity");
    }

    public static Entity getEntityFromStack(ItemStack stack, Level world, boolean withInfo) {
        Entity entity = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(stack.getTag().getString("entity"))).create(world);
        if (withInfo) entity.load(stack.getTag());
        return entity;
    }


    protected void onHitEntity(EntityHitResult result)
    {
        Entity target = result.getEntity();
        if (result.getType() == EntityHitResult.Type.ENTITY && (!(result.getEntity() instanceof Player))) {
            if (!level.isClientSide) {
                Player player = (Player) this.getOwner();
                if(containsEntity(stack)) {
                    Entity entity = getEntityFromStack(stack, player.level, true);
                    entity.absMoveTo(this.getX(), this.getY(), this.getZ(), 0, 0);
                    stack.setTag(null);
                    player.level.addFreshEntity(entity);
                }
                if(!containsEntity(stack))
                {
                    CompoundTag nbt = new CompoundTag();
                    String entityID = EntityType.getKey(target.getType()).toString();
                    nbt.putString("entity", entityID);
                    nbt.putString("id", EntityType.getKey(target.getType()).toString());
                    target.save(nbt);
                    ItemStack owned_ball = new ItemStack(Registration.POKEBALL.get());
                    owned_ball.setTag(nbt);
                    ItemEntity ball_drop = new ItemEntity(target.level, target.getX(), target.getY(), target.getZ(), owned_ball);
                    level.addFreshEntity(ball_drop);
                    target.discard();

                }
            }
        }
    }


    protected void onHitBlock(@NotNull BlockHitResult result) {
        Player player = (Player) this.getOwner();
        if (player == null)
        {
            return;
        }
        InteractionHand hand = InteractionHand.MAIN_HAND;
        ItemStack stack = player.getMainHandItem();
        if (player.getCommandSenderWorld().isClientSide || !containsEntity(stack))
        {
            return;
        }
        Entity entity = getEntityFromStack(stack, player.level, true);
        BlockPos blockPos = this.blockPosition();
        entity.absMoveTo(blockPos.getX(), blockPos.getY(), blockPos.getZ(), 0, 0);
        stack.setTag(null);
        player.setItemInHand(hand, stack);
        player.level.addFreshEntity(entity);
        if (!level.isClientSide) {
            this.discard();
        }
    }

    public void addAdditionalSaveData(CompoundTag p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);
        if (!stack.isEmpty()) {
            p_213281_1_.put("pokeball", stack.save(stack.getOrCreateTag()));
        }

    }

    public void readAdditionalSaveData(CompoundTag p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
        stack = ItemStack.of(p_70037_1_.getCompound("pokeball"));
    }

}