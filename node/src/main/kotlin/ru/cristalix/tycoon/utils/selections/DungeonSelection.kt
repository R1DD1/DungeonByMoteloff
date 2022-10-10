package ru.cristalix.tycoon.utils.selections

import me.func.mod.selection.Selection
import me.func.mod.selection.selection

object DungeonSelection {
    val selection = selection {
        title = "Подземелья"
        rows = 3
        columns = 3
        hint = "Подключится"
        buttons()
    }
}