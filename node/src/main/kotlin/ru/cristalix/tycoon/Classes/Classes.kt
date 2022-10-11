package ru.cristalix.tycoon.Classes

import clepto.bukkit.event.EventContext
import me.func.mod.util.after
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent
import ru.cristalix.tycoon.utils.mobs.MobHelper
import java.util.Objects

//open class Classes (
//        ) {
//    fun getHp(): Double = boostHp
//    fun getDamage(): Double = boostDamage
//    fun getSpeed(): Double = boostSpeed
//    fun getTitle(): String = title
//    fun getDesc(): String = desc
//    fun getKeyName(): String = keyName
//

//}

object Swordman  {
     val boostHp: Double = 1.1
     val boostDamage: Double = 1.2
     val boostSpeed: Double = 1.0
     val title: String = "Мечник"
     val desc: String = "тип мечник"
     val keyName: String = "swordman"


    fun unicAbility(player: Player) {
        val radius = 5
        var times = 5
        var multiplier = 1
        while (times > 0) {
            after((20 * multiplier).toLong()) {
                player.getNearbyEntities(5.0, 5.0, 5.0).forEach { entity ->
                    (entity as LivingEntity).damage(5.0)
                    player.world.spawnParticle(Particle.REDSTONE, entity.location.clone().add(0.0, 1.0, 0.0), 5)
                    player.world.spawnParticle(Particle.REDSTONE, entity.location.clone().add(0.0, 2.0, 0.0), 5)
                    player.world.spawnParticle(Particle.REDSTONE, entity.location.clone().add(0.0, 0.0, 0.0), 5)
                }
            }
            times--
            multiplier++
        }
    }
}

object Tank {
    val boostHp: Double = 1.2
    val boostDamage: Double = 1.1
    val boostSpeed: Double = 1.0
    val title: String = "Танк"
    val desc: String = "тип мечник"
    val keyName: String = "tank"


    fun unicAbility(player: Player) {
        val totalDamage = 50
        player.noDamageTicks = 100

        after(100) {
            val startBlock = player.location.add(3.0, 0.0, 3.0)
            player.sendMessage("БАММ")
            for (i in startBlock.blockX downTo startBlock.blockX - 7 ) {
                for (k in startBlock.blockZ downTo startBlock.blockZ - 7){
                    player.world.spawnParticle(Particle.FLAME, Location(player.world, i.toDouble(), startBlock.y+1, k.toDouble()), 3)
                }
            }
            val entities = player.location.getNearbyEntities(3.0, 3.0, 3.0).filter { entity ->  (entity !is Player)}
            val damage = totalDamage/entities.size
            entities.forEach{ entity ->
                (entity as LivingEntity).damage(damage.toDouble())
            }
        }

    }
}

object Healer  {
    val boostHp: Double = 1.2
    val boostDamage: Double = 1.0
    val boostSpeed: Double = 1.2
    val title: String = "Хиллер}"
    val desc: String = "тип мечник"
    val keyName: String = "healer"


    fun unicAbility(player: Player) {
        val heal = 15

        player.location.getNearbyPlayers(5.0).forEach { player ->
            player.health + heal
        }
    }
}