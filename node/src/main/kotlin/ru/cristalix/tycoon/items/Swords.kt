package ru.cristalix.tycoon.items

import dev.implario.bukkit.item.itemBuilder
import org.bukkit.inventory.ItemStack
import org.bukkit.Material
import org.bukkit.entity.Player
import ru.cristalix.tycoon.utils.NBT
import kotlin.random.Random

enum class Swords(private var itemStack: ItemStack) {
    MONEY(
        itemBuilder {
            type = Material.GOLD_INGOT
            text("Монеты")
            nbt(
                mapOf(
                    "money" to true,
                    "count" to 10
                )
            )
            nbt("Unbreakable", 1)
            nbt("HideFlags", 127)
        }.build()
    ),
    BEGINNER_SWORD(
        itemBuilder {
            type = Material.STONE_SWORD
            text("Меч новичка")
            nbt(
                mapOf(
                    "sword" to true,
                    "rank" to "G",
                    "damage" to 10,
                    "critical-chance" to 1,
                    "critical-damage" to 13,
                    "abilities" to "null"
                )
            )
            nbt("Unbreakable", 1)
            nbt("HideFlags", 127)

        }.build()
    ),
    WARRIOR_SWORD(
            itemBuilder {
                type = Material.STONE_SWORD
                text("Меч воина")
                nbt(
                        mapOf(
                                "sword" to true,
                                "rank" to "F",
                                "damage" to 12,
                                "critical-chance" to 1,
                                "critical-damage" to 15,
                                "abilities" to "null"
                        )
                )
                nbt("Unbreakable", 1)
                nbt("HideFlags", 127)

            }.build()
    ),
    GUARDIAN_SWORD(
            itemBuilder {
                type = Material.STONE_SWORD
                text("Меч стражника")
                nbt(
                        mapOf(
                                "sword" to true,
                                "rank" to "E",
                                "damage" to 14,
                                "critical-chance" to 5,
                                "critical-damage" to 16,
                                "abilities" to "null"
                        )
                )
                nbt("Unbreakable", 1)
                nbt("HideFlags", 127)

            }.build()
    ),
    ADVENTURER_SWORD(
            itemBuilder {
                type = Material.STONE_SWORD
                text("Меч авантюриста")
                nbt(
                        mapOf(
                                "sword" to true,
                                "rank" to "D",
                                "damage" to 15,
                                "critical-chance" to 5,
                                "critical-damage" to 17,
                                "abilities" to "null"
                        )
                )
                nbt("Unbreakable", 1)
                nbt("HideFlags", 127)

            }.build()
    ),
    COMMANDER_SWORD(
            itemBuilder {
                type = Material.STONE_SWORD
                text("Меч полководца")
                nbt(
                        mapOf(
                                "sword" to true,
                                "rank" to "C",
                                "damage" to 17,
                                "critical-chance" to 10,
                                "critical-damage" to 18,
                                "abilities" to "null"
                        )
                )
                nbt("Unbreakable", 1)
                nbt("HideFlags", 127)

            }.build()
    ),
    IGNITE_SWORD(
        itemBuilder {
            type = Material.IRON_SWORD
            text("Меч пламени")
            nbt(
                mapOf(
                    "sword" to true,
                    "damage" to 20,
                    "rank" to "A",
                    "critical-chance" to 5,
                    "critical-damage" to 25,
                    "abilities" to "ignite",
                    "abilities-chance" to 5
                )
            )
            nbt("Unbreakable", 1)
            nbt("HideFlags", 127)
        }.build()
    ),
    KNOCK_SWORD(
        itemBuilder {
            type = Material.IRON_SWORD
            text("Меч гравитации")
            nbt(
                mapOf(
                    "sword" to true,
                    "damage" to 20,
                    "rank" to "S",
                    "critical-chance" to 5,
                    "critical-damage" to 25,
                    "abilities" to "knock",
                    "abilities-chance" to 5
                )
            )
            nbt("Unbreakable", 1)
            nbt("HideFlags", 127)
        }.build()
    ),
    TP_SWORD(
        itemBuilder {
            type = Material.IRON_SWORD
            text("Меч Бегуна")
            nbt(
                mapOf(
                    "sword" to true,
                    "damage" to 20,
                    "rank" to "B",
                    "critical-chance" to 5,
                    "critical-damage" to 25,
                    "abilities" to "tp",
                    "abilities-chance" to 999
                )
            )
            nbt("Unbreakable", 1)
            nbt("HideFlags", 127)
        }.build()
    ),
    LIGHTNING_SWORD(
        itemBuilder {
            type = Material.IRON_SWORD
            text("Меч Зевса")
            nbt(
                mapOf(
                    "sword" to true,
                    "damage" to 20,
                    "rank" to "S",
                    "critical-chance" to 10,
                    "critical-damage" to 25,
                    "abilities" to "lightning",
                    "abilities-chance" to 5
                )
            )
            nbt("Unbreakable", 1)
            nbt("HideFlags", 127)
        }.build()
    ),
    EMPTY(
        itemBuilder {
            type = Material.COAL_BLOCK
            nbt("empty", "empty")
        }.build()
    ),;

    fun get(): ItemStack{
        val lore = arrayListOf<String>()
        if (!(NBT(itemStack).contains("money"))) {
            lore.add("Уровень предмета: ${NBT(itemStack).getString("rank")}")
            lore.add("Урон: ${NBT(itemStack).getInt("damage")}")
            lore.add("Крит. урон: ${NBT(itemStack).getInt("critical-damage")}")
            lore.add("Крит. шанс: ${NBT(itemStack).getInt("critical-chance")}%")
            lore.add("Особая способность - ${NBT(itemStack).getString("abilities")}")
            itemStack.lore = lore
        }
        return itemStack.clone()
    }

    val descriptionAbilities = mapOf<String, String>(

    )
}