package ru.cristalix.tycoon.utils.dungeon

import me.func.mod.conversation.ModTransfer
import me.func.mod.selection.Button
import me.func.mod.selection.button
import me.func.mod.selection.selection
import org.bukkit.Material
import org.bukkit.entity.Player
import ru.cristalix.tycoon.Rank
import ru.cristalix.tycoon.User
import ru.cristalix.tycoon.app
import ru.cristalix.tycoon.elements.Dungeon
import ru.cristalix.tycoon.utils.selections.DungeonSelection
import java.util.UUID

object DungeonHelper {
    var id1 = 1
    val selection = DungeonSelection.selection

    private var dungeons = mutableMapOf<UUID, Dungeon>()
    fun getDungeon(uuid: UUID): Dungeon? { return dungeons[uuid] }

    fun getDungeonByPlayer(player: Player): Dungeon? {
        dungeons.values.forEach { dungeon ->
            if (dungeon.players.contains(player)) { return dungeon }
        }
        return null
    }

    fun dungContainsPlayer(player: Player) : Boolean {
        dungeons.values.forEach { dungeon ->
            if (dungeon.players.contains(player)) { return true }
        }
        return false
    }

    fun createDungeon(player: Player, rank: Rank): Dungeon{
        val dungeon = Dungeon(UUID.randomUUID(), player, rank,
            mutableListOf(player), false, DungeonType.values().random(), id1)

        dungeons[dungeon.uuid] = dungeon

        createButton(player, dungeon)
        id1++
        return dungeon

    }

    private fun createButton(player: Player,dungeon: Dungeon) {
        val button = button {
            //texture = голова игрока
            title = "Подземелье ${dungeon.rank.name} ранга"

            onClick { player, _, _ ->
                if (!(dungContainsPlayer(player))) {
                    ModTransfer().string(player.playerListName).integer(dungeon.idOfPreparing).send("user-connected", player)
                    ModTransfer().integer(dungeon.idOfPreparing).send("show-preparing", player)
                } else { player.sendMessage("Вы уже тут") }
                dungeon.players.add(player)


            }
        }
        selection.add(button)
    }

}