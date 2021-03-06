package com.example.game_1

enum class TypeCurrency{
    Food, Gold, Wood
}


object FactoryCurrency {

    fun create(currencytype:TypeCurrency, sizeoncreate:Int): Currencys{
        val currencyname = currencytype.name

        return when(currencytype){
            TypeCurrency.Food -> Currencys.Food(currencyname, sizeoncreate)
            TypeCurrency.Gold -> Currencys.Gold(currencyname)
            TypeCurrency.Wood -> Currencys.Wood(currencyname, sizeoncreate)
        }
    }
}

sealed class Currencys {
    var size:Int = 0
    var name:String= ""

    fun increaseBy(amount:Int){
        size += amount
    }

    fun useCurrency(amount: Int){
        size -= amount
    }

    data class Food(val currencytype: String, var sizeoncreate: Int) : Currencys()
    {
        init{
            size = sizeoncreate
            name = currencytype
        }
    }
    data class Wood(val currencytype: String, var sizeoncreate: Int) : Currencys()
    {
        init{
            size = sizeoncreate
            name = currencytype
        }
    }
    data class Gold(val populationtype: String) : Currencys()
}


fun Currencys.Food.useFood(amount:Int):Boolean{
    return if (size > amount){
        size -=amount
        true
    }
    else{
        size = 0
        false
    }
}