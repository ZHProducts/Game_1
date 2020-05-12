package com.example.game_1

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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

        window.decorView.apply { if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE
        }
        }


        btnNextDay.setOnClickListener{
         calcNextDay()
        }

        btnBerufe.setOnClickListener{

            val intent = Intent(this,Playergpberufe::class.java)
            startActivity(intent)
        }

        screenRefresh()

    }

    //START - Set Fullscreen Part
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }
    private fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
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
    //END - Set Fullscreen Part


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
}
