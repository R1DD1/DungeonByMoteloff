package ru.cristalix.tycoon.events

import me.func.mod.util.after
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import ru.cristalix.tycoon.app
import ru.cristalix.tycoon.items.Armor
import ru.cristalix.tycoon.items.Swords
import ru.cristalix.tycoon.utils.selections.ClassChoicer


class JoinEvent : Listener {
    val choicer = ClassChoicer.choicer

    @EventHandler
    fun PlayerJoinEvent.handle(){
        player.teleport(app.getSpawn())
        after(20){
            player.inventory.clear()


            player.equipment.chestplate = Armor.DEFAULT_ARMOR_C.get()
            player.equipment.leggings = Armor.DEFAULT_ARMOR_L.get()
            player.equipment.boots = Armor.DEFAULT_ARMOR_B.get()
//            player.equipment.helmet = Armor.DEFAULT_ARMOR_H.get()

            choicer.open(player)

            Swords.values().forEach { item ->
                player.inventory.addItem(item.get())
            }

            player.maxHealth = 300.0



        }
    }

}