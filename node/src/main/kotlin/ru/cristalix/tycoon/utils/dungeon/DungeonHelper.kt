package ru.cristalix.tycoon.utils.dungeon

import me.func.mod.selection.Button
import me.func.mod.selection.button
import me.func.mod.selection.selection
import org.bukkit.Material
import org.bukkit.entity.Player
import ru.cristalix.tycoon.app
import ru.cristalix.tycoon.elements.Dungeon
import java.util.UUID

object DungeonHelper {

    private var dungeons: MutableMap<UUID, Dungeon>? = null
    val unactiveDungeons: MutableList<Dungeon>? = null

    fun getDungeon(uuid: UUID): Dungeon? { return dungeons?.get(uuid) }

    fun createDungeon(player: Player, rank: String): Dungeon{
        val dungeon = Dungeon(UUID.randomUUID(), player, rank, app.getSpawn().clone(),
            mutableListOf(player), false, DungeonType.values().random() )

        player.world.getBlockAt(100, 100, 100).type = Material.BEDROCK
//        if (dungeons == null) { dungeons = mutableMapOf(dungeon.uuid to dungeon) } else dungeons?.set(dungeon.uuid to dungeon)
        return dungeon
    }

    fun findEmptyDungeons() {
        dungeons?.values?.forEach { dungeon ->
            if (!(dungeon.gameIsOn)) { unactiveDungeons!!.add(dungeon) }
        }
    }
}