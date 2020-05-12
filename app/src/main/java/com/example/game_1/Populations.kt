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

    var size:Int = 5

    data class Bauer(val populationtype: String, var sizeoncreate: Int) : Populations()
    data class Test(val populationtype: String) : Populations()

}



fun Populations.Bauer.calcNewDay(){
    size++
}




