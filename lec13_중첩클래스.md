
## lec13. 중첩 클래스
### 중첩 클래스의 종류

#### static을 사용하는 중첩클래스
- 클래스 안에 static을 붙인 클래스는 밖의 클래스를 직접 참조가 불가능 하다!

#### static을 사용하지 않는 중첩클래스
- `내부 클래스`
    - 밖에 클래스를 직접 참조 가능한 클래스
- `지역 클래스`
    - 메소드 내부에 클래스를 정의
- `익명 클래스`
    - 일회성 클래스


- 내부 클래스는 숨겨진 외부 클래스 정보를 가지고 있어, 참조를 해지하지 못하는 경우 메모리 누수가 생길 수 있고, 이를 디버깅 하기 어렵다.
- 내부 클래스의 직렬화 형태가 명확하게 정의되지 않아 직렬화에 있어 제한이 있다.

즉, 내부 클래스가 외부 클래스를 참조할때, 몇가지 문제점이 있다는 이야기이다.

- 클래스 안에 클래스를 만들 때는 static 클래스를 사용하라
```kotlin
// 권장되는 클래스 안의 클래스
fun main() {}

class JavaHouse(
    private val address: String,
    private val livingRoom: LivingRoom
){
    class LivingRoom(
        private var area: Double,
    )
}
```
- 기본적으로 바깥 클래스에 대한 연결이 없는 중첩클래스가 만들어진다.


```kotlin
// 권장되지 않는 클래스 안의 클래스(밖의클래스를 참조하는 경우)
fun main() {}

class JavaHouse(
    private val address: String,
    private val livingRoom: LivingRoom
){
    inner class LivingRoom(
        private var area: Double,
    ){
        val address: String
            get() = this@JavaHouse.address
    }
}
```
- inner키워드 붙이고, this@바깥클래스를 사용하여 참조한다.
 
