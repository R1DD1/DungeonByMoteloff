package ru.cristalix.tycoon.buildings

import me.func.mod.conversation.ModTransfer
import me.func.mod.util.after
import me.func.unit.Building
import me.func.world.Box
import net.minecraft.server.v1_12_R1.PacketPlayOutEntityDestroy
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import ru.cristalix.tycoon.Rank
import ru.cristalix.tycoon.app
import ru.cristalix.tycoon.npc.LobbyNpc
import ru.cristalix.tycoon.utils.dungeon.DungeonType
import ru.cristalix.tycoon.utils.mobs.MobHelper
import ru.cristalix.tycoon.utils.mobs.MobType
import java.util.*


object BuildingsHelper {

    private val buildings = hashMapOf<Building, ArrayList<Player>>()

    fun create(category: String, map: DungeonType, rank: Rank, vararg players: Player) {

        val building = (Building(UUID.fromString("b7fa1de3-a464-11e8-8374-1cb72caa35fd"), "$category", "", app.maps))
        building.show(*players)
        players.forEach { player -> player.teleport(building.box!!.requireLabel("spawn")) }
        updatePlayersHide(*players)
        val mob = MobHelper.spawnMob(MobType.WEAKNESS_ZOMBIE, building.box!!.requireLabel("zombies")!!)

        after(10) {
            LobbyNpc.EXPLORER.hide(*players)
            hideMob(mob, *players)
        }


    }

    fun updatePlayersHide(vararg players: Player) {
        app.lobby.world.players.forEach { onlinePlayer ->
            players.forEach { dungPlayer ->
                if (!(players.contains(onlinePlayer))) {
                    dungPlayer.hidePlayer(app, onlinePlayer)
                }
            }
        }
    }

    fun placeMobsOnMaps(map: DungeonType, box: Box, rank: Rank) {
        val idToLabel = mapOf(
                0 to "zombies",
                1 to "skeletons",
                2 to "spiders",
                3 to "witch"
        )
        val countsMobs = map.getCountMobs()
        for (i in 0..idToLabel.size) {
            val loc = box.requireLabel(idToLabel[i]!!)

        }
    }

    fun hideMob (mob: Entity, vararg playerInDung: Player) {

        ModTransfer().integer(mob.entityId).send("entity:hide", playerInDung.first())

//        app.lobby.world.players.forEach { player ->
//            if (playerInDung.contains(player)) {
//                player.sendMessage("Моб не убит")
//            } else {  }
//        }
    }
}

