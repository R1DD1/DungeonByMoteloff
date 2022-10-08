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
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Zombie
import org.bukkit.plugin.java.JavaPlugin
import ru.cristalix.core.CoreApi
import ru.cristalix.core.realm.IRealmService
import ru.cristalix.core.realm.RealmStatus
import ru.cristalix.core.scoreboard.IScoreboardService
import ru.cristalix.core.scoreboard.ScoreboardService
import ru.cristalix.core.transfer.ITransferService
import ru.cristalix.core.transfer.TransferService
import ru.cristalix.tycoon.events.Events
import ru.cristalix.tycoon.events.JoinEvent
import ru.cristalix.tycoon.events.MobListener
import ru.cristalix.tycoon.utils.NBTEntity
import ru.cristalix.tycoon.utils.mobs.MobHelper
import ru.cristalix.tycoon.utils.mobs.MobType

const val SIMULATOR_ID = "DungSim"
lateinit var app: App


class App : JavaPlugin() {

    lateinit var map : WorldMeta

    override fun onEnable() {
        app = this
        plugin = app

        Platforms.set(PlatformDarkPaper())

        CoreApi.get().run {
            registerService(ITransferService::class.java, TransferService(socketClient))
            registerService(IScoreboardService::class.java, ScoreboardService())
        }

        B.events(JoinEvent(), Events(), MobListener())

        Anime.include(Kit.STANDARD, Kit.NPC, Kit.EXPERIMENTAL, Kit.LOOTBOX, Kit.DIALOG)
        ModLoader.onJoining("mod-bundle-1.0-SNAPSHOT.jar")

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

        map = ru.cristalix.tycoon.utils.maploader.MapLoader.load("DungeonSim", "lobby")!!
//        val mob = MobHelper.spawnMob(MobType.WEAKNESS_SKELETON, getSpawn())

    }
    fun getSpawn(): Label = map.getLabel("spawn")
}