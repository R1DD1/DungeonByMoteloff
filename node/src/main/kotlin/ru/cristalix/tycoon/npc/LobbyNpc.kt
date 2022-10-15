package ru.cristalix.tycoon.npc

import me.func.mod.Anime
import me.func.mod.Npc
import me.func.mod.Npc.location
import me.func.mod.Npc.onClick
import me.func.mod.Npc.spawn
import me.func.mod.conversation.ModTransfer
import me.func.mod.data.NpcSmart
import me.func.protocol.dialog.*
import me.func.protocol.npc.NpcBehaviour
import ru.cristalix.tycoon.app

object LobbyNpc {

    init {
        val explorer = Npc.npc {

            yaw = 115f
            behaviour = NpcBehaviour.STARE_AT_PLAYER
            location(app.lobby.label("explorerNpc", 0.5, 0.0, 0.5)!!)

            name = "Проводник"


            onClick {
                ModTransfer().boolean(false).send("enable-bars", it.player)
                ModTransfer().boolean(false).send("enable-abilities", it.player)
                Anime.sendDialog(it.player, explorerDialog)
                Anime.openDialog(it.player, "1")

            }
        }
    }

    private val explorerDialog = Dialog (
        Entrypoint(
            "1",
            "Проводник",
            Screen(
                "Привет путник!", "Тебя что-то интересует ?"
            ).buttons(
                Button("Я хочу попасть в существующее подземелье").actions(Action(Actions.COMMAND).command("/dungeons"),  Action.command("/bars"), Action.command("/ability"), Action(Actions.CLOSE),),
                Button("Я хочу найти собственное подземелье").actions(Action(Actions.COMMAND).command("/create_dungeon"), Action.command("/bars"), Action.command("/ability"), Action(Actions.CLOSE))
            )
        )
    )

}