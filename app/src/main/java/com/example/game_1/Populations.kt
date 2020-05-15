package com.example.game_1

enum class TypePopulation{
    Bauer, Haendler, Test
}

object FactoryPopulation {

    fun create(populationtype:TypePopulation, sizeoncreate:Int, upkeepfood: Int, buildingCost:Int): Populations {
        val population = when (populationtype){
            TypePopulation.Bauer -> populationtype.name
            TypePopulation.Test -> populationtype.name
            TypePopulation.Haendler -> populationtype.name
        }

        return when(populationtype){
            TypePopulation.Bauer -> Populations.Bauer(population, sizeoncreate, upkeepfood, buildingCost)
            TypePopulation.Test -> Populations.Test(population)
            TypePopulation.Haendler -> Populations.Haendler(population, sizeoncreate, upkeepfood, buildingCost)
        }
    }
}


sealed class Populations {

    var size:Int = 0
    var name:String = ""
    var upkeepFood:Int = 0
    var buildingCost:Int = 0


    data class Bauer(private val populationtype: String, private var sizeoncreate: Int, private var upkeepfood:Int, private var buildingcost: Int ) : Populations(){
        init{
            size = sizeoncreate
            name = populationtype
            upkeepFood = upkeepfood
            buildingCost = buildingcost
            }
    }
    data class Test(val populationtype: String) : Populations()

    data class Haendler (private val populationtype: String, private var sizeoncreate: Int,  private var upkeepfood:Int, private var buildingcost: Int) : Populations(){
        init {
            size = sizeoncreate
            name = populationtype
            upkeepFood = upkeepfood
            buildingCost = buildingcost
        }
    }

}










