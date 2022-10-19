package ru.cristalix.tycoon.events

import me.func.mod.util.after
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import ru.cristalix.tycoon.app
import ru.cristalix.tycoon.items.Armor
import ru.cristalix.tycoon.items.Swords
import ru.cristalix.tycoon.npc.LobbyNpc
import ru.cristalix.tycoon.utils.selections.ClassChoicer


class JoinEvent : Listener {
    @EventHandler
    fun PlayerJoinEvent.handle(){
        player.totalExperience = 0
        player.level = 0
        player.teleport(app.getSpawn())
        after(20){
            //Если игрок не существует
//            player.inventory.clear()
            player.inventory.setItem(0, Swords.DEFAULT_SWORD.get())

        }
    }
}