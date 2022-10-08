package ru.cristalix.tycoon.events

import me.func.mod.conversation.ModTransfer
import org.bukkit.GameMode
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.player.PlayerGameModeChangeEvent
import ru.cristalix.tycoon.utils.NBT
import ru.cristalix.tycoon.utils.NBTEntity
import ru.cristalix.tycoon.utils.PlayerUtil
import ru.cristalix.tycoon.utils.mobs.MobHelper

class MobListener : Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    fun EntityDamageByEntityEvent.handle(){
        var damage: Int

        // Если нападающий игрок
        if (damager is Player) {
            val item = (damager as Player).inventory.itemInMainHand
            if (NBT(item).contains("damage")){
                damage = (NBT(item).getInt("damage"))
                MobHelper.changeHp(entity as LivingEntity, damage, damager as Player)
            }
        }
       if (damager !is Player && damager is LivingEntity){
           if (entity is Player){
               val player = entity as Player
               damage = NBTEntity(damager).getInt("damage")
               val finalDamage = damage - PlayerUtil.getProtection(player)
               if (finalDamage >= PlayerUtil.getHp(player)) {
                   player.gameMode = GameMode.SPECTATOR
                   PlayerUtil.setHp(player, PlayerUtil.getMaxHp(player))
               } else player.health = player.health - finalDamage

           }
       }

    }

    @EventHandler
    fun EntityDeathEvent.handle() { isCancelled = true }

    @EventHandler
    fun PlayerGameModeChangeEvent.handle() {
        if (newGameMode == GameMode.SPECTATOR) { ModTransfer().boolean(false).send("enable-bars", player) }
        if (newGameMode == GameMode.SURVIVAL) { ModTransfer().boolean(true).send("enable-bars", player) }
    }
}