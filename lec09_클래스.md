
## lec09. 클래스
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
