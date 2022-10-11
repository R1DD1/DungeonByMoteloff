package ru.cristalix.tycoon.utils.selections

import me.func.mod.Anime
import me.func.mod.selection.button
import me.func.mod.selection.choicer
import me.func.mod.selection.selection
import ru.cristalix.tycoon.Classes.Healer
import ru.cristalix.tycoon.Classes.Swordman
import ru.cristalix.tycoon.Classes.Tank
import ru.cristalix.tycoon.app

object ClassChoicer {
    val choicer = selection {
        title = "Выбор класса"

        buttons(
            button{
                title = Swordman.title
                description = Swordman.desc
                hint = "Выбрать"
                texture = "minecraft:textures/items/iron_sword.png"
                onClick { player, _, _ ->
                    app.playerToClass[player] = Swordman.keyName
                    Anime.close(player)
                }
            },
            button{
                title = Tank.title
                description = Tank.desc
                hint = "Выбрать"
                texture = "minecraft:textures/items/totem.png"
                onClick { player, _, _ ->
                    app.playerToClass[player] = Tank.keyName
                    Anime.close(player)
                }
            },
            button{
                title = Healer.title
                description = Healer.desc
                hint = "Выбрать"
                texture = "minecraft:textures/items/emerald.png"
                onClick { player, _, _ ->
                    app.playerToClass[player] = Healer.keyName
                    Anime.close(player)
                }
            }
        )
    }

}