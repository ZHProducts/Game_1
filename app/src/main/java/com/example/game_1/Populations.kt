package com.example.game_1

enum class TypePopulation{
    Bauer,
    Haendler,
    Holzfaeller,
    Ritter,

}

object FactoryPopulation {

    fun create(populationtype:TypePopulation, sizeoncreate:Int, upkeepfood: Int, buildingCost:Int): Populations {
        val population = populationtype.name

        return when(populationtype){
            TypePopulation.Bauer -> Populations.Bauer(population, sizeoncreate, upkeepfood, buildingCost)
            TypePopulation.Haendler -> Populations.Haendler(population, sizeoncreate, upkeepfood, buildingCost)
            TypePopulation.Ritter -> Populations.Ritter(population, sizeoncreate, upkeepfood, buildingCost)
            TypePopulation.Holzfaeller -> Populations.Holzfaeller(population, sizeoncreate, upkeepfood, buildingCost)
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
    data class Haendler (private val populationtype: String, private var sizeoncreate: Int,  private var upkeepfood:Int, private var buildingcost: Int) : Populations(){
        init {
            size = sizeoncreate
            name = populationtype
            upkeepFood = upkeepfood
            buildingCost = buildingcost
        }
    }
    data class Holzfaeller(private val populationtype: String, private var sizeoncreate: Int,  private var upkeepfood:Int, private var buildingcost: Int) : Populations(){
        init{
            size = sizeoncreate
            name = populationtype
            upkeepFood = upkeepfood
            buildingCost = buildingcost
        }
    }
    data class Ritter (private val populationtype: String, private var sizeoncreate: Int,  private var upkeepfood:Int, private var buildingcost: Int) : Populations() {
        init {
            size = sizeoncreate
            name = populationtype
            upkeepFood = upkeepfood
            buildingCost = buildingcost
        }
    }

}










