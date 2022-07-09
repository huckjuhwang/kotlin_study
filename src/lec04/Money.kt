package lec04

data class Money(
    val amount: Long
){
    operator fun plus(other: Money): Money{
        return Money(this.amount + other.amount)
    }
}


fun main(){
    val money1 = Money(1000L)
    val money2 = Money(2000L)

    // Money(amount=3000)
    println(money1 + money2)
}