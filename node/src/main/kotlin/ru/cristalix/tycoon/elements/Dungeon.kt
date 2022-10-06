package ru.cristalix.tycoon.elements

import org.bukkit.entity.Player
import java.util.UUID

class Dungeon(
    var uuid: UUID = UUID.randomUUID(),
    var creator: Player? = null,
    var rank: String? = "",
    var x: Double = 100.0
)