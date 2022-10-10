package ru.cristalix.tycoon.items

import dev.implario.bukkit.item.itemBuilder
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

enum class Armor(private val itemStack: ItemStack) {
    DEFAULT_ARMOR_C(
      itemBuilder {
          type = org.bukkit.Material.LEATHER_CHESTPLATE
          text("Куртка новичка")
          nbt(
              mapOf(
                  "armor" to true,
                  "chestplate" to true,
                  "protection" to 2
             )
          )
          nbt("Unbreakable", 1)
          nbt("HideFlags", 127)
      }.build()
    ),
    DEFAULT_ARMOR_L(
        itemBuilder {
            type = org.bukkit.Material.LEATHER_LEGGINGS
            text("Штаны новичка")
            nbt(
                mapOf(
                    "armor" to true,
                    "leggins" to true,
                    "protection" to 2
                )
            )
            nbt("Unbreakable", 1)
            nbt("HideFlags", 127)
        }.build()
    ),
    DEFAULT_ARMOR_B(
        itemBuilder {
            type = org.bukkit.Material.LEATHER_BOOTS
            text("Боты новичка")
            nbt(
                mapOf(
                    "armor" to true,
                    "boots" to true,
                    "protection" to 1
                )
            )
            nbt("Unbreakable", 1)
            nbt("HideFlags", 127)
        }.build()
    ),
    DEFAULT_ARMOR_H(
        itemBuilder {
            type = org.bukkit.Material.LEATHER_BOOTS
            text("Шляпа новичка")
            nbt(
                mapOf(
                    "armor" to true,
                    "helmet" to true,
                    "protection" to 1
                )
            )
            nbt("Unbreakable", 1)
            nbt("HideFlags", 127)
        }.build()
    );
    fun get(): ItemStack{ return itemStack }
}