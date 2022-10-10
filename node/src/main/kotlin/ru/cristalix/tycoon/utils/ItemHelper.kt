package ru.cristalix.tycoon.utils

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

object ItemHelper {
    fun getChance(NBTTag: String, itemStack: ItemStack): Boolean {
        val chance = NBT(itemStack).getInt(NBTTag)
        if (chance == 999) { return true }
        val randomInt = (1..100).random()
        if (chance >= randomInt) { return true }
        return false
    }

    fun addToInv(player: Player, item: ItemStack){ player.inventory.addItem(item) }
}