## lec16. 다양한 함수 다루는 방법

### 1. 확장함수
- 기존 Java 코드 위에 `자연스럽게 코틀린 코드를 추가`할수 없을까??
- 어떤 클래스안에 있는 메소드처럼 호출할 수 있지만, 함수는 밖에 만들 수 있게 하자!


- String클래스 안에 문자열의 가장 마지막 문자를 가져오는 함수를 구현을 해보면 다음과 같다.
```kotlin
fun main(){
    val str = "ABC"
    println(str.lastChar()) // C
}

fun String.lastChar(): Char {
    return this[this.length-1]
    // 수신객체
}


// 확장하려는 클래스 => 수신객체 타입
fun 확장하려는클래스.함수이름(파라미터): 리턴타입 { 
    //this를 통하여 인스턴스를 통해서 접근(this를 수신객체라고 함.)
}


```
- 함수 안에서는 this를 통하여 인스턴스를 통해서 접근
- 원래 String에 있는 멤버함수처럼 사용할수 있다.
####  Q. 엇..? 확장함수가 public이고, 확장함수에서 수신객체 클래스의 private함수를 가져오면 캡슐화가 깨지는거 아니야??

- 맞습니다. public의 확장함수를 통해서 private함수를 가져오게되면 캡슐화가 깨지게 됩니다.
- 이 때문에 코틀린에서는 `(확장함수 내에서) private 또는 protected 멤버를 가져올 수 없습니다.`

#### Q. 멤버함수와 확장함수의 시그니처가 같다면?

```java
public class Person {
    private final String firstName;
    private int age;

    public Person(String firstName, int age) {
        this.firstName = firstName;
        this.age = age;
    }
    
    public int nextYearAge() {
        System.out.println("멤버 함수");
        return this.age + 1;
    }
}
```
```kotlin
fun Person.nextYearAge(): Int {
    println("확장 함수")
    return this.age + 1
}

fun main() {
    val person = Person("A", 100)
    println(person.nextYearAge())
}
```
- 이 경우 어떤 함수가 호출되는가?? 
  
  - `멤버함수가 우선적으로 호출`된다.

### 확장함수 정리

- 확장함수는 원본 클래스의 private, protected멤버로 접근이 안된다!
- 멤버함수, 확장함수 두개가 동일하게 존재한다면, 멤버함수에 우선권이 있다.
- 확장함수는 현재 타입을 기준으로 호출한다.

```kotlin
val String.lastChar: Char 
    get() = this[this.length -1]
```
- 확장 프로퍼티의 원리는 `확장함수 + custom getter`와 동일하다.

### 2. infix함수
- 함수를 호출하는 새로운 방법!
- `변수.함수이름(argument)` 대신 `변수 함수이름 argument`로 호출이 가능하다
```kotlin
fun Int.add(other: Int): Int{
    return this + order
}

infix fun Int.add2(other: Int): Int{
    return this + order
}

fun main() {
    3.add(4)
    3.add2(4)
    3 add2 4
}

```
- infix는 멤버함수에도 붙일 수 있다.

### 3. inline함수
- 함수가 호출되는 대신, 함수를 호출한 지점에 함수 본문을 그대로 복붙하고 싶은 경우!
```kotlin
fun main(){
    3.add(4)
}

inline fun Int.add(other: Int): Int {
    return this + other
}
```
- 함수를 파라미터로 전달할 때에 오버헤드를 줄일 수 있다.
- 하지만 inline 함수의 사용은 성능 측정과 함께 신중하게 사용이 되어야합니다.

### 4. 지역함수
- 함수안에 함수를 선언할 수 있다.
- 함수로 추출하면 좋을 것 같은데, 이 함수를 지금 함수 내에서만 사용하고 싶을때, 사용하면 좋다.
- 하지만 코드가 깔끔하지 않다... 주로 사용하지는 않는다.

### 정리
- Java코드가 있는 상황에서, kotlin코드로 추가 기능 개발을 하기위해 `확장함수`와 `확장 프로퍼티`가 등장했다.
  ```kotlin
  // 확장하려는 클래스 => 수신객체 타입
  fun 확장하려는클래스.함수이름(파라미터): 리턴타입 { 
      //this를 통하여 인스턴스를 통해서 접근(this를 수신객체라고 함.)
  }
  ```
- 확장함수는 원본 클래스의 `private, protected`멤버 접근이 안된다!.
- 멤버함수, 확장함수 중 멤버함수에 우선권이 있다.
- 확장함수는 현재 타입을 기준으로 호출된다.
- 함수 호출 방식을 바꾸어주는 i`nfix함수` 존재한다.
