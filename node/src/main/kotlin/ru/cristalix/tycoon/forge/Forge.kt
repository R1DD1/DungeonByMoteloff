package ru.cristalix.tycoon.forge

import me.func.mod.util.after
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import ru.cristalix.tycoon.utils.NBT

object Forge {
    private val swordCriteria = listOf("damage", "critical-chance", "critical-damage", "abilities-chance")
    private val armorCriteria = listOf("protection")

    private val cooldown = mutableMapOf<Player, Boolean>()

    fun upgrade(item: ItemStack, player: Player): ItemStack? {
        if (cooldown[player] == null) { cooldown[player] = true }
        if (cooldown[player]!!) {
            val criteria = swordCriteria.random()
            player.sendMessage("бывшая" +(NBT(item).getInt(criteria)).toString())
            player.sendMessage(criteria)
            val value = NBT(item).getInt(criteria).plus(10)
            player.sendMessage("сумма $value")
            NBT(item).setInt(criteria, value)
            player.sendMessage("сейчас" +(NBT(item).getInt(criteria)).toString())
            item.lore = updateLore(item)
            cooldown[player] = false
            after(50) { cooldown[player] = true }
            return item
        }
        return null
    }

    private fun updateLore(item: ItemStack): ArrayList<String> {
        val lore = arrayListOf<String>()
        lore.add("Урон: ${NBT(item).getInt("damage")}")
        lore.add("Крит. урон: ${NBT(item).getInt("critical-damage")}")
        lore.add("Крит. шанс: ${NBT(item).getInt("critical-chance")}")
        lore.add("Особая способность - ${NBT(item).getString("abilities")}")
        return lore
    }
}