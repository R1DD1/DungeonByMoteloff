package ru.cristalix.tycoon.utils.dungeon

import org.bukkit.Material
import org.bukkit.entity.Player
import ru.cristalix.tycoon.elements.Dungeon
import java.util.UUID

object DungeonHelper {

    private  var dungeons: MutableMap<UUID, Dungeon>? = null

    fun getDungeon(uuid: UUID): Dungeon? { return dungeons?.get(uuid)
    }

    fun createDungeon(player: Player, rank: String): Dungeon{
        val dungeon = Dungeon(UUID.randomUUID(), player, rank)
        player.world.getBlockAt(100, 100, 100).type = Material.BEDROCK
        player.sendMessage(dungeon.x.toString())
//        dungeons.set(dungeon.uuid to dungeon)
        return dungeon
    }
}