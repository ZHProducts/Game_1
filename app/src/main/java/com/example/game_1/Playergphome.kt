package com.example.game_1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_playergp_home.*
import java.io.File


class Playergphome : AppCompatActivity() {
    companion object {
        val countFood = FactoryCurrency.create(currencytype = TypeCurrency.Food, sizeoncreate = 3)
        val countGold = FactoryCurrency.create(currencytype = TypeCurrency.Gold, sizeoncreate = 0)

        val countBauern = FactoryPopulation.create(TypePopulation.Bauer, sizeoncreate = 1, upkeepfood = 0, buildingCost = 2)
        val countHaendler = FactoryPopulation.create(TypePopulation.Haendler,sizeoncreate = 1, upkeepfood = 1, buildingCost = 10)

        var countPopulation = 0
    }

    var MySave = "SaveData"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playergp_home)

        val prefs = getSharedPreferences(MySave, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        val f = File("/data/data/$packageName/shared_prefs/$MySave.xml")


        hideSystemUI(window)

        if(f.exists()){
        loadGame(prefs)}

        btnNextDay.setOnClickListener{
         calcNextDay()
         saveGame(editor)
        }

        btnBerufe.setOnClickListener{
            val intent = Intent(this,Playergpberufe::class.java)
            startActivity(intent)
        }

        btnReset.setOnClickListener(){
            resetGame(editor)
        }

        screenRefresh()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        screenRefresh()
    }

    private fun calcNextDay(){

        recalcPopulationSize()

        countFood.increaseBy(countBauern.size * 2)
        countGold.increaseBy(countHaendler.size)

        if(countFood is Currencys.Food) {
            countFood.useFood(getUpkeepcost())
        }


        screenRefresh()
    }

    private fun screenRefresh(){
        idFoodcount.text = countFood.size.toString()
        idPopcount.text = (countBauern.size + countHaendler.size).toString()
        idGoldcount.text = countGold.size.toString()
    }

    private fun saveGame(editor:SharedPreferences.Editor){
        editor.putInt(countBauern.name, countBauern.size)
        editor.putInt(countFood.name, countFood.size)
        editor.putInt(countHaendler.name, countHaendler.size)
        editor.apply()
    }

    private fun loadGame(prefs:SharedPreferences){
        countBauern.size = prefs.getInt(countBauern.name, countBauern.size)
        countFood.size = prefs.getInt(countFood.name, countFood.size)
        countHaendler.size = prefs.getInt(countHaendler.name, countHaendler.size)
    }

    private fun resetGame(editor:SharedPreferences.Editor)
    {
        editor.clear()
        editor.apply()
    }

    private fun getUpkeepcost():Int{
        val upkeepBauern = countBauern.size * countBauern.upkeepFood
        val upkeepHaender = countHaendler.size * countHaendler.upkeepFood
        val rv = upkeepBauern + upkeepHaender
        return rv
    }

    private fun recalcPopulationSize(){
        countPopulation = countBauern.size + countHaendler.size
    }


}
