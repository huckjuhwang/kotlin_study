# kotlin_study
코틀린 기본문법을 학습하기 위한 레파지토리입니다.


## lec01. var vs val

var = 수정가능한 변수 <br>
val(final) = 수정 불가능한 변수

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
- val 컬렉션에는 element 를 추가할 수 있다.
- 타입을 명시적으로 작성하지 않아도, 타입이 추론된다.
- 모든 변수를 우선 val로 만들고 꼭 필요한 경우 var로 변경한다(꿀팁)
- kotlin에서의 primitive type, reference type은 하나의 타입으로 구분하며, 즉, 프로그래머가 boxing /]() unboxing을 고려하지 않아도 되도록 코틀린이 알아서 상황에 따라서 바꿔서 사용해준다. 
- null이 들어갈 수 있는 변수는 타입 뒤에 ? 를 붙여주어야 한다. (아예 다른 타입으로 간주)


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
- 객체끼리의 연산자를 코틀린에서는 직접 정의할 수 이싿.
- 