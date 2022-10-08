package ru.cristalix.tycoon.elements

import org.bukkit.entity.Player
import ru.cristalix.tycoon.utils.dungeon.DungeonType
import java.util.UUID

class Dungeon(
    var uuid: UUID = UUID.randomUUID(),
    var creator: Player? = null,
    var rank: String? = "",
    var x: Double = 100.0,
    var players: MutableList<Player>? = null,
    var gameIsOn: Boolean = false,
    var map: DungeonType? = null
)