package com.example.game_1

import kotlin.random.Random

enum class TypeEnemy{
    Goblin,Troll
}


object FactoryEnemy {

    fun create(): Enemy{
              val creator:Int = Random.nextInt(1,100)
                return when(creator){
            in 1..50 -> Enemy.Goblin()
            in 51..100 -> Enemy.Troll()
                    else -> Enemy.Goblin()
                }
    }
}

sealed class Enemy {
    var name:String= ""
    var health:Int = 0
    var attack = 0
    var defense = 0


    class Goblin() : Enemy()
    {
        init{
            name = "Goblin"
            health = Random.nextInt(1,10)
            attack = Random.nextInt(1,3)
            defense = Random.nextInt(1,2)
        }
    }

    class Troll() : Enemy()
    {
        init{
            name = "Troll"
            health = Random.nextInt(20,50)
            attack = Random.nextInt(2,5)
            defense = Random.nextInt(1,4)
        }
    }

}

