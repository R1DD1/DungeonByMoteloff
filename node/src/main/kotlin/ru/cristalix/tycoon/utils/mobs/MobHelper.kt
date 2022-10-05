package ru.cristalix.tycoon.utils.mobs

import dev.implario.bukkit.world.Box
import me.func.mod.util.after
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.inventory.EntityEquipment
import java.util.UUID


object MobHelper {
    fun spawnMob(mob: MobType, location: Location) {
        val spawningMob = location.world.spawnEntity(location, mob.getType())
        spawningMob.customName = "${mob.getVisibleName()}  ${mob.getHp()}/${mob.getHp()}"
        spawningMob.isCustomNameVisible = true
    }

    fun killMob(uuid: UUID, world: World) = world.getEntity(uuid).remove()

    fun checkAliveMobs(world: World): Boolean {
        world.entities.forEach { entity ->
            if (entity is Player) {  } else { return true }
        }
        return true
    }
}

