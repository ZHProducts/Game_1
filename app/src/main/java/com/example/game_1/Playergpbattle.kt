package com.example.game_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_playergpbattle.*

class Playergpbattle : AppCompatActivity() {

    var playerhp = Playergphome.countPopulation
    var playeratk = Playergphome.countRitter.size
    var playerdef = Playergphome.countRitter.size

    var enemyhp = 10
    var enemyatk = 1
    var enemydef = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playergpbattle)

        hideSystemUI(window)
        btnkillEnemy.visibility = View.INVISIBLE

        refreshScreen()

        btnkillEnemy.setOnClickListener(){
            finish()
        }

        btnattack.setOnClickListener(){
            playerhp -= enemyatk
            enemyhp -= playeratk

            refreshScreen()

            if(enemyhp <= 0)
            {
                btnkillEnemy.visibility = View.VISIBLE
            }
        }
    }

    fun refreshScreen(){
        tvplayerarmyhp.setText("HP: $playerhp")
        tvplayerarmyatk.setText("ATK: $playeratk")
        tvplayerarmydef.setText("DEF: $playerdef")

        tvenemyarmyhp.setText("HP: $enemyhp")
        tvenemyarmyatk.setText("ATK: $enemyatk")
        tvenemyarmydef.setText("DEF: $enemydef")
    }
}
