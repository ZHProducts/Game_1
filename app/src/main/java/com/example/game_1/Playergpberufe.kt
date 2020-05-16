package com.example.game_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_playergp_berufe.*

class Playergpberufe : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playergp_berufe)
        hideSystemUI(window)

        val textBauern = Playergphome.countBauern.size.toString() + " (cost: "+Playergphome.countBauern.buildingCost + ")"
        BerufeShowerBauer.text = textBauern

        val textHaendler = Playergphome.countHaendler.size.toString() + " (cost: "+Playergphome.countHaendler.buildingCost + ")"
        BerufeShowerHaendler.text = textHaendler

        val textRitter = Playergphome.countRitter.size.toString() + " (cost: "+Playergphome.countRitter.buildingCost + ")"
        BerufeShowerRitter.text = textRitter


        btnreturn.setOnClickListener{
            finish()
        }

        btnBauernincrease.setOnClickListener {
            if (Playergphome.countGold.size >= Playergphome.countBauern.buildingCost){
            Playergphome.countBauern.size++
            BerufeShowerBauer.text = Playergphome.countBauern.size.toString()
            Playergphome.countGold.useCurrency(Playergphome.countBauern.buildingCost)
            }
        }

        btnHaendlerincrease.setOnClickListener {
            if (Playergphome.countGold.size >= Playergphome.countHaendler.buildingCost) {
                Playergphome.countHaendler.size++
                BerufeShowerHaendler.text = Playergphome.countHaendler.size.toString()
                Playergphome.countGold.useCurrency(Playergphome.countHaendler.buildingCost)
            }
        }

        btnRitterincrease.setOnClickListener{
            if (Playergphome.countGold.size >= Playergphome.countRitter.buildingCost) {
                Playergphome.countRitter.size++
                BerufeShowerRitter.text = Playergphome.countRitter.size.toString()
                Playergphome.countGold.useCurrency(Playergphome.countRitter.buildingCost)
            }
        }

    }


    @Suppress("UNUSED_PARAMETER")
    fun showPrev(view: View) {
        BerufeViewFlipper.showPrevious()
    }


    @Suppress("UNUSED_PARAMETER")
    fun showNext(view:View){
        BerufeViewFlipper.showNext()
    }
}
