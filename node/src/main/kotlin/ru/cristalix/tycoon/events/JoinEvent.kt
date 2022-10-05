package ru.cristalix.tycoon.events

import me.func.mod.util.after
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import ru.cristalix.tycoon.app


class JoinEvent : Listener {

    @EventHandler
    fun PlayerJoinEvent.handle(){
       after(2){
           player.teleport(app.spawn)
       }
    }

}