package com.aquatictyphoon.pokemonmod.setup.entities.misc;

import com.aquatictyphoon.pokemonmod.setup.entities.pokemon.PokemonEntity;
import com.aquatictyphoon.pokemonmod.setup.entities.registration.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

import java.util.Objects;
import java.util.UUID;


import static com.aquatictyphoon.pokemonmod.setup.entities.registration.Registration.POKEBALL;
import static com.aquatictyphoon.pokemonmod.setup.entities.registration.EntityTypeInit.POKE_BALL;


public class Pokeball_Entity extends ThrowableItemProjectile {


    public Pokeball_Entity(EntityType<Pokeball_Entity> type, Level level) {
        super(type, level);
    }

    public Pokeball_Entity(LivingEntity entity, Level level, InteractionHand pHand) {
        super(POKE_BALL.get(), entity, level);
        this.setOwner(entity);
    }

    private static final ItemStack PokeballItem = ItemStack.EMPTY;

    protected @NotNull Item getDefaultItem() {
        return (POKEBALL.get());
    }

    public boolean containsEntity(@Nonnull ItemStack PokeballItem) {
        if (!PokeballItem.hasTag()) return false;
        assert PokeballItem.getTag() != null;
        return PokeballItem.getTag().contains("entity");
    }

    public Entity getEntityFromNBT(@Nonnull ItemStack PokeballItem, Level world, boolean withInfo) {
        assert PokeballItem.getTag() != null;
        Entity entity = Objects.requireNonNull(ForgeRegistries.ENTITIES.getValue(new ResourceLocation(PokeballItem.getTag().getString("entity")))).create(world);
        if (withInfo) {
            assert entity != null;
            entity.load(PokeballItem.getTag());
        }
        return entity;
    }



    protected void onHitEntity(EntityHitResult result) {
        Entity target = result.getEntity();
        if (result.getType() == EntityHitResult.Type.ENTITY && (!(result.getEntity() instanceof Player)) && ((result.getEntity() instanceof PokemonEntity))){
            if (!level.isClientSide) {
                if ((containsEntity(PokeballItem)) && (target.isAlive() && ((PokemonEntity) target).isTame()) && !(target.getPersistentData().getDouble("UniqueID") == PokeballItem.getOrCreateTag().getDouble("UniqueID"))) {
                    Entity entity = getEntityFromNBT(PokeballItem, target.level, true);
                    BlockPos blockPos = this.blockPosition();
                    entity.absMoveTo(blockPos.getX(), blockPos.getY(), BlockPos.getZ(+1), 0, 0);
                    level.addFreshEntity(entity);
                    //System.out.println("RELEASE SUCCESS!");
                }
                if ((!containsEntity(PokeballItem)) && (target.isAlive() && !((PokemonEntity) target).isTame()))
                {
                    Player player = (Player) this.getOwner();
                    if(player == null){
                        return;
                    }
                    ((PokemonEntity) target).tame((Player) this.getOwner());
                    CompoundTag nbt = new CompoundTag();
                    String entityID = EntityType.getKey(target.getType()).toString();
                    UUID TargetUUID = target.getUUID();
                    String Name = (target.getPersistentData().getString("Nickname"));
                    String Species = (target.getPersistentData().getString("Species"));

                    nbt.putString("UUID", String.valueOf(TargetUUID));
                    nbt.putString("entity", entityID);
                    nbt.putString("id", EntityType.getKey(target.getType()).toString());
                    nbt.putString("Nickname", Name);
                    nbt.putString("Species", Species);
                    target.save(nbt);
                    ItemStack owned_ball = new ItemStack(Registration.POKEBALL.get());
                    owned_ball.setTag(nbt);
                    owned_ball.setCount(1);
                    ItemHandlerHelper.giveItemToPlayer(player, owned_ball);
                    target.discard();
                    //System.out.println("CAPTURE SUCCESS!");
                }
                //Retrieval
                if((containsEntity(PokeballItem)) && (target.isAlive() && ((PokemonEntity) target).isTame()) && (String.valueOf(target.getUUID()).equals(String.valueOf(PokeballItem.getOrCreateTag().getUUID("UUID")))))
                {
                    //System.out.println("RECAPTURE SUCCESS!");
                    Player player = (Player) this.getOwner();
                    if(player == null){
                        return;
                    }
                    String Species = (target.getPersistentData().getString("Species"));
                    String Name = String.valueOf(target.getName());
                    CompoundTag nbt = new CompoundTag();
                    String entityID = EntityType.getKey(target.getType()).toString();
                    UUID TargetUUID = target.getUUID();
                    nbt.putString("UUID", String.valueOf(TargetUUID));
                    nbt.putString("entity", entityID);
                    nbt.putString("id", EntityType.getKey(target.getType()).toString());
                    nbt.putString("Nickname", Name);
                    nbt.putString("Species", Species);

                    target.save(nbt);
                    PokeballItem.setTag(nbt);
                    this.discard();
                    target.discard();
                }
            }
        }
    }



    protected void onHitBlock(@NotNull BlockHitResult result) {
        if (!level.isClientSide) {
            BlockPos blockPos = this.blockPosition();
            Player player = (Player) this.getOwner();
            if (player == null) {
                return;
            }
            Entity entity = getEntityFromNBT(PokeballItem, player.level, true);
            if (containsEntity(PokeballItem)) {
                entity.absMoveTo(blockPos.getX(), blockPos.getY(), blockPos.getZ(), 0, 0);
                level.addFreshEntity(entity);
                //System.out.println("RELEASE SUCCESS!");
                this.discard();
            } else{
                this.discard();
            }
        }
    }
}
