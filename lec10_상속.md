
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


### 정리
- 상속 또는 구현을 할 때에 `:`을 사용해야한다.
- 상위 클래스 상속을 구현할 때, 생성자를 반드시 호출해야한다.( override를 필수로 붙여야한다.)
- 추상 멤버가 아니면 기본적으로 오버라이드가 불가능하다 ( final키워드가 default로 붙음 )
  - open키워드를 사용하여 해결할 수 있음
- 상위 클래스의 생성자 또는 초기화 블록에서 open프로퍼티를 사용하면 얘기치 못한 버그가 생길수 있다.
  

