package com.example.game_1

enum class TypeCurrency{
    Food, Gold
}


object FactoryCurrency {

    fun create(currencytype:TypeCurrency, sizeoncreate:Int): Currencys{
        val currencyname = when (currencytype){
            TypeCurrency.Food -> currencytype.name
            TypeCurrency.Gold -> currencytype.name
        }

        return when(currencytype){
            TypeCurrency.Food -> Currencys.Food(currencyname, sizeoncreate)
            TypeCurrency.Gold -> Currencys.Gold(currencyname)
        }
    }
}

sealed class Currencys {
    var size:Int = 0
    var name:String= ""

    data class Food(val currencytype: String, var sizeoncreate: Int) : Currencys()
    {
        init{
            size = sizeoncreate
            name = currencytype
        }
    }
    data class Gold(val populationtype: String) : Currencys()
}

fun Currencys.Food.increaseBy(amount:Int){
    size += amount
}

fun Currencys.Food.useFood(amount:Int){
    size -=amount
}