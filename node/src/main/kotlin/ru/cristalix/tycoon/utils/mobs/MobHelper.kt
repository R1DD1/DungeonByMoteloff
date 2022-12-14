package ru.cristalix.tycoon.utils.mobs

import net.minecraft.server.v1_12_R1.AttributeModifier
import net.minecraft.server.v1_12_R1.GenericAttributes
import net.minecraft.server.v1_12_R1.PacketPlayOutEntityDestroy
import net.minecraft.server.v1_12_R1.PacketPlayOutSpawnEntityLiving
import org.bukkit.Bukkit
import org.bukkit.Bukkit.getEntity
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftLivingEntity
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import ru.cristalix.tycoon.utils.NBT
import ru.cristalix.tycoon.utils.NBTEntity
import java.util.*


object MobHelper {

    fun spawnMob(mob: MobType, location: Location): LivingEntity {
        val spawningMob = location.world.spawnEntity(location, mob.getType()) as LivingEntity

        val list = mob.getEquipment()
        spawningMob.equipment.chestplate = list[0]
        spawningMob.equipment.leggings = list[1]
        spawningMob.equipment.boots = list[2]

        NBTEntity(spawningMob).setTag("health", mob.getHp())
        NBTEntity(spawningMob).setTag("damage", mob.getDamage())
        NBTEntity(spawningMob).setTag("xp", mob.getXp())

        setMovementSpeed(spawningMob, mob.getSpeed(), 1)
        setDefaultDamage(spawningMob, 0.0, 0)

        spawningMob.customName = NBTEntity(spawningMob).getInt("health").toString()
        spawningMob.isCustomNameVisible = true

        return spawningMob
    }

    fun getProtection(entity: Entity): Int{
        var protection = 0
        if (entity !is Player){
            (entity as LivingEntity).equipment.armorContents.forEach { armor ->
                if (NBT(armor).contains("protection")) { protection += NBT(armor).getInt("protection") }
            }
        }
        return protection
    }

    fun setMovementSpeed(entity: LivingEntity, var1: Double, var2: Int){
        val nmsEntity = (entity as CraftLivingEntity).handle
        val attributes = nmsEntity.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED)
        val modifier = AttributeModifier(entity.uniqueId, "movement speed multiplier", var1, var2)
        attributes.b(modifier)
    }

    fun setDefaultDamage(entity: LivingEntity, var1: Double, var2: Int){
        val nmsEntity = (entity as CraftLivingEntity).handle
        val attributes = nmsEntity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE)
        val modifier = AttributeModifier(entity.uniqueId, "attack damage multiplier", var1, var2)
        attributes.b(modifier)
    }

    fun changeHp(entity: LivingEntity, damage: Int, clearDamage: Int, killer: Player){
        if (!(entity.isDead)) {
            var finalDamage = damage - getProtection(entity)
            if (finalDamage < 0)  finalDamage = 0
            if (entity !is Player){
                entity.customName = (entity.customName.toInt() - finalDamage).toString()
                entity.customName = (entity.customName.toInt() - clearDamage).toString()
                if (entity.customName.toInt() <= 0) {
                    killMob(entity.uniqueId, killer)
                    entity.world.spawnParticle(Particle.REDSTONE, entity.location.add(0.0, 1.5, 0.0), 5)
                    entity.world.spawnParticle(Particle.REDSTONE, entity.location.add(0.0, 1.0, 0.0), 5)
                }
            }
        }
    }

    fun killMob(uuid: UUID, killer: Player) {
        getEntity(uuid).remove()
        killer.exp + NBTEntity(getEntity(uuid)).getInt("xp")
    }

//    fun hideEntity(entity: LivingEntity) {
//        for (on in Bukkit.getServer().onlinePlayers) {
//            val packet = PacketPlayOutSpawnEntityLiving
//            (on as CraftPlayer).handle.playerConnection.sendPacket(packet)
//        }
//    }

//    fun checkAliveMobs(world: World): Boolean {
//        world.entities.forEach { entity ->
//            if (entity !is Player) return false
//        return true
//    }
}

