package com.example.game_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_playergp_berufe.*

class Playergpberufe : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playergp_berufe)

        HelloBerufe.text = Playergphome.countBauern.size.toString()

        btnreturn.setOnClickListener{
            startActivity(Intent(this,Playergphome::class.java))
        }

        btnBauernincrease.setOnClickListener {
            if (Playergphome.countBauern is Populations.Bauer) {
                Playergphome.countBauern.calcNewDay()
                HelloBerufe.text = Playergphome.countBauern.size.toString()
            }

        }
    }


}
