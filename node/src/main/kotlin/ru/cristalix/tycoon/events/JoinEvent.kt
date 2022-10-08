package ru.cristalix.tycoon.events

import me.func.mod.util.after
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import ru.cristalix.tycoon.app
import ru.cristalix.tycoon.items.Armor
import ru.cristalix.tycoon.items.Swords
import ru.cristalix.tycoon.utils.NBT
import ru.cristalix.tycoon.utils.dungeon.DungeonHelper
import ru.cristalix.tycoon.utils.mobs.MobHelper
import ru.cristalix.tycoon.utils.mobs.MobType


class JoinEvent : Listener {

    @EventHandler
    fun PlayerJoinEvent.handle(){
        player.teleport(app.getSpawn())
        after(20){
            MobHelper.spawnMob(MobType.WEAKNESS_ZOMBIE, app.getSpawn())
            player.inventory.clear()

            val item = Swords.DEFAULT_SWORD.get()
            player.equipment.chestplate = Armor.DEFAULT_ARMOR_C.get()
            player.equipment.leggings = Armor.DEFAULT_ARMOR_L.get()
            player.equipment.boots = Armor.DEFAULT_ARMOR_B.get()
            player.equipment.helmet = Armor.DEFAULT_ARMOR_H.get()
            player.inventory.addItem(item)
        }
    }

}