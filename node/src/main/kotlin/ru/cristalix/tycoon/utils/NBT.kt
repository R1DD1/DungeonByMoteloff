package ru.cristalix.tycoon.utils

import net.minecraft.server.v1_12_R1.NBTTagCompound
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import ru.cristalix.tycoon.items.Swords

class NBT(itemStack: ItemStack) {

    private val item = CraftItemStack.asNMSCopy(itemStack)
    private val tag = item.getTag() ?: CraftItemStack.asNMSCopy(Swords.EMPTY.get()).getTag()!!

    fun getString(string: String): String = tag.getString(string)

    fun getInt(string: String): Int = tag.getInt(string)

    fun getDouble(string: String): Double = tag.getDouble(string)

    fun getBoolean(string: String): Boolean = tag.getBoolean(string)

    fun setString(key: String, value: String): NBT {
        tag.setString(key, value)
        item.setTag(tag)
        return this
    }

    fun setInt(key: String, value: Int): NBT {
        tag.setInt(key, value)
        item.setTag(tag)
        return this
    }

    fun setDouble(key: String, value: Double): NBT {
        tag.setDouble(key, value)
        item.setTag(tag)
        return this
    }

    fun setBoolean(key: String, value: Boolean): NBT {
        tag.setBoolean(key, value)
        item.setTag(tag)
        return this
    }

    fun setToMainHand(player: Player) {
        player.inventory.itemInMainHand = item.asBukkitCopy()
    }

    fun setToSlot(player: Player, slot: Int) {
        player.inventory.setItem(slot, item.asBukkitCopy())
    }

    fun contains(string: String): Boolean = tag.hasKey(string)
}

class NBTEntity(private val entity: Entity) {

    fun getString(key: String) = getTag(key) ?: "null"

    fun getInt(key: String) = getTag(key)?.toIntOrNull() ?: 0

    fun getDouble(key: String) = getTag(key)?.toDoubleOrNull() ?: 0.0

    fun getBoolean(key: String) = getTag(key)?.toBooleanStrictOrNull() ?: false

    fun setTag(key: String, value: Any) {
        if (contains(key)) entity.removeScoreboardTag(getTagsByKey(key))
        entity.addScoreboardTag("$key $value")
    }

    fun setTag(map: Map<String, Any>) {
        map.keys.forEach { key ->
            val value = map[key] ?: return@forEach
            setTag(key, value)
        }
    }

    fun removeTag(key: String) {
        entity.removeScoreboardTag(getTagsByKey(key))
    }

    fun contains(key: String) = getTagsByKey(key) != null

    private fun getTag(key: String): String? {
        val tag = getTagsByKey(key) ?: return null
        return tag.split(" ")[1]
    }

    private fun getTagsByKey(key: String): String? {
        entity.scoreboardTags.forEach { tag ->
            if (key == tag.split(" ")[0]) {
                return tag
            }
        }
        return null
    }
}