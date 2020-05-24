package com.example.game_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_playergp_berufe.*

class Playergpberufe : AppCompatActivity() {

    private lateinit var textBauern:String
    private lateinit var textHaendler:String
    private lateinit var textHolzfaeller:String
    private lateinit var textRitter:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playergp_berufe)
        hideSystemUI(window)

        refreshScreen()




        btnreturn.setOnClickListener{
            finish()
        }

        btnBauernincrease.setOnClickListener {
            if (Playergphome.countGold.size >= Playergphome.countBauern.buildingCost){
            Playergphome.countBauern.size++
            refreshScreen()
            Playergphome.countGold.useCurrency(Playergphome.countBauern.buildingCost)
            }
        }

        btnHaendlerincrease.setOnClickListener {
            if (Playergphome.countGold.size >= Playergphome.countHaendler.buildingCost) {
                Playergphome.countHaendler.size++
                refreshScreen()
                Playergphome.countGold.useCurrency(Playergphome.countHaendler.buildingCost)
            }
        }

        btnHolzfaellerincrease.setOnClickListener{
            if(Playergphome.countGold.size >= Playergphome.countHolzFaeller.buildingCost){
                Playergphome.countHolzFaeller.size++
                refreshScreen()
                Playergphome.countGold.useCurrency(Playergphome.countHolzFaeller.buildingCost)
            }
        }
        btnRitterincrease.setOnClickListener{
            if (Playergphome.countGold.size >= Playergphome.countRitter.buildingCost) {
                Playergphome.countRitter.size++
                refreshScreen()
                Playergphome.countGold.useCurrency(Playergphome.countRitter.buildingCost)
            }
        }

    }

    private fun refreshScreen(){
        textBauern = Playergphome.countBauern.size.toString() + " (cost: "+Playergphome.countBauern.buildingCost + ")"
        BerufeShowerBauer.text = textBauern

        textHaendler = Playergphome.countHaendler.size.toString() + " (cost: "+Playergphome.countHaendler.buildingCost + ")"
        BerufeShowerHaendler.text = textHaendler

        textHolzfaeller = Playergphome.countHolzFaeller.size.toString() + " (cost: "+Playergphome.countHolzFaeller.buildingCost + ")"
        BerufeShowerHolzfaeller.text = textHolzfaeller

        textRitter = Playergphome.countRitter.size.toString() + " (cost: "+Playergphome.countRitter.buildingCost + ")"
        BerufeShowerRitter.text = textRitter
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
