package ru.cristalix.tycoon.events

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLevelChangeEvent

class LevelListener : Listener {

    @EventHandler
    fun PlayerLevelChangeEvent.handle() {
        if (newLevel == 2) {  }
    }
}