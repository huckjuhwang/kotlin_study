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

### 정리
- val 컬렉션에는 `element` 를 추가할 수 있다.
- 타입을 명시적으로 작성하지 않아도, 타입이 추론된다.
- 모든 변수를 `우선 val로 만들고` 꼭 필요한 경우 `var로 변경`한다(`꿀팁!!!`)
- kotlin에서의 `primitive type, reference type`은 하나의 타입으로 구분하며,
- 즉, 프로그래머가 `boxing / unboxing`을 고려하지 않아도 되도록 코틀린이 알아서 상황에 따라서 바꿔서 사용해준다.
- null이 들어갈 수 있는 변수는 타입 뒤에 `?` 를 붙여주어야 한다. (아예 다른 타입으로 간주)

