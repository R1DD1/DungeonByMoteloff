package ru.cristalix.tycoon


import clepto.bukkit.B
import clepto.bukkit.B.plugin
import dev.implario.bukkit.platform.Platforms
import dev.implario.bukkit.world.Label
import dev.implario.games5e.sdk.cristalix.WorldMeta
import dev.implario.platform.impl.darkpaper.PlatformDarkPaper
import me.func.mod.Anime
import me.func.mod.Kit
import me.func.mod.conversation.ModLoader
import me.func.mod.conversation.ModTransfer
import me.func.mod.util.after
import me.func.mod.util.command
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import ru.cristalix.core.CoreApi
import ru.cristalix.core.realm.IRealmService
import ru.cristalix.core.realm.RealmStatus
import ru.cristalix.core.scoreboard.IScoreboardService
import ru.cristalix.core.scoreboard.ScoreboardService
import ru.cristalix.core.transfer.ITransferService
import ru.cristalix.core.transfer.TransferService
import ru.cristalix.tycoon.Classes.Healer
import ru.cristalix.tycoon.Classes.Swordman
import ru.cristalix.tycoon.Classes.Tank
import ru.cristalix.tycoon.events.Events
import ru.cristalix.tycoon.events.ItemAbilities
import ru.cristalix.tycoon.events.JoinEvent
import ru.cristalix.tycoon.events.MobListener
import ru.cristalix.tycoon.npc.LobbyNpc
import ru.cristalix.tycoon.utils.dungeon.DungeonHelper
import ru.cristalix.tycoon.utils.maploader.MapLoader
import ru.cristalix.tycoon.utils.selections.DungeonSelection
import java.util.UUID

const val SIMULATOR_ID = "DungSim"
lateinit var app: App


class App : JavaPlugin() {

    lateinit var lobby : WorldMeta
    lateinit var maps : WorldMeta
    val playerToClass = mutableMapOf<Player, String>()

    override fun onEnable() {
        app = this
        plugin = app

        Platforms.set(PlatformDarkPaper())

        CoreApi.get().run {
            registerService(ITransferService::class.java, TransferService(socketClient))
            registerService(IScoreboardService::class.java, ScoreboardService())
        }

        B.events(JoinEvent(), Events(), MobListener(), ItemAbilities())

        Anime.include(Kit.STANDARD, Kit.NPC, Kit.EXPERIMENTAL, Kit.LOOTBOX, Kit.DIALOG, Kit.DEBUG)
        ModLoader.loadAll("mods")
        ModLoader.onJoining("mod-bundle-1.0.jar")


//        createSimulator<App, User> {
//            id = SIMULATOR_ID
//            plugin = this@App
//
//            levelFormula { ((sqrt(5.0) * sqrt((this * 80 + 5).toDouble()) + 5) / 20).toInt() }
//
//            expFormula { this * this - this / 2 }
//
//            userCreator { uuid -> User(uuid) }
//        }

        IRealmService.get().currentRealmInfo.apply {
            IScoreboardService.get().serverStatusBoard.displayName = "§fСимулятор подземелья§b"}.run {
            readableName = "DungSim"
            groupName = "DungSim "
            status = RealmStatus.GAME_STARTED_CAN_JOIN
            isLobbyServer = false
        }

        lobby = MapLoader.load("DungeonSim", "lobby")!!
        maps = MapLoader.load("DungeonSim", "dungs")!!

        LobbyNpc

        command("dungeons") { sender, _ -> DungeonSelection.selection.open(sender) }
        command("create_dungeon") { sender, _ ->
            DungeonHelper.createDungeon(sender, "S")
            ModTransfer().boolean(true).send("show-preparing", sender)
            ModTransfer().string(sender.playerListName).send("user-connected", sender)
            ModTransfer().boolean(true).send("show-start-btn", sender)
        }

        command("bars") { sender, _ -> ModTransfer().boolean(true).send("enable-bars", sender) }
        command("ability") { sender, _ -> ModTransfer().boolean(true).send("enable-abilities", sender) }

        val reload = mutableMapOf<Player, Boolean>()

        Anime.createReader("btn:leave") { player, _ ->
            player.sendMessage("Пака")
        }

        Anime.createReader("btn:start") { player, _ ->
            player.sendMessage("Запускаю")
        }

        Anime.createReader("key_f") { player, _ ->
            ModTransfer().send("reload-start", player)
            after(19 * 20) {
                reload[player] = false
                ModTransfer().send("reload-end", player)

            }

            if (!(reload.containsKey(player))) { reload.set(player, false) }
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

    }
    fun getSpawn(): Label = lobby.getLabel("spawn")


}