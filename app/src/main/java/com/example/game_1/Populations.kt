package com.example.game_1

enum class TypePopulation{
    Bauer, Test
}

object FactoryPopulation {

    fun create(populationtype:TypePopulation, sizeoncreate:Int): Populations {
        val population = when (populationtype){
            TypePopulation.Bauer -> populationtype.name
            TypePopulation.Test -> populationtype.name
        }

        return when(populationtype){
            TypePopulation.Bauer -> Populations.Bauer(population, sizeoncreate)
            TypePopulation.Test -> Populations.Test(population)
        }
    }
}


sealed class Populations {

    var size:Int = 0
    var name:String = ""

    data class Bauer(private val populationtype: String, private var sizeoncreate: Int) : Populations()
    {
        init{
            size = sizeoncreate
            name = populationtype
            }
    }
    data class Test(val populationtype: String) : Populations()

}



fun Populations.Bauer.calcNewDay(){
    size++
}








