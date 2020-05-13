package com.example.game_1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_playergp_home.*


class Playergphome : AppCompatActivity() {
    companion object {
        val countFood = FactoryCurrency.create(currencytype = TypeCurrency.Food, sizeoncreate = 10)
        val countBauern = FactoryPopulation.create(TypePopulation.Bauer, 10)
        var countPopulation = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playergp_home)

        val prefs = getSharedPreferences("test", Context.MODE_PRIVATE)
        val editor = prefs.edit()


        hideSystemUI(window)
        loadGame(prefs)


        btnNextDay.setOnClickListener{
         calcNextDay()
         saveGame(editor)
        }

        btnBerufe.setOnClickListener{

            val intent = Intent(this,Playergpberufe::class.java)
            startActivity(intent)
        }
        screenRefresh()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        screenRefresh()
    }

    private fun calcNextDay(){
       countPopulation = countBauern.size
        if(countFood is Currencys.Food){
           countFood.increaseBy(countBauern.size)
            //countFood.useFood(countPopulation)
        }
        screenRefresh()
    }

    private fun screenRefresh(){
        idFoodcount.text = countFood.size.toString()
        idPopcount.text = countBauern.size.toString()
    }

    private fun saveGame(editor:SharedPreferences.Editor){
        editor.putInt(countBauern.name, countBauern.size)
        editor.putInt(countFood.name, countFood.size)
        editor.apply()
    }

    private fun loadGame(prefs:SharedPreferences){
        countBauern.size = prefs.getInt(countBauern.name, 17)
        countFood.size = prefs.getInt(countFood.name,17)
    }


}
