package com.example.game_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_playergpbattle.*

class Playergpbattle : AppCompatActivity() {

    private var playerhp = Playergphome.countPopulation
    private var playeratk = Playergphome.countRitter.size
    private var playerdef = Playergphome.countRitter.size

    private var enemyhp = 10
    private var enemyatk = 1
    private var enemydef = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playergpbattle)

        hideSystemUI(window)
        btnkillEnemy.visibility = View.INVISIBLE

        refreshScreen()

        btnkillEnemy.setOnClickListener{
            finish()
        }

        btnattack.setOnClickListener{
            playerhp -= enemyatk
            enemyhp -= playeratk

            refreshScreen()

            if(enemyhp <= 0)
            {
                btnkillEnemy.visibility = View.VISIBLE
            }
        }
    }

    private fun refreshScreen(){
        tvplayerarmyhp.text = getString(R.string.hp, playerhp)
        tvplayerarmyatk.text = getString(R.string.atk, playeratk)
        tvplayerarmydef.text = getString(R.string.def, playerdef)

        tvenemyarmyhp.text = getString(R.string.hp, enemyhp)
        tvenemyarmyatk.text = getString(R.string.atk, enemyatk)
        tvenemyarmydef.text = getString(R.string.def, enemydef)
    }
}
