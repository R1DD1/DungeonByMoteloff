package ru.cristalix.tycoon.events

import me.func.mod.util.after
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.util.Vector
import ru.cristalix.tycoon.items.Swords
import ru.cristalix.tycoon.utils.ItemHelper
import ru.cristalix.tycoon.utils.NBT
import ru.cristalix.tycoon.utils.mobs.MobHelper

class ItemAbilities : Listener{

    val cooldownTp = mutableMapOf<Player, Boolean>()

    @EventHandler(priority = EventPriority.HIGHEST)
    fun EntityDamageByEntityEvent.handle() {
        if (damager is Player){
            val player = damager as Player
            val item = player.inventory.itemInMainHand
            if (NBT(item).contains("abilities")) {
                when(NBT(item).getString("abilities")) {
                    "ignite" -> {
                        if (ItemHelper.getChance("abilities-chance", item)) {
                            entity.fireTicks = 80

                            var times = 4
                            var multiplier = 1
                            while (times > 0) {
                                after((20 * multiplier).toLong()) {
                                    MobHelper.changeHp(entity as LivingEntity, 0, 3, player)
                                }
                                multiplier++
                                times--
                            }
                        }
                    }
                    "knock" -> {
                        if (ItemHelper.getChance("abilities-chance", item)) {
                            (entity as LivingEntity).velocity = Vector(entity.velocity.x, 5.0, entity.velocity.z).normalize()
                            after(40) { MobHelper.changeHp(entity as LivingEntity, 0, 10, player) }
                        }
                    }
                    "lightning" -> {
                        if (ItemHelper.getChance("abilities-chance", item)) {
                            player.world.strikeLightningEffect(entity.location)
                            after(20) { MobHelper.changeHp(entity as LivingEntity, 0, 15, player) }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    fun PlayerInteractEvent.handle() {
        val item = player.inventory.itemInMainHand
        if (NBT(item).contains("abilities")) {
            if (action == Action.RIGHT_CLICK_AIR) {
                when(NBT(item).getString("abilities")) {
                    "tp" -> {
                        if (cooldownTp[player] == null) { cooldownTp[player] = false }
                        if (cooldownTp[player] == false) {
                            val direction = player.location.direction
                            direction.normalize()
                            direction.multiply(5)
                            val loc = player.location.clone().add(direction)
                            player.teleport(loc)
                            cooldownTp[player] = true
                            after(15 * 20) { cooldownTp[player] = false }
                        }
                    }
                }
            }
        }
    }
}
