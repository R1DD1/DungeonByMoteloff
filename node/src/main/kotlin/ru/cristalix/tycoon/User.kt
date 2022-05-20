package ru.cristalix.tycoon

import org.bukkit.entity.Player
import ru.kdev.simulatorapi.common.SimulatorUser
import java.util.*

class User(id: UUID) : SimulatorUser(id) {

    @Transient
    var player: Player? = null
}