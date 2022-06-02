/**
 * toString(), hashCode(), equals(), copy(), constructor() 컴파일러가 자동으로 만들어준다
 */
data class Ticket(val companyName: String, val name: String, var date: String, var seatNumber: Int)

class TicketNormal(val companyName: String, val name: String, var date: String, var seatNumber: Int)


fun main(){
    val ticketA = Ticket("koreanAir", "dante", "2022-06-03", 14)
    val ticketB = TicketNormal("koreanAir", "dante", "2022-06-03", 14)

    println(ticketA)
    println(ticketB)
}