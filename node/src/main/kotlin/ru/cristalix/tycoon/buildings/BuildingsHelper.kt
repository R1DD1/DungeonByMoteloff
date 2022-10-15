package ru.cristalix.tycoon.buildings

import me.func.MetaWorld
import me.func.unit.Building
import net.minecraft.server.v1_12_R1.PacketPlayOutBlockChange
import org.bukkit.Location
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer
import org.bukkit.entity.Player
import ru.cristalix.tycoon.app
import ru.cristalix.tycoon.npc.LobbyNpc
import ru.cristalix.tycoon.utils.mobs.MobHelper
import ru.cristalix.tycoon.utils.mobs.MobType
import java.util.*
import kotlin.collections.ArrayList

object BuildingsHelper {

    private val buildings = hashMapOf<Building, ArrayList<Player>>()

    fun create(category: String, vararg players: Player) {
        val building = (Building(UUID.fromString("b7fa1de3-a464-11e8-8374-1cb72caa35fd"), "testBox", "", app.maps).apply {
            allocate(app.lobby.label("dung")!!.clone().add(50.0, 0.0, 0.0))
//            MobHelper.spawnMob(MobType.WEAKNESS_ZOMBIE, box!!.requireLabel("mob")!!)
            show(*players)
        })
        players.forEach { player ->
//            player.teleport(app.maps.getLabel("dung"))
        }

        updatePlayersHide(*players)
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
}

