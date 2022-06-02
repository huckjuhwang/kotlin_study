
// 3. Companion object
class Book private constructor(val id: Int, val name: String){

    // private의 프로퍼티나 메소드를 읽어 올 수 있도록 도와준다
    // java의 static 역할을 수행한다.
    companion object BookFactory : IdProvider {
        val myBook = "new Book"
        fun create() = Book(getId(), myBook)

        override fun getId(): Int {
            return 444
        }
    }
}

interface IdProvider {
    fun getId() : Int
}

fun main(){
    val book = Book.BookFactory.create()
    println("${book.id} ${book.name}")
}
