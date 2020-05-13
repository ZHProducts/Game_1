package com.example.game_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_playergp_berufe.*

class Playergpberufe : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playergp_berufe)
        hideSystemUI(window)
        HelloBerufe.text = Playergphome.countBauern.size.toString()

        btnreturn.setOnClickListener{
            finish()
        }

        btnBauernincrease.setOnClickListener {
            if (Playergphome.countBauern is Populations.Bauer) {
                Playergphome.countBauern.calcNewDay()
                HelloBerufe.text = Playergphome.countBauern.size.toString()
            }

        }
    }


}
