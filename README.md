# kotlin_study
코틀린 기본문법을 학습하기 위한 레파지토리입니다.

// ex) [목차텍스트](#링크가 걸리는 텍스트)

## 목차 
1. [var vs val](#lec01.-var-vs-val)


## lec01. var vs val

`var` => 수정가능한 변수 <br>
`val(final)` => 수정 불가능한 변수

```kotlin
    // variable
    var number1: Long = 10L
    number1 = 4L

    // Val cannot be reassigned!!!
    val number2: Long = 10L
   //    number2 = 5L

    // null값을 넣어주기 위해 ?를 사용할 수 있다.
    var number3: Long? = 10000
    number3 = null

    // 객체를 인스턴스화 할때 new를 붙이지 않아야한다.
    var person = Person("name")

```

## 정리
- val 컬렉션에는 `element` 를 추가할 수 있다.
- 타입을 명시적으로 작성하지 않아도, 타입이 추론된다.
- 모든 변수를 `우선 val로 만들고` 꼭 필요한 경우 `var로 변경`한다(꿀팁)
- kotlin에서의 `primitive type, reference type`은 하나의 타입으로 구분하며, 
- 즉, 프로그래머가 `boxing / unboxing`을 고려하지 않아도 되도록 코틀린이 알아서 상황에 따라서 바꿔서 사용해준다. 
- null이 들어갈 수 있는 변수는 타입 뒤에 `?` 를 붙여주어야 한다. (아예 다른 타입으로 간주)


## lec02. null check

```kotlin
val str: String? = "ABC" 
str.length // 불가능 
str?.length // 가능
```
- null이 아니면 실행하고, null이면 실행하지 않는다.(그대로 null)

```kotlin
var str: String? = "ABC"
str?.length ?:0
```
- 앞의 연산 결과가 null이면 뒤의 값을 사용

## 정리
- 코틀린에서 null이 들어갈 수 있는 타입은 완전히 다르게 간주된다
  - 한번 null 검사를 하면 non-null임을 컴파일러가 알 수 있다.
- null이 아닌 경우에만 호출되는 Safe Call(`?.`)있다.
- null인 경우에만 호출하는 Elvis연산자 (`?:`)가 있다.
- null이 절때 아닐때, 사용할 수 있는 널이 아님 단언(`!!`)이 있다

## lec03. type

```kotlin
var number1 = 3 // Int
var number2 = 3L // Long
```
- 선언된 기본값을 보고 타입을 추론한다.

```kotlin
var number1 = 3 // Int
var number2: Long = number1 // Type mismatch!!

var number3: Long = number1.toLong()
```
- 코틀린에서는 암시적 타입 변경이 불가능하다.
- to변환타입()을 사용해야 한다.


```kotlin
var number1: Int? = 3 // Int
var number2: Long = number1?.toLong() ?: 0L
```
- 변수에 nullable이라면 적절한 처리가 필요하다.


### JAVA, Kotlin diff
```java
public static void printAgeIfPerson(Object obj){
    if(obj instanceof Person){
        Person person = (Person) obj;
        sout(person.getAge());
    }
}
```

```kotlin
fun printAgeIfPerson(obj: Any) {
  if (obj is Person) {
//      val person = obj as Person // 생략가능! ( 스마트 캐스트, if를 들어왔는데 타입체크를 해줬을때 )
      println(person.age)
  }
}
```
- kotlin의 is -> Java의 instanceof
- kotlin의 as -> (Person) obj 
  - obj라는 변수를 person타입으로 간주한다.
  - 스마트 캐스트(생략)가능, if를 들어왔는데 타입체크를 해줬을때

```kotlin
fun main(){
    printAgeIfPerson(null)
}

fun printAgeIfPerson(obj: Any?) {
  val person = obj as? Person   // Person?
  println(person?.age)
}
```
- obj가 null이라면 Safe Call처럼 null이 된다.

### is
```kotlin
value is Type   // value가 type이면 TRUE
                // value가 type이 아니면 FALSE

value !is Type   // value가 type이면 FALSE
                 // value가 type이 아니면 TRUE
```

### as
```kotlin
value as Type   // value가 type이면 Type으로 타입 캐스팅
                // value가 type이 아니면 예외 발생

value as? Type   // value가 type이면 Type으로 타입 캐스팅
                 // value가 null이면 null
                 // value가 type이 아니면 null
```

### Any
- Java에서 Object역할(모든 객체의 최상위 타입)
- 모든 primitive Type의 최상의 타입도 Any
- Any자체로는 null을 포함할 수 없어 null을 포함하고 싶으면 Any?로 표현
- Any에 equals / hashCode / toString이 존재


### unit
- Java의 void와 동일한 역할
- Void와는 다르게 Unit은 그 자첼 타입 인자로 사용이 가능하다
- 함수형 프로그래밍에서 Unit은 단 하나의 인스턴스만 갖는 타입을 의미.
- 즉, 코틀린의 Unit은 실제 존재하는 타입이라는 것을 표현

### Noting
- 함수가 정상적으로 끝나지 않았다는 사실을 표현하는 역할
- 무조건 예외를 반환하는 함수/ 무한 루프 함수 등
```kotlin
fun fail(message: String): Nothing{
    throw IllegalArgumentException(message)
}
```

### String interpolation / String indexing
```kotlin
val person = Person("dante", 100)
println("이름 : ${person.name}")

var str = """
  자
  유
  롭
  게
  작
  성 가농 농
""".trimIndent()
println(str)

var str1 = "ABC"
println(str1[0])
```
- ${변수}를 사용할 수 있다.


## 정리
- 코틀린의 변수는 초기 값을 보고 타입을 추론하며, 기본 타입들간의 변환은 명시적으로 이루어진다.
- 코틀린에서는 `is, !is, as, as?`를 이용해 타입을 확인하고 캐스팅한다.
- 코틀린의 `Any`는 Java의 Object와 같은 최상위 타입이다.
- 코틀린의 `Unit`은 Java의 void와 같다.
- 코틀린에 있는 `Noting`은 정상적으로 끝나지 않은 함수의 반환을 의미한다.
- 문자열을 가공할때, `${변수}`와 `""" """`를 사용하면 깔끔한 코딩이 가능하다.
- 문자열에서 문자를 가져올때는 Java의 배열처럼 `[]`를 사용한다.

## lec04. 연산자

1. 단항 연산자(++,--), 산술연산자(+,-,*, 등.) Java와 동일
2. 비교 연산자(>, <) Java와 동일
```java
public class JavaMoney implements Comparable<JavaMoney> {
  private final long amount;

  public JavaMoney(long amount) {
    this.amount = amount;
  }

  // 주어진 수 기준,
  // 크면(>) 음수
  // 작으면(<) 양수
  // 같으면(==) 0
  @Override
  public int compareTo(@NotNull JavaMoney javaMoney) {
    return Long.compare(this.amount, o.amount);
  }
}

  public static void main(String[] args) {
    JavaMoney money1 = new JavaMoney(2000L);
    JavaMoney money2 = new JavaMoney(1000L);

    if (money1.compareTo(money2) > 0) {
      System.out.println("money1이 money2보다 금액이 큽니다.");
    }
  }
```
```kotlin
fun main(){

  val money1 = JavaMoney(2000L)
  val money2 = JavaMoney(1000L)

  if (money1 > money2) {
      println("money1이 money2보다 금액이 큽니다.");
  }
}
```
- Java와는 다르게 객체를 비교할때, 비교 연산자를 사용하면, 자동으로 `compareTo`를 호출해줍니다.

### 비교연산자와 동등성, 동일성
* 동일성(Identity): 완전히 동일한 객체인가?! 즉, 주소가 같은가??
* 동등성(Equality): 두 객체의 값이 같은가

- Java   기준 동일성 `==`  / 동등성 `equals`
- Kotlin 기준 동일성 `===` / 동등성 `==`호출한다.
  - `==`을 사용하면 간접적으로 `equals`를 호출해준다.

```Java
  public static void main(String[] args) {
    JavaMoney money1 = new JavaMoney(1000L);
    JavaMoney money2 = money1;
    JavaMoney money3 = new JavaMoney(1000L);
    
    sout(money1 == money2);     // true
    sout(money1 == money3);     // false
  }
```

```kotlin
fun main(){
    val money1 = JavaMoney(1000L)
    val money2 = money1
    val money3 = JavaMoney(1000L)
  
    println(money1 === money2) // true
    println(money1 === money3) // false
  
  
  fun fun1( )
}
```

### 논리연산자( &&, ||, ! )
- 자바와 완전히 동일
 

### 특이한 연산자

#### in, !in 
- 컬렉션이나 범위에 포함되어 있다, 포함되어있지 않다.
```kotlin
println (1 in numbers)
```

#### a..b
- a부터 b까지의 범위 객체를 생성한다.


### 연산자 오버로딩
- 코틀린에서는 객체마다 연산자를 직접 정의할 수 있다.
```java
public class JavaMoney implements Comparable<JavaMoney> {
  private final long amount;

  public JavaMoney(long amount) {
    this.amount = amount;
  }

  /**
   * @param other
   * @return
   */
  public JavaMoney plus(JavaMoney other) {
    return new JavaMoney(this.amount + other.amount);
  }

  // 주어진 수 기준,
  // 크면(>) 음수
  // 작으면(<) 양수
  // 같으면(==) 0
  @Override
  public int compareTo(@NotNull JavaMoney javaMoney) {
    return Long.compare(this.amount, o.amount);
  }
  
  @..toString();
  
  @..hashCode();
  
  @..eqauls();
}

  public static void main(String[] args) {
    JavaMoney money1 = new JavaMoney(2000L);
    JavaMoney money2 = new JavaMoney(1000L);
    
    // JavaMoney{amount=3000}
    System.out.println(money1.plus(money2));
  }
```
```kotlin
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
```

## 정리
- 단항연산자, 산술연산자, 산술대입연산자, 비교연산자 JAVA와 같다.
  - 단 객체끼리 비교 시점에 CompareTo가 자동으로 사용되어 비교연산자를 수행할수 있다.
- in, !in / a..b / a[i] / a[i] = b와 같이 코틀린에서 새로 생긴 연산자도 있다.
- 객체끼리의 연산자를 코틀린에서는 직접 정의할 수 있다.

## lec05. 조건문

### Expression & Statement
```kotlin

 import java.lang.IllegalArgumentException
 
 fun validateScoreIsNotNegative(score: Int) {
    if(score < 0){
        throw IllegalArgumentException("${score}는 0보다 작을수 없습니다.")
    }
} 

fun getPassOrFail(score: int): String{
  if (score >= 50) {
      return "P"
  }else {
      return "F"
  }
}
```
- 동일한점도 있지만, 다른점도 존재한다.
- JAVA에서 if-else는 `Statement`이지만, Kotlin에서는 `Expression`이다.

  * `statement` : 프로그램의 문장, 하나의 값으로 도출되지 않는다.
  * `Expresstion`: 하나의 값으로 도출되는 문장

```java
String grade = if(score >= 50){     // 오류 문장!!
    "P";
}else{
    "F"
}
```
- Java에서는 하나의 값으로 취급하지 않기 때문에 오류가 발생한다.
- 때문에, Java에서는 3항 연산자를 사용하여 해결한다.
```kotlin
fun getPassOrFail(score: int): String{
  return if (score >= 50) {
    "P"
  }else {
    "F"
  }
}
```
- 코틀린에서는 if문 전체를 리턴할 수 있다.
- IF-ELSE가 Expression이기 때문에(3항 연산자 같은 느낌)
- 따라서, 코틀린에서는 3항연산자가 존재하지 않는다.

```kotlin

fun getGrade(score: Int) String{
  return if (score >= 90) {
      "A"
  }else if (score >= 80) {
      "B"
  }else if (score >= 70) {
      "C"
  }else{
      "D"
  }
}
```
###Tip!!
- 어떤 값이 특정 법위에 포함되어 있는지, 포함되어 있지 않은지
```java
if(0 <= score && score <= 100)
```

```kotlin
// score가 1~100사이 범위에 있지 않은 경우
if( score !in 1..100 ){
  throw IllegalArgumentException("${score}는 0부터 100사이의 값이 아닙니다.")
}
```

### switch와 when

```text
when(값){
    조건부 -> 어떠한 구문
    조건부 -> 어떠한 구문
    else-> 어떠한 구문
}
```
- 조건부에는 어떠한, expression이라도 들어갈 수 있다. (EX. `is` Type)
```java
private boolean startsWithA(Object obj){
  if(obj instanceof String){
    return (String)obj.startsWith("A");
  }else { 
    return false;
  }
}
```

```kotlin
fun startsWithA(obj: Any): Boolean {
    return when(obj){
        is String -> obj.startsWith("A")
        else -> false
    }
}
```

- 여러개의 조건을 동일하게 검증할 수 있다.
```kotlin
fun judgeNumber(number: Int){
  when (number) {
    1, 0, -1 -> println("어디서 많이 본 숫자입니다.")
    else -> println("1, 0, -1이 아닙니다.")
  }
}
```

```kotlin
fun judgeNumber2(number: Int){
  when {
    number == 0 -> println("주어진 숫자는 0입니다")
    number % 2 == 0 -> println("주어진 숫자는 짝수 입니다.")
    else -> println("주어진 숫자는 홀수입니다.")
  } 
}
```
- when은 `enum Class` or `sealed class`와 함께 사용할 경우, 더욱더 진가를 발휘한다.

```kotlin
fun getGradeWithSwitch(score: Int): String{
  return when (score / 10) {
      in 90..99 -> "A"
      in 80..89 -> "B"
      in 70..79 -> "C"
      else -> "D"
  }
}
```

## 정리
- if / if-else / if-else if-else 모두 문법이 동일하다
- 단, kotlin에서는 Expression으로 취급된다. ( 따라서, 삼항연산자가 존재하지 않는다 )


## lec 06. 반복문

```kotlin
fun main() {
  // 숫자가 들어있는 리스트를 하나씩 출력하는 예제
  val number = listOf(1L, 2L, 3L)
  for(number in numbers){
      println(number)
  }
  
  // 1부터 3까지 출력하는 반복문 
  for(i in 1..3){
      pruntln(i)
  }

  // 내려가는 경우
  for (i in 3 downTo 1) {
      println(i)
  }
  
  // 2칸씩 올라가는 경우?
  for (i in 1..5 step 2) {
      println(i)
  }

  /**
   * 1..5 step 2
   * 1부터 5까지 등차가 2인 등차수열이 생성된다.
   */
}
```
- Kotlin에서 전통적인 for문은 등차수열을 사용한다.
- while문과 do-while문은 java와 완전히 일치한다.

## 예외처리
```java
private int parseIntOrThrow(@NotNull String str){
    try{
        return Integer.parseInt(str);
    } catch(NumberFormatException e) {
        throw new IlleagArgumentException(String.format("주어진 숫자는 숫자가 아닙니다.");)    
    }    
}
```

```kotlin
fun parseIntOrThrow(str: String): Int {
  try {
    return str.toInt()
  } catch (e: NumberFormatException) {
    throw IllegalArgumentException("주어진 ${str}는 숫자가 아닙니다.")   
  }
}
```

- 주어진 문자열을 정수로 변경하는 예제 실패하면 null을 반환
```kotlin
fun parseIntOrThrow2(str: String): Int {
  return try { 
    str.toInt()
  } catch (e: NumberFormatException) {
    null 
  }
}
```

### checked Exception, Unchecked Exception
- Kotlin에서는 Checked Exception과 Unchecked Exception을 구분하지 않습니다.
- 모두 `Unchecked Exception`입니다.
- try catch finally 구문은 문법적으로 완전히 동일하다.
  - kotlin에서는 try catch가 Expression이다.
- kotlin에서는 try with resources구문이 없다. 대신 코틀린의 언어적 특징을 활용해 close를 호출해 준다.

## lec 08.함수
### 함수 선언 문법
- 두 정수를 받아 더 큰 정수를 반환하는 예제
```kotlin
fun max(a: Int, b: Int): Int {
  return if (a > b) {
      a
  }else {
      b
  }
}
```
- 함수가 하나의 결과값이면 block 대신 = 사용 가능 
```kotlin
fun max(a: Int, b: Int): Int =
  if (a > b) {
      a
  }else {
      b
  }
```

- 리턴 타입을 추론가능하기 때문에, 제거가능
- 한 줄로 변경 가능
- 만약 block {}을 사용하는 경우에는 반환 타입이 Unit이 아니면, 명시적으로 작성해 주어야 합니다!
```kotlin
fun max(a: Int, b: Int) = if (a > b) a else b
```
- 함수는 클래스 안에 있을 수도, 파일 최상단에 있을 수도 있습니다.
- 또한, 한 파일 안에 여러 함수들이 있을 수도 있습니다.
### default parameter
- 주어진 문자열을 N번 출력하는 예제

```java
public void repeat(String str, int num, boolean useNewLine){
  for(int i =1; i<=num; i++){
      if( useNewLine ){
          soutln(str);
      }else {
          sout(str);
      }
  }
}

public void repeat(String str, int num){
  repeat(str, num, true);
}

public void repeat(String str){
    repeat(str, 3, true);
}
```

```kotlin
fun repeat(
  str: String,
  num: Int = 3,
  useNewLine: Boolean = true,
){
  for (i in 1..num) {
    if (useNewLine) {
        println(str)
    }else{
        print(str)
    }
  }
}
```
- 밖에서 파라미터를 넣어주지 않으면, 기본값을 사용하자.
- 물론, 코틀린에도, 오버로딩 기능은 존재한다.

### named argument
- repeat을 호출할 때, num은 3을 그대로 쓰고, useNewLine은 false를 쓰고 싶다!
- 어떻게 사용하지?
```kotlin
fun main() {
    repeat("Hello world", useNewLine = false)
}

fun repeat(
  str: String,
  num: Int = 3,
  useNewLine: Boolean = true,
){
  for (i in 1..num) {
    if (useNewLine) {
        println(str)
    }else{
        print(str)
    }
  }
}
```
- 매개변수 이름을 통해 직접 지정, 지정되지 않은 매개변수는 기본값 사용
- 이를통해, builder를 직접 만들지 않고, builder의 장점을 가지게 된다.
```kotlin
fun main() {
  printNameAndGender(name="dante", gender="man")
}

fun printNameAndGender(name: Stirng, gender: Stirng) {
  println(name)
  println(gender)
}
```
- kotlin에서 Java함수를 가져다 사용할 때는, named argument를 사용할 수 없다.

### 같은 타입의 여러 파라미터 받기(가변인자)
- 문자열 N을 받아 출력하는 예제
```java
public static void printAll(String... strings){
    for(String str: strings){
        sout(str);
    }    
}
```
- Java에서는 타입 ...을 사용했을때, 가변인자를 사용하는것을 인지
```kotlin
fun main() {
  printALl("A", "B", "C")

  val array = arrayOf("A", "B", "C")
  printALl(*array)
}

fun printALl(vararg strings: String) {
  for (str in strings) {
      println(str)
  }
}
```
- ...을 타입 뒤에 쓰는 대신 제일 앞에 vararg를 적어주어야 한다. 
- 배열을 바로 넣는대신 스프레드 연산자를 (*)를 붙여주어야한다.


## lec 09. 클래스
### 클래스와 프로퍼티
```java

public class JavaPerson{
  private final String name;
  private int age;

  public JavaPerson(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
```
- name에는 `setter`가 존재하지 않는다(final)


```kotlin
class Person constructor(name: String, age: Int){
    val name = name 
    var age = age
}
```

```kotlin
fun main() {
  val person = Person("dante", 100)
  println(person.name)  // getter
}

class Person(
  val name: String,
  var age: Int
  )
```
- 코틀린에서는 필드만 만들면 `getter, setter`가 자동으로 생성된다.


### 생성자와 init
- 클래스가 생성되는 시점에 나이를 검증해 보자
```java

public class JavaPerson{
  private final String name;
  private int age;

  public JavaPerson(String name, int age) {
    if (this.age <= 0) {
      throw new IllegalArgumentException(String.Format("나이 범위 초과 오류"));
    }
    this.name = name;
    this.age = age;
  }

  public JavaPerson(String name) {
    this(name, 1);
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
```

```kotlin
fun main(){
    val person = Person("dante")
}

class Person(
  val name: String,
  var age: Int
){
    init {
      if (age <= 0) {
          throw IllegalArgumentException("나이는 ${age}일 수 없습니다.")
      }
    }

  constructor(name: String) : this(name, 1){
      println("부 생성자1")
  }
}
```
- `init(초기화)`블록은 생성자가 호출되는 시점에 호출된다.
- 값을 적절히 만들어주거나, `validation`역할로 사용
- 생성자를 추가하고 싶은 경우에는 `constuctor(파라미터)`로 생성자를 추가
- 주 생성자는 반드시 존재해야한다!! 단, 주생성자에 파라미터가 하나도 없다면 생략이 가능하다.
- 부 생성자는 있을수도 있고, 없을수도 있다. ( 최종적으로 주 생성자를 `this`로 호출해줘야한다. )
- 본문은 역순으로 호출된다( init{ 본문부터 실행 } )
- default parameter를 사용하는것을 추천



### 커스텀 getter, setter

```kotlin
fun main(){
    val person = Person("dante")
}

class Person(
  val name: String,
  var age: Int
){
    init {
      if (age <= 0) {
          throw IllegalArgumentException("나이는 ${age}일 수 없습니다.")
      }
    }

  constructor(name: String) : this(name, 1){
      println("부 생성자1")
  }
}
```
### backing field
```kotlin
fun isAdult(): Boolean{
    return this.age >= 20
}

val isAdult2: Boolean
    get() = this.age >= 20


val isAdult3: Boolean
    get(){
        return this.age >= 20
    }

```
- 객체의 속성일 경우, custom getter를 사용하고, 그렇지 않다면 함수를 사용한다.

#### Q. name을 get할때, 무조건 대문자로 바꾸어 줘야한다면?
```kotlin

class Person(
  name: String,
  var age: Int
){
  // 여기!!
  val name = name
    get() = field.uppercase()
  
    init {
      if (age <= 0) {
          throw IllegalArgumentException("나이는 ${age}일 수 없습니다.")
      }
    }

  constructor(name: String) : this(name, 1){
      println("부 생성자1")
  }
}
```
- name에 대한 `Custom getter`를 만들때, `field`를 사용해야된다. (무한루프를 막기위한 예약어, 자기자신을 가리킨다.)

#### Q. name을 set할때, 무조건 대문자로 바꾸어 줘야한다면?
```kotlin
var name = name
    set(value) {
        field = value.uppercase()
    }
```


### 정리
- 코틀린에서는 필드를 만들면 getter와(필요에 따라) setter가 자동으로 생긴다.
  - 때문에, 이를 `프로퍼티`라고 부른다
  ```kotlin
  class Person(
    var ame: String = "dante",
    var age: Int = 1
  )
  ```
- 코틀린에서는 `주 생성자`가 필수 이다.
- constuctor 키워드를 사용하여 `부 생성자`를 추가로 만들수 있지만, `default parameter`나 정적 팩토리 메소드를 추천한다.
- 실제 메모리와 존재하는 것과 무관하게 `custom getter`와 `custom setter`를 만들수 있다.
- custom getter와 setter에서 무한루프를 막기 위해 `field`라는 키워드를 사용한다.
  - 이를 `backing field`라고 부른다.


## 상속

### 추상 클래스
```kotlin
abstract class Animal(
  protected val species: String,
  protected open val legCount: Int,
) {
  abstract fun move()
}
```
- 프로퍼티를 override하기 위해서는 `open`키워드를 붙여주어야한다.
```kotlin
class Cat(
    species: String
) : Animal(species, 4) {
    
    override fun move() {
        println("고양이가 사뿐 사뿐 걸어가~")
    }

}
```
- extends 키워드를 사용하지 않고, `:`을 사용한다.
- 상위 클래스의 생성자를 바로 호출한다.
```kotlin
class Penguin(
    species: String
) : Animal(species, 2) {
    private val wingCount: Int = 2

    override fun move() {
        println("펭귄 꽥~")
    }
    
    override val legCount: Int
        get() = super.legCount + this.wingCount

}
```

### 인터페이스
```kotlin
interface Flyable {
    fun act(){
        println("파닥 파닥")
    }
}
```

```kotlin
interface Swimable {
    fun act(){
        println("어푸 어푸")
    }
}
```
- default 키워드 없이 메소드 구현이 가능하다.
- Kotlin에서도 추상메소드를 만들수 있다.

```kotlin
class Penguin(
    species: String
) : Animal(species, 2), Swimable, Flyable {
    private val wingCount: Int = 2

    override fun move() {
        println("펭귄 꽥~")
    }
    
    override val legCount: Int
        get() = super.legCount + this.wingCount
    
    override fun act() {
        super<Swimable>.act()
        super<Flyable>.act()
    }
}
```
- 인터페이스 구현도 `:`를 사용한다.
- 중복되는 인터페이스를 특정할때, `super<타입>.함수`를 사용한다.

```kotlin
interface Swimable {
  val swimAbility: Int
    get() = 3

  fun act(){
    println("어푸 어푸")
  }
}
```
```kotlin
class Penguin(
    species: String
) : Animal(species, 2), Swimable, Flyable {
    private val wingCount: Int = 2

    override fun move() {
        println("펭귄 꽥~")
    }
    
    override val legCount: Int
        get() = super.legCount + this.wingCount
    
    override fun act() {
        super<Swimable>.act()
        super<Flyable>.act()
    }

//    override val swimAbility: Int
//        get() = 3
}
```
- kotlin에서는 backing field가 없는 프로퍼티를 interface에 만들 수 있다.

### 클래스를 상속할 때, 주의할 점
```kotlin
open class Base(
  open val number: Int = 100
){
    init {
      println("Base Class")
      println(number)
    }
}

class Derived(
  override val number: Int
): Base(number){
    init {
        println("Drivered Class")
    }
}

fun main(){
    Derived(300)
}
```

```kotlin
결과 : 
Base Class
0
Derived Class
```
- 상위 클래스에서 하위 클래스 override하고 있는 프로퍼티를 `생성자 블럭`이나 `init 블럭`에서 사용하면 안된다.(이상한 값이 나감)
- 상위 클래스를 설계할 때는 생성자 또는 초기화 블록에 사용되는 프로퍼티에는 open을 피해야한다.

### 상속 관련 지시어 정리
1. `final`: override를 할 수 없게 한다. default로 보이지 않게 존재한다. ( 이 때문에 상속을 위한 open 키워드 사용)
2. `open`: overrride를 열어준다.
3. `abstract`: 반드시 override 해야한다.
4. `override`: 상위 타입을 오버라이드를 하고있다.


## 정리
- 상속 또는 구현을 할 때에 `:`을 사용해야한다.
- 상위 클래스 상속을 구현할 때, 생성자를 반드시 호출해야한다.( override를 필수로 붙여야한다.)
- 추상 멤버가 아니면 기본적으로 오버라이드가 불가능하다 ( final키워드가 default로 붙음 )
  - open키워드를 사용하여 해결할 수 있음
- 상위 클래스의 생성자 또는 초기화 블록에서 open프로퍼티를 사용하면 얘기치 못한 버그가 생길수 있다.
  