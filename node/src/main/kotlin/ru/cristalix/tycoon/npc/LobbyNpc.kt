package ru.cristalix.tycoon.npc

import me.func.mod.Anime
import me.func.mod.Npc
import me.func.mod.Npc.location
import me.func.mod.Npc.onClick
import me.func.mod.Npc.spawn
import me.func.mod.conversation.ModTransfer
import me.func.mod.data.NpcSmart
import me.func.mod.selection.Confirmation
import me.func.protocol.dialog.*
import me.func.protocol.npc.NpcBehaviour
import org.bukkit.entity.Player
import ru.cristalix.tycoon.app
import ru.cristalix.tycoon.utils.selections.DungeonSelection

enum class LobbyNpc(private val npc: NpcSmart) {
    EXPLORER(
       Npc.npc {
           yaw = 115f
           behaviour = NpcBehaviour.STARE_AT_PLAYER
           location(app.lobby.label("explorerNpc", 0.5, 0.0, 0.5)!!)

           name = "Проводник"

           onClick {
               ModTransfer().boolean(false).send("enable-bars", it.player)
               ModTransfer().boolean(false).send("enable-abilities", it.player)
               DungeonSelection.selection.open(it.player)

           }
       }
    ),
    SMITH(
    Npc.npc {
        yaw = 115f
        behaviour = NpcBehaviour.STARE_AT_PLAYER
        location(app.lobby.label("explorerNpc", 3.0, 0.0, 0.5)!!)

        name = "Кузнец"

        onClick {
            ModTransfer().send("show-smith", it.player)
            }
        }
    ),
    ADVENTURER(
            Npc.npc {
                yaw = 115f
                behaviour = NpcBehaviour.STARE_AT_PLAYER
                location(app.lobby.label("explorerNpc", -3.0, 0.0, 0.5)!!)

                name = "Авантюрист"

                val menu = Confirmation("Найти собственное подземелье за", " 300 монет") { player ->
                    if (!(ru.cristalix.tycoon.utils.dungeon.DungeonHelper.dungContainsPlayer(player))) {
                        val dung = ru.cristalix.tycoon.utils.dungeon.DungeonHelper.createDungeon(player, ru.cristalix.tycoon.Rank.B)
                        ModTransfer().string(ru.cristalix.tycoon.Rank.B.name).integer(dung.idOfPreparing).send("create-preparing", player)
                        ModTransfer().string(player.playerListName).integer(dung.idOfPreparing).send("user-connected", player)
                    } else { player.sendMessage("Ты и так в данже") }
                }

                onClick {
                    menu.open(it.player)
                }
            }
    );

    fun hide(vararg players: Player) {
        players.forEach { player ->
            npc.hide(player)
        }
    }
    fun show(vararg players: Player) {
        players.forEach { player ->
            npc.show(player)
        }
    }
}
