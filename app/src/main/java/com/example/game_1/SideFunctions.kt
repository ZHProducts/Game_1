package com.example.game_1

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.view.View
import android.view.Window
import android.widget.Toast


fun hideSystemUI(window: Window) {
    // Enables regular immersive mode.
    // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
    // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }
}

fun Context.showPlayerMassage(string: String) {
    val toast = Toast.makeText(applicationContext, string, Toast.LENGTH_SHORT)
    toast.show()
}


fun saveGame(editor: SharedPreferences.Editor) {
    editor.putInt(Playergphome.countBauern.name, Playergphome.countBauern.size)
    editor.putInt(Playergphome.countHaendler.name, Playergphome.countHaendler.size)
    editor.putInt(Playergphome.countHolzFaeller.name, Playergphome.countHolzFaeller.size)
    editor.putInt(Playergphome.countRitter.name, Playergphome.countRitter.size)

    editor.putInt(Playergphome.countFood.name, Playergphome.countFood.size)
    editor.putInt(Playergphome.countGold.name, Playergphome.countGold.size)
    editor.putInt(Playergphome.countWood.name, Playergphome.countWood.size)
    editor.putInt("Days", Playergphome.dayCounter)
    editor.putInt("Lager", Playergphome.sizeLager)

    editor.apply()
}

fun loadGame(prefs: SharedPreferences) {
    Playergphome.countBauern.size =
        prefs.getInt(Playergphome.countBauern.name, Playergphome.countBauern.size)
    Playergphome.countHaendler.size =
        prefs.getInt(Playergphome.countHaendler.name, Playergphome.countHaendler.size)
    Playergphome.countHolzFaeller.size =
        prefs.getInt(Playergphome.countHolzFaeller.name, Playergphome.countHolzFaeller.size)
    Playergphome.countRitter.size =
        prefs.getInt(Playergphome.countRitter.name, Playergphome.countRitter.size)

    Playergphome.countFood.size =
        prefs.getInt(Playergphome.countFood.name, Playergphome.countFood.size)
    Playergphome.countGold.size =
        prefs.getInt(Playergphome.countGold.name, Playergphome.countGold.size)
    Playergphome.countWood.size =
        prefs.getInt(Playergphome.countWood.name, Playergphome.countWood.size)

    Playergphome.dayCounter = prefs.getInt("Days", 0)
}

fun resetGame(editor: SharedPreferences.Editor) {
    editor.clear()
    editor.apply()
}
