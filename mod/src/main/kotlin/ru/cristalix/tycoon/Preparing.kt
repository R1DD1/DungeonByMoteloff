package ru.cristalix.tycoon

import io.netty.buffer.Unpooled
import io.netty.util.NetUtil
import mod
import ru.cristalix.uiengine.UIEngine
import ru.cristalix.uiengine.element.CarvedRectangle
import ru.cristalix.uiengine.element.RectangleElement
import ru.cristalix.uiengine.element.TextElement
import ru.cristalix.uiengine.utility.*
import java.util.UUID

class Preparing {
    var curY: Int = -55
//    val preparingMenu: PreparingMenu = PreparingMenu()
    val preparings = mutableListOf<PreparingMenu>()

    private val leaveButton = carved {
        align = BOTTOM
        origin = BOTTOM
        size = V3(35.0, 10.0)
        carveSize = 1.0
        offset.y += -10
        offset.x += -20
        color = Color(250, 0, 0)
        onLeftClick {
            UIEngine.clientApi.clientConnection().sendPayload("btn:leave", Unpooled.EMPTY_BUFFER)
        }
    }
    private val leaveButtonText = text {
        content = "Покинуть"
        origin = CENTER
        align = CENTER
        shadow = true
        scale = V3(0.6, 0.6)

    }

    private val startButton = carved {
        align = BOTTOM
        origin = BOTTOM
        size = V3(35.0, 10.0)
        enabled = false
        carveSize = 1.0
        offset.y += -10
        offset.x += 30
        color = Color(10, 230, 10)
        onLeftClick {
            UIEngine.clientApi.clientConnection().sendPayload("btn:start", Unpooled.EMPTY_BUFFER)
        }


    }
    private val startButtonText = text {
        content = "Начать"
        origin = CENTER
        align = CENTER
        shadow = true
        scale = V3(0.6, 0.6)

    }

    private val title = text {
        content = "Подземелье"
        align = TOP
        origin = TOP
        offset.y += 10
        offset.x += 3
    }
    private val users = text {
        content = "Участники:"
        offset.x += -40
        offset.y += 35
        align = TOP
        origin = TOP_LEFT
        scale = V3(0.8, 0.8)
    }

    init {
//        mod.registerChannel("show-preparing") {
//            val status = readBoolean()
//            val uuid = dev.xdark.feder.NetUtil.readUtf8(this)
//            getPreparingByUUID(UUID.fromString(uuid))!!.enabled = status
//            display(getPreparingByUUID(UUID.fromString(uuid))!!)
//        }

//        mod.registerChannel("show-start-btn") {
//            val status = readBoolean()
//            val uuid = dev.xdark.feder.NetUtil.readUtf8(this)
//            startButton.enabled = status
//        }
        mod.registerChannel("user-leave") {
            val name = dev.xdark.feder.NetUtil.readUtf8(this)
            val id = readInt()
            getPreparingById(id).players.remove(name)
            UIEngine.overlayContext.removeChild(getPreparingById(id))
            addUser(getPreparingById(id).players).forEach { element ->
                getPreparingById(id).addChild(element)
            }
        }

        mod.registerChannel("hide-preparing") {
            val status = readBoolean()
            val id = readInt()
            getPreparingById(id)
            getPreparingById(id).enabled = status
        }

        mod.registerChannel("user-connected") {
            val name = dev.xdark.feder.NetUtil.readUtf8(this)
            val id = readInt()
            getPreparingById(id).players.add(name)
            addUser(getPreparingById(id).players).forEach { element ->
                getPreparingById(id).addChild(element)
            }
//            display(getPreparingById(id))
        }

        mod.registerChannel("show-preparing") {
            val id = readInt()
            startButton.enabled = false
            UIEngine.overlayContext + getPreparingById(id)
        }

        mod.registerChannel("create-preparing") {
            val id = readInt()
            val preparing = PreparingMenu(id, )
            preparings.add(preparing)
            preparing.enabled = true
            startButton.enabled = true
            display(preparing)
        }

    }

    private fun getPreparingById(id: Int): PreparingMenu {
        preparings.forEach { preparingMenu ->
            if (preparingMenu.id == id) { return preparingMenu }
        }
        return preparings.first()
    }

    private fun display(preparingMenu: PreparingMenu) {
        startButton.addChild(startButtonText)
        leaveButton.addChild(leaveButtonText)
        preparingMenu.addChild(title, users, leaveButton, startButton)

        UIEngine.overlayContext + preparingMenu
    }

    private fun addUser(players: List<String>): MutableList<TextElement> {

        val cords = listOf(-55, -40, -25, -10, 5)
        val textElements = mutableListOf<TextElement>()
        players.indices.forEach {
            val text = text {
                content = players[it]
                offset = V3(30.0, 0.0)
                offset.y += cords[it]
                align = Relative.LEFT
                origin = Relative.TOP_LEFT
                scale = V3(1.0, 1.0)
            }
            textElements.add(text)
        }

        curY += 15
        return textElements
    }
}

class PreparingMenu(
    var id: Int = 0,
    var players: MutableList<String> = mutableListOf()
) : CarvedRectangle() {
    init {
        color.alpha = 1.0
        align = LEFT
        origin = CENTER
        enabled = false
        size = V3(120.0, 200.0)
        offset.y = 0.0
        offset.x += 50
        carveSize = 2.0
        color = Color(0, 0, 0, .7)
    }
}