package com.aquatictyphoon.pokemonmod.setup.entities.misc;

import com.aquatictyphoon.pokemonmod.setup.entities.pokemon.PokemonEntity;
import com.aquatictyphoon.pokemonmod.setup.entities.registration.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
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
import java.util.Random;
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
        PokeballItem = entity.getItemInHand(pHand);

        Player player = (Player) this.getOwner();
        if (!(player == null)) {
            Entity pokemonfromball = getEntityFromNBT(PokeballItem, player.level, true);
            UUID fromball = pokemonfromball.getUUID();
            Entity entityinworld = ((ServerLevel) level).getEntity(fromball);

            if (!(entityinworld == null)) {
                String Species = (entityinworld.getPersistentData().getString("Species"));
                String Name = String.valueOf(entityinworld.getName());
                CompoundTag nbt = new CompoundTag();
                String entityID = EntityType.getKey(entityinworld.getType()).toString();
                UUID TargetUUID = entityinworld.getUUID();
                nbt.putString("UUID", String.valueOf(TargetUUID));
                nbt.putString("entity", entityID);
                nbt.putString("id", EntityType.getKey(entityinworld.getType()).toString());
                nbt.putString("Nickname", Name);
                nbt.putString("Species", Species);
                entityinworld.save(nbt);
                PokeballItem.setTag(nbt);
                entityinworld.discard();
                this.discard();
            }
        }

    }

    private static ItemStack PokeballItem = ItemStack.EMPTY;

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
        Entity entity = Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation(PokeballItem.getTag().getString("entity")))).create(world);
        if (withInfo) {
            assert entity != null;
            entity.load(PokeballItem.getTag());
        }
        return entity;
    }



    protected void onHitEntity(EntityHitResult result) {
        Entity target = result.getEntity();
        Random random = new Random();
        int catchrate = ((PokemonEntity) target).getCatchRate();
        if (result.getType() == EntityHitResult.Type.ENTITY && (!(result.getEntity() instanceof Player)) && ((result.getEntity() instanceof PokemonEntity))){
            if (!level.isClientSide) {
                if ((containsEntity(PokeballItem)) && (target.isAlive() && ((PokemonEntity) target).isTame()) && !(target.getPersistentData().getDouble("UniqueID") == PokeballItem.getOrCreateTag().getDouble("UniqueID"))) {
                    Entity entity = getEntityFromNBT(PokeballItem, target.level, true);
                    BlockPos blockPos = this.blockPosition();
                    entity.absMoveTo(blockPos.getX(), blockPos.getY(), BlockPos.getZ(+1), 0, 0);
                    level.addFreshEntity(entity);
                }
                if(PokeballItem.getTag().getFloat("ballBonus") == 0){
                    return;
                }

                float maxHP = ((PokemonEntity)target).getMaxHealth();
                float currentHP = ((PokemonEntity)target).getHealth();
                float ballBonus =  PokeballItem.getTag().getFloat("ballBonus");
                float statusBonus = 1;


                float calculatedCatch = (( ((((3 * (maxHP) ) - 2 *(currentHP)  ) * catchrate * ballBonus) / 3 * maxHP) * statusBonus) / 255);

                System.out.println(calculatedCatch);

                if (!containsEntity(PokeballItem) && (target.isAlive()) && !((PokemonEntity)target).isTame()  && random.nextInt(256) <= calculatedCatch)
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
                    int Nature = (target.getPersistentData().getInt("Nature"));
                    int IvsHp = (target.getPersistentData().getInt("Ivs_Hp"));
                    int IvsAttack = (target.getPersistentData().getInt("Ivs_Attack"));
                    int IvsSpAttack = (target.getPersistentData().getInt("Ivs_SP_Attack"));
                    int IvsDefence = (target.getPersistentData().getInt("Ivs_Defence"));
                    int IvsSpDefence = (target.getPersistentData().getInt("Ivs_SP_Defence"));
                    int IvsSpeed = (target.getPersistentData().getInt("Ivs_Speed"));
                    nbt.putInt("Nature", Nature);
                    nbt.putInt("Ivs_Hp", IvsHp);
                    nbt.putInt("Ivs_Attack", IvsAttack);
                    nbt.putInt("Ivs_SP_Attack", IvsSpAttack);
                    nbt.putInt("Ivs_Defence", IvsDefence);
                    nbt.putInt("Ivs_SP_Defence", IvsSpDefence);
                    nbt.putInt("Ivs_Speed", IvsSpeed);
                    nbt.putString("UUID", String.valueOf(TargetUUID));
                    nbt.putString("entity", entityID);
                    nbt.putString("id", EntityType.getKey(target.getType()).toString());
                    nbt.putString("Nickname", Name);
                    nbt.putString("Species", Species);
                    target.save(nbt);
                    ItemStack owned_ball = new ItemStack(PokeballItem.getItem());
                    owned_ball.setTag(nbt);
                    owned_ball.setCount(1);
                    ItemHandlerHelper.giveItemToPlayer(player, owned_ball);
                    target.discard();
                    //System.out.println("CAPTURE SUCCESS!");
                }
                if(!containsEntity(PokeballItem) && (target.isAlive()) && !((PokemonEntity)target).isTame()  && random.nextInt(256) >= calculatedCatch)        {
                    Player player = (Player) this.getOwner();
                    if(player == null){
                        return;
                    }
                    player.displayClientMessage( Component.translatable("Oh no! The " + ((PokemonEntity) target).getPokeName() + " broke free!"), true);
                    this.discard();
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
                    int Nature = (target.getPersistentData().getInt("Nature"));
                    int IvsHp = (target.getPersistentData().getInt("Ivs_Hp"));
                    int IvsAttack = (target.getPersistentData().getInt("Ivs_Attack"));
                    int IvsSpAttack = (target.getPersistentData().getInt("Ivs_SP_Attack"));
                    int IvsDefence = (target.getPersistentData().getInt("Ivs_Defence"));
                    int IvsSpDefence = (target.getPersistentData().getInt("Ivs_SP_Defence"));
                    int IvsSpeed = (target.getPersistentData().getInt("Ivs_Speed"));
                    nbt.putInt("Nature", Nature);
                    nbt.putInt("Ivs_Hp", IvsHp);
                    nbt.putInt("Ivs_Attack", IvsAttack);
                    nbt.putInt("IvsSP_Attack", IvsSpAttack);
                    nbt.putInt("Ivs_Defence", IvsDefence);
                    nbt.putInt("Ivs_SP_Defence", IvsSpDefence);
                    nbt.putInt("Ivs_Speed", IvsSpeed);

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
            if (containsEntity(PokeballItem)) {
                Player player = (Player) this.getOwner();
                if (player == null) {
                    return;
                }
                Entity pokemonfromball = getEntityFromNBT(PokeballItem, player.level, true);
                UUID fromball = pokemonfromball.getUUID();
                Entity entityinworld = ((ServerLevel) level).getEntity(fromball);
                if (!(entityinworld == null)) {

                    String Species = (entityinworld.getPersistentData().getString("Species"));
                    String Name = String.valueOf(entityinworld.getName());
                    CompoundTag nbt = new CompoundTag();
                    String entityID = EntityType.getKey(entityinworld.getType()).toString();
                    UUID TargetUUID = entityinworld.getUUID();
                    nbt.putString("UUID", String.valueOf(TargetUUID));
                    nbt.putString("entity", entityID);
                    nbt.putString("id", EntityType.getKey(entityinworld.getType()).toString());
                    nbt.putString("Nickname", Name);
                    nbt.putString("Species", Species);
                    int Nature = (entityinworld.getPersistentData().getInt("Nature"));
                    int IvsHp = (entityinworld.getPersistentData().getInt("IvsHp"));
                    int IvsAttack = (entityinworld.getPersistentData().getInt("IvsAttack"));
                    int IvsSpAttack = (entityinworld.getPersistentData().getInt("IvsSpAttack"));
                    int IvsDefence = (entityinworld.getPersistentData().getInt("IvsDefence"));
                    int IvsSpDefence = (entityinworld.getPersistentData().getInt("IvsSpDefence"));
                    int IvsSpeed = (entityinworld.getPersistentData().getInt("IvsSpeed"));
                    nbt.putInt("Nature", Nature);
                    nbt.putInt("Ivs_Hp", IvsHp);
                    nbt.putInt("Ivs_Attack", IvsAttack);
                    nbt.putInt("IvsSP_Attack", IvsSpAttack);
                    nbt.putInt("Ivs_Defence", IvsDefence);
                    nbt.putInt("Ivs_SP_Defence", IvsSpDefence);
                    nbt.putInt("Ivs_Speed", IvsSpeed);
                    entityinworld.save(nbt);
                    PokeballItem.setTag(nbt);
                    entityinworld.discard();
                }else{
                    BlockPos blockPos = this.blockPosition();
                    pokemonfromball.absMoveTo(blockPos.getX(), blockPos.getY(), blockPos.getZ(), 0, 0);
                    level.addFreshEntity(pokemonfromball);
                }
                this.discard();
            }
            else {
                this.discard();
            }
        }
    }

}
