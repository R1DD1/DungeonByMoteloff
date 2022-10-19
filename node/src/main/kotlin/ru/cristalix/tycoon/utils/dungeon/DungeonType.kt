package ru.cristalix.tycoon.utils.dungeon

import ru.cristalix.tycoon.utils.mobs.MobType

enum class DungeonType(private val dungeonBox: String, private val countMobs: List<Int>) {
    TROPIC(
        "tropic-dungeon",
        listOf(10, 10, 10, 10)
    ),
    SAND(
        "sand-dungeon",
        listOf(10, 10, 10, 10)
    ),
    DEFAULT(
            "sand-dungeon",
            listOf(10, 10, 10, 10)
    ),
    ICE(
            "sand-dungeon",
            listOf(10, 10, 10, 10)
    ),
    HELL(
            "sand-dungeon",
            listOf(10, 10, 10, 10)
    ),
    END(
            "sand-dungeon",
            listOf(10, 10, 10, 10)
    );

    fun getDungeonBox(): String { return dungeonBox }
    fun getCountMobs(): List<Int> { return countMobs }
}