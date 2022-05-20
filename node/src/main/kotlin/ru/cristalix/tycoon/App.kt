package ru.cristalix.tycoon

import dev.implario.bukkit.platform.Platforms
import dev.implario.bukkit.world.Label
import dev.implario.games5e.node.CoordinatorClient
import dev.implario.games5e.node.NoopGameNode
import dev.implario.games5e.sdk.cristalix.MapLoader
import dev.implario.games5e.sdk.cristalix.WorldMeta
import dev.implario.platform.impl.darkpaper.PlatformDarkPaper
import me.func.mod.Anime
import me.func.mod.Kit
import me.func.mod.conversation.ModLoader
import org.bukkit.plugin.java.JavaPlugin
import ru.cristalix.core.CoreApi
import ru.cristalix.core.inventory.IInventoryService
import ru.cristalix.core.inventory.InventoryService
import ru.cristalix.core.network.ISocketClient
import ru.cristalix.core.party.IPartyService
import ru.cristalix.core.party.PartyService
import ru.cristalix.core.realm.IRealmService
import ru.cristalix.core.realm.RealmStatus
import ru.cristalix.core.transfer.ITransferService
import ru.cristalix.core.transfer.TransferService
import ru.kdev.simulatorapi.createSimulator
import ru.kdev.simulatorapi.listener.SessionListener
import kotlin.math.sqrt

lateinit var app: App
const val SIMULATOR_ID = "tycoon"

class App : JavaPlugin() {

    private val client = CoordinatorClient(NoopGameNode())
    val map = WorldMeta(MapLoader.load("func", "tower"))
    val spawn: Label = map.getLabel("spawn").apply { yaw = -90f }

    override fun onEnable() {
        app = this

        Anime.include(Kit.STANDARD, Kit.NPC, Kit.EXPERIMENTAL, Kit.LOOTBOX, Kit.DIALOG)
        ModLoader.onJoining("mod-bundle")

        createSimulator<App, User> {
            id = SIMULATOR_ID
            plugin = this@App

            levelFormula { ((sqrt(5.0) * sqrt((this * 80 + 5).toDouble()) + 5) / 20).toInt() }

            expFormula { this * this - this / 2 }

            userCreator { uuid -> User(uuid) }
        }

        CoreApi.get().apply {
            registerService(ITransferService::class.java, TransferService(this.socketClient))
            registerService(IPartyService::class.java, PartyService(ISocketClient.get()))
            registerService(IInventoryService::class.java, InventoryService())
        }

        Platforms.set(PlatformDarkPaper())

        // Конфигурация реалма
        IRealmService.get().currentRealmInfo.apply {
            status = RealmStatus.WAITING_FOR_PLAYERS
            isLobbyServer = true
            readableName = "Название режима"
            groupName = "Группа режима"
        }

        Runtime.getRuntime().addShutdownHook(Thread { SessionListener.simulator.disable() })
    }

    override fun onDisable() = SessionListener.simulator.disable()

    private fun formula(number: Int): Int = (number * number - number) / 4
}