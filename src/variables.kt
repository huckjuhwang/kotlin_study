/**
 * val : 변할 수 없는 상수, 초기화 이후에 값을 변경할 수 없음/ Java에서 final
 * var : 일반적인 변수에 해당함
 */
fun main(){
//    var list_one = ArrayList<String>()
//    var list = ArrayList<String>()
//    list.add("하나")
//    list.add("둘")
//    list.add("셋")
//
//    for(x in 0..list.size-1){
//        println(list.get(x) + " ")
//    }


    forAndWhile()
    nullCheck()
    ignoreNulls("test")
//    ignoreNulls(null)     // NPE발생!! ( !! 연산 때문에 컴파일 과정에서 찾을 수가 없음 )


    // 생성자 시점에서 초기화 해주지 않으면 문법상 오류가 생긴다.
    val a: Int = 1
    val b = 1
    val c: Int = 100

    // 일반적인 변수
    var x = 5
    x += 10

    // 코틀린의 기본변수는 null을 가질 수 없다.
    val q: Int = 15
    //a = null   //  문법상 오류 발생

    // 이와 같이 물음표를 추가했을때만 null을 명시할수 있다.
    var w: Int? = null
    w = null


    /**
     * var와 val 모두 추론을 통해서 Int, String인지 확인이 가능하지만,
     * 실수의 여지가 존재하기 때문에 지정하는 것이 좋다.
     */

    printMessage("Hello")
    printMessageWithPrefix("Hello", "Log")
    printMessageWithPrefix("Hello")
}

// ABC의 객체를 에서 a를 return 하는 함수입니다.
// 이때 a는 Int이면서 null이 될수 있도록 물음표를 추가하였습니다.
// ABC의 객체의 .a를 return 하지만 abc가 null이면 null을 리턴합니다.
/**
    fun abc(abc: ABC?) : Int? {
        return abc?.a
    }
*/



// fun 함수명(변수명: 변수 타입): 리턴 타입 { return 값 }
fun printMessage(message: String): Unit{
    println(message)
}

fun printMessageWithPrefix(message: String, prefix: String = "Info") {
    println("[$prefix] $message")
}



// 조건식
fun maxBy(a: Int, b: Int) = if(a>b) a else b


// when 문법정리(switch)
// when 은 if문을 중첩하여 사용하지 않고 간단하게 Any와 함께 구현이 가능합니다.
/**
fun main(args: Array<String>) {
    cases("Hello") // String
    cases(1) // Int
    cases(System.currentTimeMillis()) // Long
    cases(MyClass()) // Not a string
    cases("hello") // Unknown
}
 */


// Any 사용하기
// Any는 Java의 Object에 해당하며, is는 instanceof와 같은 기능으로 동작합니다.
fun getStringLength(obj: Any): Int?{
    if (obj is String) {
        return obj.length
    }
    // String이 아니면 null을 리턴하게 된다.
    return null
}

// 위의 형태를 자바로 구현한다면?
/**
int getStringLength(Object obj){
    if( obj instanceof String ){
        return obj.length;
    }
    return 0;
}
 */

// is String이 아님을 나타낼때는 is앞에 "!" 간단하게 부정할 수 있습니다.
/**
 * Java *
int getStringLength(Object obj){
    if( !(obj instanceof String)) return 0;
    return obj.length;
}

 * Kotlin *
fun getStringLength(obj: Any) : Int? {
    if( obj !is String ) return null
    return obj.length
}
 */



// Any == Object
fun cases(obj: Any) {
    when (obj) {
        1 -> println("One")
        2,3 -> println("this is 2 of 3")
        "Hello" -> println("Greeting")
        is Long -> println("Long")
        !is String -> println("Not a String")
        else -> println("Unknown")
    }


    // 이런식으로도 사용할 수 있음
    var b = when (obj) {
        1-> 1
        2-> 2
        else -> 3
    }
}

fun checkNum(score: Int) {
    when (score) {
        in 90..100 -> println("You are genius")
        in 10..80 -> println("not bad")
        else -> println("okay")
    }
}


// Expression vs Statement
// 5. Array and list
// Array
// List
// 1. list(읽기 전용 리스트) 2. MutableList(ArrayList)
fun array(){
    var array = arrayOf(1, 2, 3)
    var list = listOf(1,2,3)

    var array2 = arrayOf(1, 2, 3)
    var list2 = listOf(1,2,3)

    // (Array) 1. 읽기, 쓰기 모두가능
    array[0] = 3

    // (list) 1. 읽기만 가능
    // list[0] = 2;

    var arrayList = arrayListOf<Int>()
    arrayList.add(10)
    arrayList.add(20)

}


// 6. for / while

/**
 * Java *
for(int i = 1; i<=5; i++) {
    Log.i("TAG", "index : " + i);
}

ArrayList<Stirng> list = new ArrayList<>();
for(String s : list){
    Log.d("TAG", "String : " + s);
}

 * Kotlin *
for(x in 1..5) {
    println(x)
}

val list = ArrayList<String>()
for(s in list){
    log.d("TAG", "String : " + s)
}
 */

fun forAndWhile(){
    val students = arrayListOf<String>("joyce", "dante", "jenny", "jennifer")

    for (name in students) {
        println(name)
    }

    for ((index, name) in students.withIndex()) {
        println("${index+1}번째 학생 : ${name}")
    }

    var sum : Int = 0;
    for (i in 1..10) sum += i
    println(sum)

    for (i in 1..10 step 2)  print("$i ")
    println()

    for (i in 10 downTo 2) print("$i ")
    println()

    // 1~99까지 돌아감  // 1..100(1~100까지 돌아감)
    for (i in 1 until 100)   print("$i ")

    var index = 0
    while (index < 10) {
        println("current index : ${index++}")
    }


}


// 7. Nullable / NonNull
fun nullCheck(){
    // NPE : NULL pointer Exception
    // 컴파일 과정에서는 잡히지 않고, 런타임 시점에 해당 에러가 검출된다. ( 불편 )
    // 그래 컴파일 시점에 잡아줄게 ( ?의 웅장한 등장 )
    var name : String = "dante"
    var nameInUpperCase = name.toUpperCase()


    // ?를 삽입하게 되면, null을 삽입할 수 있게 된다.
    var nullName : String? = null

    // 만약 null이면 null을 반환하고, null이 아니면, uppercase를 수행한다.( 단 한줄로 코드가 간결해진다. )
    var nullNameInUpperCase = nullName?.uppercase()

    // ?:
    // 위의 소스코드에서 null일 경우 default value를 주고 싶을 경우 사용
    var lastName: String? = null

    // 만약 lastName이 있으면 lastName을 출력하게 되고, Null일 경우 No lastName을 출력해 준다.
    val fullName = name + " " + (lastName?: "No lastName")
    println(fullName)

}


// !!
// 이거는 null이 절때 아니다.
fun ignoreNulls(str: String?) {
    // 해당 매개변수로 들어온 str변수는 null이 절때 아니다 ( 우리가 컴파일러한테 알려주게 됨 )
    // !!이것은 주의해서 사용해야 된다. 만약 Null이 넘어오게 되면 NPE가 발생한다.
    val mNotNull : String = str!!
    val upper = mNotNull.toUpperCase()
    println(upper)


    var email : String? = "skygurwn96@naver.com"
    // 자신의 receive 객체를 람다식 내부로 옮겨 준다.
    // null이 아니면 옮겨준다
    email?.let{
            println("my email is $email")
    }
}







