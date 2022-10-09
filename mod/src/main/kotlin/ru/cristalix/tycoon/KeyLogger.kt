package ru.cristalix.tycoon

import dev.xdark.clientapi.event.input.KeyPress
import io.netty.buffer.Unpooled
import org.lwjgl.input.Keyboard
import ru.cristalix.clientapi.registerHandler
import ru.cristalix.uiengine.UIEngine

class KeyLogger {
    init {
        registerHandler<KeyPress> {
            if (key == Keyboard.KEY_F) {
                UIEngine.clientApi.clientConnection().sendPayload("key_f", Unpooled.buffer())
            }
        }
    }
}