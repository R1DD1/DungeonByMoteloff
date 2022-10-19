package ru.cristalix.tycoon


import clepto.bukkit.B
import clepto.bukkit.B.plugin
import dev.implario.bukkit.platform.Platforms
import dev.implario.platform.impl.darkpaper.PlatformDarkPaper
import me.func.MetaWorld
import me.func.builder.MetaSubscriber
import me.func.mod.Anime
import me.func.mod.Kit
import me.func.mod.conversation.ModLoader
import me.func.unit.Building
import me.func.world.MapLoader
import me.func.world.WorldMeta
import net.minecraft.server.v1_12_R1.Block
import net.minecraft.server.v1_12_R1.BlockPosition
import net.minecraft.server.v1_12_R1.PacketPlayOutBlockChange
import org.bukkit.Location
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer
import org.bukkit.plugin.java.JavaPlugin
import ru.cristalix.core.CoreApi
import ru.cristalix.core.realm.IRealmService
import ru.cristalix.core.realm.RealmStatus
import ru.cristalix.core.scoreboard.IScoreboardService
import ru.cristalix.core.scoreboard.ScoreboardService
import ru.cristalix.core.transfer.ITransferService
import ru.cristalix.core.transfer.TransferService
import ru.cristalix.tycoon.events.*
import ru.cristalix.tycoon.npc.LobbyNpc
import ru.cristalix.tycoon.npc.Npc
import java.util.*

const val SIMULATOR_ID = "DungSim"
lateinit var app: App


class App : JavaPlugin() {

    lateinit var lobby : WorldMeta
    lateinit var maps : WorldMeta

    var buildings = mutableListOf<Building>()


    override fun onEnable() {
        app = this
        plugin = app

        Platforms.set(PlatformDarkPaper())

        CoreApi.get().run {
            registerService(ITransferService::class.java, TransferService(socketClient))
            registerService(IScoreboardService::class.java, ScoreboardService())
        }

        B.events(JoinEvent(), Events(), MobListener(), ItemAbilities(), LevelListener())

        lobby = MapLoader.load("DungeonSim", "lobby")
        maps = MapLoader.load("DungeonSim", "dungs")

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


        Npc
        Readers

        MetaWorld.universe(
            lobby.world, *MetaSubscriber()
                .buildingLoader { buildings }
                .customModifier { chunk ->
                    chunk.modify(
                        BlockPosition(chunk.chunk.locX * 16, 0, chunk.chunk.locZ * 16),
                        Block.getById(9).getBlockData()
                    )
                }.build()
        )

    }
    fun getSpawn(): me.func.world.Label {
        return lobby.label("spawn", 0.5, 0.0, 0.5)!!
    }
//    fun create(): List<Building> {
//        if (buildings.isEmpty())
//            buildings.add(Building(UUID.fromString("b7fa1de3-a464-11e8-8374-1cb72caa35fd"), "test", "red", lobby).apply {
//                allocate(Location(lobby.world, 0.0, 91.0, 0.0))
//            }.onClick { player, packetPlayInUseItem -> player.sendMessage("Привет папаша!") }
//                .onBreak { player, packetPlayInBlockDig -> (player as CraftPlayer).handle.playerConnection.sendPacket(
//                    PacketPlayOutBlockChange().apply {
//                    a = packetPlayInBlockDig.a
//                    block = MetaWorld.storage[player.uniqueId]!!.buildings[0].allocation!!.blocks?.get(a)!!
//                }) })
//        return buildings
//    }



}