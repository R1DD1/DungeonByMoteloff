package ru.cristalix.tycoon.utils.mobs

import dev.implario.bukkit.item.item
import org.bukkit.entity.EntityType
import org.bukkit.inventory.ItemStack

enum class MobType(private val type: EntityType, private val hp: Int,private val damage: Int,private val equipment: List<ItemStack>,private val visibleName: String) {
    WEAKNESS_ZOMBIE(
        EntityType.ZOMBIE,
        100,
        10,
        listOf(item { type = org.bukkit.Material.LEATHER_CHESTPLATE }),
        "Охраник руин",
    ),
    WEAKNESS_SKELETON(
        EntityType.SKELETON,
        90,
        20,
        listOf(item { type = org.bukkit.Material.LEATHER_CHESTPLATE }),
        "Стрелок"
    );

    fun getType(): EntityType { return type }
    fun getHp(): Int { return hp }
    fun getDamage(): Int { return damage }
    fun getEquipment(): List<ItemStack> { return equipment }
    fun getVisibleName(): String { return visibleName }

}



