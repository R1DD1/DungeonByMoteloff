package ru.cristalix.tycoon.items

import dev.implario.bukkit.item.itemBuilder
import org.bukkit.inventory.ItemStack
import org.bukkit.Material
import org.bukkit.entity.Player
import ru.cristalix.tycoon.utils.NBT
import kotlin.random.Random

enum class Swords(private var itemStack: ItemStack) {
    DEFAULT_SWORD(
        itemBuilder {
            type = Material.STONE_SWORD
            text("Меч новичка")
            nbt(
                mapOf(
                    "sword" to true,
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
    IGNITE_SWORD(
        itemBuilder {
            type = Material.IRON_SWORD
            text("Меч пламени")
            nbt(
                mapOf(
                    "sword" to true,
                    "damage" to 20,
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

    fun get(): ItemStack{ return itemStack.clone() }
}