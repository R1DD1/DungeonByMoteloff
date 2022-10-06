package ru.cristalix.tycoon.utils.dungeon

import ru.cristalix.tycoon.utils.mobs.MobType

enum class DungeonType(private val dungeonBox: String ,private val countMobs: Map<MobType, Int>) {
    TROPIC(
        "tropic-dungeon",
        mapOf(MobType.WEAKNESS_ZOMBIE to 10)
    ),
    SAND(
        "sand-dungeon",
        mapOf(MobType.WEAKNESS_SKELETON to 10)
    );

    fun getDungeonBox(): String { return dungeonBox }
    fun getCountMobs(): Map<MobType, Int> { return countMobs }
}