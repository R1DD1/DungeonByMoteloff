package ru.cristalix.tycoon.utils.selections

import me.func.mod.Anime
import me.func.mod.selection.button
import me.func.mod.selection.selection
import ru.cristalix.tycoon.classes.Healer
import ru.cristalix.tycoon.classes.Swordman
import ru.cristalix.tycoon.classes.Tank
import ru.cristalix.tycoon.Readers

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
                    Readers.playerToClass[player] = Swordman.keyName
                    Anime.close(player)
                }
            },
            button{
                title = Tank.title
                description = Tank.desc
                hint = "Выбрать"
                texture = "minecraft:textures/items/totem.png"
                onClick { player, _, _ ->
                    Readers.playerToClass[player] = Tank.keyName
                    Anime.close(player)
                }
            },
            button{
                title = Healer.title
                description = Healer.desc
                hint = "Выбрать"
                texture = "minecraft:textures/items/emerald.png"
                onClick { player, _, _ ->
                    Readers.playerToClass[player] = Healer.keyName
                    Anime.close(player)
                }
            }
        )
    }

}