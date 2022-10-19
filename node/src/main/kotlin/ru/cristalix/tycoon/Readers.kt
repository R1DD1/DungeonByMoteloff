package ru.cristalix.tycoon

import me.func.mod.Anime
import me.func.mod.conversation.ModTransfer
import me.func.mod.util.after
import me.func.mod.util.command
import org.bukkit.entity.Player
import ru.cristalix.tycoon.classes.Healer
import ru.cristalix.tycoon.classes.Swordman
import ru.cristalix.tycoon.classes.Tank
import ru.cristalix.tycoon.buildings.BuildingsHelper
import ru.cristalix.tycoon.utils.dungeon.DungeonHelper
import ru.cristalix.tycoon.utils.dungeon.DungeonType
import ru.cristalix.tycoon.utils.selections.DungeonSelection

object Readers {
    val playerToClass = mutableMapOf<Player, String>()
    val reload = mutableMapOf<Player, Boolean>()
    init {
        Anime.createReader("btn:leave") { player, _ ->
            val  dung = DungeonHelper.getDungeonByPlayer(player)
            dung!!.players.remove(player)
            ModTransfer().string(player.playerListName).integer(dung.idOfPreparing).send("user-leave", player)
        }

        Anime.createReader("btn:start") { player, _ ->
            BuildingsHelper.create("testBox", DungeonType.DEFAULT, Rank.B,player)
        }

        Anime.createReader("key_f") { player, _ ->
            ModTransfer().send("reload-start", player)
            after(19 * 20) {
                reload[player] = false
                ModTransfer().send("reload-end", player)

            }

            if (!(reload.containsKey(player))) { reload[player] = false }
            if (reload[player] == false) {
                val keyName = playerToClass[player]
                when(keyName) {
                    Swordman.keyName -> Swordman.unicAbility(player)
                    Tank.keyName -> Tank.unicAbility(player)
                    Healer.keyName -> Healer.unicAbility(player)
                }
                reload[player] = true
            } else { player.sendMessage("Перезарядка") }
        }

//        command("dungeons") { sender, _ -> DungeonSelection.selection.open(sender) }
//        command("create_dungeon") { sender, _ ->
//            if (!(DungeonHelper.dungContainsPlayer(sender))) {
//                val dung = DungeonHelper.createDungeon(sender, Rank.B)
//                ModTransfer().string(Rank.B.name).integer(dung.idOfPreparing).send("create-preparing", sender)
//                ModTransfer().string(sender.playerListName).integer(dung.idOfPreparing).send("user-connected", sender)
//            } else { sender.sendMessage("Ты и так в данже") }
//
//        }

        command("bars") { sender, _ -> ModTransfer().boolean(true).send("enable-bars", sender) }
        command("ability") { sender, _ -> ModTransfer().boolean(true).send("enable-abilities", sender) }


    }
}