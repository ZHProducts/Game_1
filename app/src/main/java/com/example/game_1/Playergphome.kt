package com.example.game_1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_playergp_home.*
import java.io.File
import kotlin.random.Random


class Playergphome : AppCompatActivity() {
    companion object {
        val countFood = FactoryCurrency.create(currencytype = TypeCurrency.Food, sizeoncreate = 3)
        val countGold = FactoryCurrency.create(currencytype = TypeCurrency.Gold, sizeoncreate = 0)

        val countBauern = FactoryPopulation.create(TypePopulation.Bauer, sizeoncreate = 1, upkeepfood = 0, buildingCost = 2)
        val countHaendler = FactoryPopulation.create(TypePopulation.Haendler,sizeoncreate = 1, upkeepfood = 1, buildingCost = 10)
        val countRitter = FactoryPopulation.create(TypePopulation.Ritter, sizeoncreate = 1, upkeepfood = 5, buildingCost = 30)

        var countPopulation = 0
    }

    private var mySave = "SaveData"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playergp_home)

        val prefs = getSharedPreferences(mySave, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        val f = File("$filesDir/$packageName/shared_prefs/$mySave.xml")


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

        btnReset.setOnClickListener{
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

        checkforEncounter()

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
        val upkeepRitter = countRitter.size * countRitter.upkeepFood
        return upkeepBauern + upkeepHaender + upkeepRitter
    }

    private fun recalcPopulationSize(){
        countPopulation = countBauern.size + countHaendler.size + countRitter.size
    }


    private fun checkforEncounter(){
        val rollforEncounter = Random.nextInt(0,100)

        if (rollforEncounter in 1..30){
            goodEncounter(rollforEncounter)
        }
        if (rollforEncounter in 31..40){
            enemyEncounter()
        }


    }

    private fun goodEncounter(rolled:Int){
        val toast:Toast
        var rolledcoins = rolled
        if (rolledcoins in 1..9) {
        toast = Toast.makeText(applicationContext, "Du hast $rolledcoins Bauern gefunden ", Toast.LENGTH_SHORT)
            countBauern.size += rolled
            toast.show()
        }
        else {
            rolledcoins /= 10
        toast = Toast.makeText(applicationContext, "Du hast $rolledcoins Münzen gefunden" , Toast.LENGTH_SHORT)
            countGold.increaseBy(rolledcoins)
            toast.show()
        }


    }

    private fun enemyEncounter(){
        val toast:Toast = Toast.makeText(applicationContext, "EnemyEncounter", Toast.LENGTH_SHORT)
        val intent = Intent(this,Playergpbattle::class.java)
        toast.show()
        startActivity(intent)
    }

}
