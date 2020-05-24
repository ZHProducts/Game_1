package com.example.game_1

import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_playergpbattle.*

class Playergpbattle : AppCompatActivity(), Runnable {

    private var playerhp = Playergphome.countPopulation
    private var playeratk = Playergphome.countRitter.size + 1
    private var playerdef = Playergphome.countRitter.size + 1

    private var mHandler: Handler = Handler()

    private val enemy = FactoryEnemy.create()

    private var heavymax: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playergpbattle)

        val soundPool: SoundPool


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val audioattributes: AudioAttributes =
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MOVIE)
                    .build()

            soundPool = SoundPool.Builder()
                .setMaxStreams(6)
                .setAudioAttributes(audioattributes)
                .build()

        } else {
            soundPool = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        }
        val sound1 = soundPool.load(this, R.raw.swords, 1)


        hideSystemUI(window)
        btnkillEnemy.visibility = View.INVISIBLE

        refreshScreen()

        btnkillEnemy.setOnClickListener {
            finish()
        }

        btnattack.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    mHandler.postDelayed(heavyattackaction, 500)
                }
                MotionEvent.ACTION_UP -> {
                    mHandler.removeCallbacks(heavyattackaction)
                    enemy.health -= playeratk
                    playeratk = Playergphome.countRitter.size + 1
                    refreshScreen()
                    heavymax = 0
                    if (enemy.health <= 0) {
                        btnkillEnemy.visibility = View.VISIBLE
                    }
                    soundPool.play(sound1, 1f, 1f, 0, 0, 0f)
                }
            }
            v?.onTouchEvent(event) ?: true
        }
    }

    private var heavyattackaction: Runnable = Runnable {

        if (heavymax < 3) {
            run()
            heavymax++
        }
    }

    override fun run() {
        playeratk += 1
        mHandler.postDelayed(heavyattackaction, 500)
        refreshScreen()
    }

    private fun refreshScreen() {
        tvenemyarmyname.text = enemy.name
        tvplayerarmyhp.text = getString(R.string.hp, playerhp)
        tvplayerarmyatk.text = getString(R.string.atk, playeratk)
        tvplayerarmydef.text = getString(R.string.def, playerdef)


        tvenemyarmyhp.text = getString(R.string.hp, enemy.health)
        tvenemyarmyatk.text = getString(R.string.atk, enemy.attack)
        tvenemyarmydef.text = getString(R.string.def, enemy.defense)

    }


}
