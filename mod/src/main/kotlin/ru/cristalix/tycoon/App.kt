import ru.cristalix.clientapi.KotlinMod
import ru.cristalix.tycoon.Abilities
import ru.cristalix.tycoon.EntityManager
import ru.cristalix.tycoon.KeyLogger
import ru.cristalix.tycoon.Preparing
import ru.cristalix.uiengine.UIEngine


lateinit var mod: App

class App : KotlinMod() {

    override fun onEnable() {
        mod = this
        UIEngine.initialize(this)

        BarManager()
        KeyLogger()
        Abilities()
        Preparing()
        EntityManager()
    }
}
