import ru.cristalix.clientapi.KotlinMod
import ru.cristalix.tycoon.*
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
        Blacksmith()
    }
}
