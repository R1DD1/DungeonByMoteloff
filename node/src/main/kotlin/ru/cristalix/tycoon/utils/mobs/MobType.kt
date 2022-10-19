package ru.cristalix.tycoon.utils.mobs

import dev.implario.bukkit.item.item
import org.bukkit.entity.EntityType
import org.bukkit.inventory.ItemStack
import ru.cristalix.tycoon.items.Armor

enum class MobType(private val type: EntityType, private val hp: Int,private val damage: Int,private val equipment: List<ItemStack>,private val visibleName: String, private val xp: Double, private val speed: Double) {
    WEAKNESS_ZOMBIE(
        EntityType.ZOMBIE,
        1000,
        10,
        listOf(Armor.DEFAULT_ARMOR_C.get(), Armor.DEFAULT_ARMOR_L.get(), Armor.DEFAULT_ARMOR_B.get()),
        "Охраник руин",
        0.07,
        0.4

    ),
    WEAKNESS_SKELETON(
        EntityType.SKELETON,
        90,
        20,
        listOf(Armor.DEFAULT_ARMOR_C.get(), Armor.DEFAULT_ARMOR_L.get(), Armor.DEFAULT_ARMOR_B.get()),
        "Стрелок",
        0.07,
        0.4
    ),
    WEAKNESS_SPIDER(
            EntityType.SPIDER,
            130,
            12,
            listOf(),
            "Павук",
            0.07,
            0.4
    ),
    WEAKNESS_WITCH(
            EntityType.WITCH,
            80,
            25,
            listOf(),
            "Гадалка",
            0.07,
            0.4
    );

    fun getType(): EntityType = type
    fun getHp(): Int = hp
    fun getDamage(): Int = damage
    fun getEquipment(): List<ItemStack> = equipment
    fun getVisibleName(): String = visibleName
    fun getXp(): Double = xp
    fun getSpeed(): Double = speed

}



