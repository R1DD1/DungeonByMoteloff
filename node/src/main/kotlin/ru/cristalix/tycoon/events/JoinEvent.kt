package ru.cristalix.tycoon.events

import me.func.mod.util.after
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import ru.cristalix.tycoon.app
import ru.cristalix.tycoon.utils.dungeon.DungeonHelper


class JoinEvent : Listener {

    @EventHandler
    fun PlayerJoinEvent.handle(){
        player.teleport(app.getSpawn())
        after(10){
            DungeonHelper.createDungeon(player, "S")
        }
    }

}