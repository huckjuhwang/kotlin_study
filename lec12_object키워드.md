

## lec 12. object 키워드

### static 함수와 변수
```java
public class JavaPerson{
    private static final int MIN_AGE = 1;

    public static JavaPerson newBaby(String name) {
        return new JavaPerson(name, MIN_AGE);
    }

    private final String name;
    private int age;

    public JavaPerson(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

```kotlin
class Person private constructor(
    var name: String,
    var age: Int
) {

    companion object {
        private const val MIN_AGE = 1
        fun newBaby(name: String): Person {
            return Person(name, MIN_AGE)
        }
    }
}
```
- Kotlin에서는 static대신 `companion object`를 사용한다
- `static`: 클래스가 인스턴스화 될때, 새로운 값이 복제되는게 아니라 정적으로 인스턴스끼리의 값을 공유
- `companion object`: 클래스와 동행하는 유일한 오브젝트

```kotlin
class Person private constructor(
  var name: String,
  var age: Int
) {

  // name : factory
  companion object Factory : Log {
    private const val MIN_AGE = 1
    fun newBaby(name: String): Person {
      return Person(name, MIN_AGE)
    }

    override fun log() {
      println("나는 person 클래스의 동행객체이다.")
    }
  }
}

interface Log {
  fun log()
}
```
- `companion object`는 하나의 객체로 간주된다. 때문에, 이름을 붙일 수도 있고, interface를 구현할 수도 있다.
- `companion object`의 유틸성 함수들을 넣어도 되지만, 최상단 파일을 활용하는것을 추천!


### 싱글톤
```java
public class JavaSingleton {

  private static final JavaSingleton INSTANCE = new JavaSingleton();

  private JavaSingleton() {}

  public static JavaSingleton getInstance() {
    return INSTANCE;
  }
}

```
```kotlin
fun main() {
  println(Singleton.a)    // 0
  Singleton.a += 10
  println(Singleton.a)    // 10
}

object Singleton{
  var a: Int= 0
}
```


### 익명 클래스
- 특정 인터페이스나 클래스를 상속받은 구현체를 일회성으로 사용할 때 쓰는 클래스

```java
public interface Movable{
    void move();
    
    void fly();
}
```


```kotlin
fun main() {
  moveSomething(object : Movable {
    override fun move(){
        println("무브 무브!")
    }

    override fun fly(){
        println("난다난다.")
    }
  })
}

private fun moveSomething(movable: Movable){
  movable.move()
  movable.fly()
}
```


### 정리
- Java의 `static` 변수와 함수를 만드려면, Kotlin에서는 `companion object`를 사용해야한다.
- `companion object`도 하나의 객체로 간주되기 때문에 이름을 붙일 수 있고, 다른 타입을 상속받을 수 도있다.
- 싱글톤 클래스를 만들 때 `object` 키워드를 사용한다.
- 익명 클래스를 만들 때, `object : 타입`을 사용한다.

