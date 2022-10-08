package ru.cristalix.tycoon.items

import dev.implario.bukkit.item.itemBuilder
import org.bukkit.inventory.ItemStack
import org.bukkit.Material
import org.bukkit.entity.Player

enum class Swords(private var itemStack: ItemStack) {
    DEFAULT_SWORD(
        itemBuilder {
            type = Material.STONE_SWORD
            text("Меч новичка")
            nbt(
                mapOf(
                    "sword" to true,
                    "damage" to 10,
                    "critical-chance" to 0.1,
                    "critical-damage" to 13,
                    "abilities" to "null"
                )
            )
            nbt("Unbreakable", 1)
            nbt("HideFlags", 127)
            nbt("display", "test")
        }.build()
    ),
    IGNITE_SWORD(
        itemBuilder {
            type = Material.STONE_SWORD
            text("Меч пламени")
            nbt(
                mapOf(
                    "sword" to true,
                    "damage" to 20,
                    "abilities" to "ignite"
                )
            )
        }.build()
    ),
    KNOCK_SWORD(
        itemBuilder {
            type = Material.STONE_SWORD
            text("Меч новичка")
            nbt(
                mapOf(
                    "sword" to true,
                    "damage" to 20,
                    "abilities" to "knock"
                )
            )
        }.build()
    ),
    EMPTY(
        itemBuilder {
            type = Material.COAL_BLOCK
            nbt("empty", "empty")
        }.build()
    ),;

    fun get(): ItemStack{ return itemStack.clone() }
    fun addToInv(player: Player){ player.inventory.addItem(itemStack.clone()) }
}