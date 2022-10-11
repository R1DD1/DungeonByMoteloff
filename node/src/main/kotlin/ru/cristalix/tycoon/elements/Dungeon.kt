package ru.cristalix.tycoon.elements

import me.func.mod.selection.Button
import org.bukkit.Location
import org.bukkit.entity.Player
import ru.cristalix.tycoon.utils.dungeon.DungeonType
import java.util.UUID

class Dungeon(
    var uuid: UUID = UUID.randomUUID(),
    var creator: Player,
    var rank: String = "",
    var location: Location,
    var players: MutableList<Player>,
    var gameIsOn: Boolean,
    var map: DungeonType,
    var buttonIndex: Int
){
    fun playerJoin(player: Player) { players.add(player) }

    fun getPlayersInDung(): MutableList<Player> { return this.players }

    fun dungState(boolean: Boolean) { gameIsOn = boolean }


}