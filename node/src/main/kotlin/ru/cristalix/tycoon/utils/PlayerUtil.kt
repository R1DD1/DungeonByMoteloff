package ru.cristalix.tycoon.utils

import net.minecraft.server.v1_12_R1.ItemStack
import org.bukkit.entity.Player

object PlayerUtil {

    fun getProtection(player: Player): Int{
        var protection = 0
        player.inventory.armorContents.forEach {
            if (it != null) { protection += NBT(it).getInt("protection") }
        }
        return protection
    }

    fun getMaxHp(player: Player) = player.healthScale

    fun getHp(player: Player) = player.health

    fun setHp(player: Player, value: Double ) { player.health = value }
}