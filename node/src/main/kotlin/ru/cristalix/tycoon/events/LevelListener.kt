package ru.cristalix.tycoon.events

import me.func.mod.conversation.ModTransfer
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLevelChangeEvent
import ru.cristalix.tycoon.utils.selections.ClassChoicer

class LevelListener : Listener {

    @EventHandler
    fun PlayerLevelChangeEvent.handle() {
        if (newLevel == 3) {
            ClassChoicer.choicer.open(player)
            ModTransfer().send("unlock-abilities", player)
        }
    }
}