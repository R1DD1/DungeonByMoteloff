package ru.cristalix.tycoon.events

import me.func.mod.Anime
import me.func.mod.conversation.ModTransfer
import me.func.mod.selection.button
import me.func.mod.selection.choicer
import me.func.mod.selection.selection
import me.func.mod.util.after
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import ru.cristalix.tycoon.Classes.Healer
import ru.cristalix.tycoon.Classes.Swordman
import ru.cristalix.tycoon.Classes.Tank
import ru.cristalix.tycoon.app
import ru.cristalix.tycoon.items.Armor
import ru.cristalix.tycoon.items.Swords
import ru.cristalix.tycoon.utils.NBT
import ru.cristalix.tycoon.utils.dungeon.DungeonHelper
import ru.cristalix.tycoon.utils.mobs.MobHelper
import ru.cristalix.tycoon.utils.mobs.MobType


class JoinEvent : Listener {



    @EventHandler
    fun PlayerJoinEvent.handle(){

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
                    texture = "minecraft:textures/items/shield.png"
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

        player.teleport(app.getSpawn())
        after(20){
            MobHelper.spawnMob(MobType.WEAKNESS_ZOMBIE, app.getSpawn())
            player.inventory.clear()


            player.equipment.chestplate = Armor.DEFAULT_ARMOR_C.get()
            player.equipment.leggings = Armor.DEFAULT_ARMOR_L.get()
            player.equipment.boots = Armor.DEFAULT_ARMOR_B.get()
            player.equipment.helmet = Armor.DEFAULT_ARMOR_H.get()

            Swords.values().forEach { item ->
                player.inventory.addItem(item.get())
            }

            choicer.open(player)

            player.maxHealth = 300.0

        }
    }

}