package ru.cristalix.tycoon

import ru.cristalix.clientapi.JavaMod.clientApi
import io.netty.buffer.Unpooled
import io.netty.util.NetUtil
import mod
import ru.cristalix.uiengine.UIEngine
import ru.cristalix.uiengine.element.CarvedRectangle
import ru.cristalix.uiengine.element.RectangleElement
import ru.cristalix.uiengine.element.TextElement
import ru.cristalix.uiengine.utility.*
import java.util.UUID

class EntityManager {
    fun hideEntity(entityId: Int) {
        clientApi.minecraft().world.removeEntity(clientApi.minecraft().world.getEntity(entityId))
    }

    init {
        mod.registerChannel("entity:hide") {
            val id = readInt()
            hideEntity(id)
        }
    }
}