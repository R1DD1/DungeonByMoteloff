package ru.cristalix.tycoon.utils.dungeon

import me.func.mod.selection.Button
import me.func.mod.selection.button
import me.func.mod.selection.selection
import org.bukkit.Material
import org.bukkit.entity.Player
import ru.cristalix.tycoon.app
import ru.cristalix.tycoon.elements.Dungeon
import ru.cristalix.tycoon.utils.selections.DungeonSelection
import java.util.UUID

object DungeonHelper {
    val selection = DungeonSelection.selection

    val dungeonToButton = mutableMapOf<Dungeon, Button>()
    private var dungeons = mutableMapOf<UUID, Dungeon>()
    fun getDungeon(uuid: UUID): Dungeon? { return dungeons[uuid]
    }

    fun createDungeon(player: Player, rank: String): Dungeon{
        val dungeon = Dungeon(UUID.randomUUID(), player, rank, app.getSpawn().clone(),
            mutableListOf(player), false, DungeonType.values().random() )

        val desc = " "
        dungeon.players.forEach {
            desc + it.playerListName
            player.sendMessage(it.playerListName)
        }

        player.sendMessage(desc)

        createButton(dungeon, desc)
        selection.open(player)
        return dungeon
    }

    private fun createButton(dungeon: Dungeon, desc: String) {
        val button = button {
            title = "Подземелье ${dungeon.rank} ранга"
            description = desc

            onClick { player, _, _ -> player.teleport(dungeon.location) }
        }
        selection.add(button)
    }

}