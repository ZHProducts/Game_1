package com.example.game_1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_playergp_home.*
import java.io.File
import kotlin.random.Random


class Playergphome : AppCompatActivity() {
    companion object {
        val countFood = FactoryCurrency.create(currencytype = TypeCurrency.Food, sizeoncreate = 10)
        val countGold = FactoryCurrency.create(currencytype = TypeCurrency.Gold, sizeoncreate = 0)
        val countWood = FactoryCurrency.create(TypeCurrency.Wood, 0)

        val countBauern = FactoryPopulation.create(
            TypePopulation.Bauer,
            sizeoncreate = 1,
            upkeepfood = 0,
            buildingCost = 2
        )
        val countHaendler = FactoryPopulation.create(
            TypePopulation.Haendler,
            sizeoncreate = 0,
            upkeepfood = 1,
            buildingCost = 10
        )
        val countHolzFaeller = FactoryPopulation.create(
            TypePopulation.Holzfaeller,
            sizeoncreate = 0,
            upkeepfood = 3,
            buildingCost = 5
        )
        val countRitter = FactoryPopulation.create(
            TypePopulation.Ritter,
            sizeoncreate = 0,
            upkeepfood = 5,
            buildingCost = 30
        )
        var countPopulation = 0
        var dayCounter = 0
        var sizeLager = 20
    }



    private var mySave = "SaveData"
    private var needTutorial = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playergp_home)

        val prefs = getSharedPreferences(mySave, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        val f = File("/data/data/shared_prefs/$mySave.xml")

        hideSystemUI(window)
        //if(f.exists()){
        loadGame(prefs) // Needs to be fixed
        //}
        if (needTutorial && dayCounter == 0) {
            CustomDialog(
                window, "Welcome", "Willkommen, scheinbar ist es dein erster Tag." +
                        "\n Also lass dir kurz deine Aufgabe erklären" +
                        "\n ÜBERLEBE"
            )
                .show(supportFragmentManager, "first day")
            btnBerufe.visibility = View.INVISIBLE
        }

        /* NEXT DAY START*/
        btnNextDay.setOnClickListener {

            dayCounter++
            doEveryNextDay()

            if (dayCounter % 3 == 0)
                doEveryThirdDay()

            screenRefresh()

            saveGame(editor)

            //Quick Fix for anti spam
            btnNextDay.visibility = View.INVISIBLE
            Handler().postDelayed({ btnNextDay.visibility = View.VISIBLE }, 100)
        }
        /*NEXT DAY END*/
        /*BERUFE START*/
        btnBerufe.setOnClickListener {
            val intent = Intent(this, Playergpberufe::class.java)
            startActivity(intent)
        }
        /*BERUFE END*/
        /*RESET START*/
        btnReset.setOnClickListener {
            resetGame(editor)
            CustomDialog(window, "Reset", "Game has been Reset!!").show(
                supportFragmentManager,
                "reset"
            )
        }
        /*RESET END*/

        btnLagerhaus.setOnClickListener{

            if(countWood.size >= 5 )
            {
                countWood.useCurrency(5)
                sizeLager += 5
                showPlayerMassage("Dein Lager wurde erweitert.")
            }
            screenRefresh()

        }
        screenRefresh()
    }
    /*OnCreate END*/


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        screenRefresh()
    }

    private fun doEveryNextDay() {

        if (needTutorial && dayCounter == 1) {
            CustomDialog(
                window,
                "Nahrung",
                "Jeden Tag produzieren deine Bauern Nahrung um dein Volk am leben zu halten"
            )
                .show(supportFragmentManager, "first day")
            btnBerufe.visibility = View.VISIBLE
        }

        if (needTutorial && dayCounter == 3) {
            CustomDialog(
                window,
                "Gold",
                "Jeden dritten Tag produziert dein Volk Gold. \n Verwende Gold um dein Volk zu erweitern"
            )
                .show(supportFragmentManager, "first day")
            btnBerufe.visibility = View.VISIBLE
        }

        recalcPopulationSize()

        if (countFood is Currencys.Food) {
            if (!countFood.useFood(getUpkeepcost()))
                showPlayerMassage("DEINE BEVÖLKERUNG HUNGERT")
        }


        if (countFood.size + countBauern.size > sizeLager){
            countFood.size = sizeLager}
        else{
        countFood.increaseBy(countBauern.size)}
        countWood.increaseBy(countHolzFaeller.size)

        checkforEncounter()
    }

    private fun doEveryThirdDay() {
        countGold.increaseBy(countHaendler.size + 1)
    }


    private fun screenRefresh() {
        idFoodcount.text =
            getString(R.string.amo_max_use, countFood.size, sizeLager, getUpkeepcost())
        idPopcount.text =
            (countBauern.size + countHaendler.size + countRitter.size + countHolzFaeller.size).toString()
        idGoldcount.text = countGold.size.toString()
        idWoodcount.text = countWood.size.toString()
        tvdaycounter.text = dayCounter.toString()
    }



    private fun getUpkeepcost(): Int {
        val upkeepBauern = countBauern.size * countBauern.upkeepFood
        val upkeepHaender = countHaendler.size * countHaendler.upkeepFood
        val upkeepHolzfaeller = countHolzFaeller.size * countHolzFaeller.upkeepFood
        val upkeepRitter = countRitter.size * countRitter.upkeepFood
        return upkeepBauern + upkeepHaender + upkeepHolzfaeller + upkeepRitter
    }

    private fun recalcPopulationSize() {
        countPopulation =
            countBauern.size + countHaendler.size + countHolzFaeller.size + countRitter.size
    }


    private fun checkforEncounter() {
        if (dayCounter > 20) {
            if (needTutorial && dayCounter == 21) {
                CustomDialog(
                    window,
                    "Feinde",
                    "Nun sind bereits 20 Tage vergangen.\nDu solltest besser auch in eine Verteidigung investieren!\nAb nun können auch Feinde dein Volk überfallen"
                )
                    .show(supportFragmentManager, "first day")
                btnBerufe.visibility = View.VISIBLE
            }

            val rollforEncounter = Random.nextInt(0, 100)

            if (rollforEncounter in 1..30) {
                goodEncounter(rollforEncounter)
            }
            if (rollforEncounter in 31..40) {
                enemyEncounter()
            }
        }
    }

    private fun goodEncounter(rolled: Int) {
        /*var rolledcoins = rolled
        if (rolledcoins in 1..9) {
            showPlayerMassage("Du hast $rolledcoins Bauern gefunden ")
            countBauern.size += rolled
        } else {
            rolledcoins /= 10
            showPlayerMassage("Du hast $rolledcoins Münzen gefunden")
            countGold.increaseBy(rolledcoins)
        }*/
        showPlayerMassage("Heute ist ein guter Tag!")
    }

    private fun enemyEncounter() {
        val toast: Toast = Toast.makeText(applicationContext, "EnemyEncounter", Toast.LENGTH_SHORT)
        val intent = Intent(this, Playergpbattle::class.java)
        toast.show()
        startActivity(intent)
    }


}
