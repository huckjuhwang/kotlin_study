## lec18. 컬렉션을 함수형으로 다루는 방법

### 1. 필터와 맵
```kotlin
data class Fruit(
    val id: Long,
    val name: String,
    val factoryPrice: Long,
    val currentPrice: Long,
)
```
#### Q.  `사과만 주세요!` or `사과의 가격들을 알려주세요`
```kotlin
val apples = fruits.filter { fruit -> fruit.name == "사과" }
```
- 사과들만 걸러져서 나오게 되는데
- 만약, 인덱스가 필요하다면, 다음과 같이 프로그래밍 할수 있다.
```kotlin
val apples = fruits.filterIndexed { idx, fruit -> 
    println(idx)
    fruit.name == "사과"
}
```
#### Q. 사과의 가격들만 알고 싶을 경우?
```kotlin
val applePrice = fruits.filter { fruit -> fruit.name == "사과" }
    .map { fruit -> fruit.currentPrice }
```

#### Q. 맵에서 인덱스가 필요한 경우?
```kotlin
val applePrice = fruits.filter { fruit -> fruit.name == "사과" }
    .mapIndexed { idx, fruit ->
        println(idx)
        fruit.currentPrice
    }
```
#### Q. Mapping 결과가 null이 아닌 것만 가져오고 싶다면? 
```kotlin
val applePrice = fruits.filter { fruit -> fruit.name == "사과" }
    .mapNotNull { fruit -> fruit.nullOrValue() }
```

- `filter / filterIndexed `
- `map / mapIndexed / mapNotNull`

### 2. 다양한 컬렉션 처리기능

#### Q. 모든 과일이 사과 인가요? || 
#### Q. 혹시 출고가 10,000원 이상의 과일이 하나라도 있나요?!


```kotlin
val isAllApple = fruits.all { fruit -> fruit.name == "사과" }
```

- `all` : 조건을 모두 만족하면 true, 그렇지 않다면 false
```kotlin
val isAllApple = fruits.none { fruit -> fruit.name == "사과" }
```
- `none` : 조건을 모두 만족하면 false, 그렇지 않다면 true

```kotlin
val isAllApple = fruits.any { fruit -> fruit.factoryPrice >= 10000 }
```
- `any` : 조건을 하나라도 만족하면 true, 그렇지 않다면 false
#### Q. 총 과일 개수가 몇개인지 || 
#### Q. 낮은 가격 순으로 보여주세요 ||
#### Q. 과일이 몇 종류가 있나요
```kotlin
val fruitCount = fruits.count()
```
- `count` : 개수를 센다

```kotlin
val fruitCount = fruits.sortedBy { fruit -> fruit.currentPrice }
val fruitCount = fruits.sortedByDescending { fruit -> fruit.currentPrice }
```
- `sortedBt` : (오름차순) 정렬을 한다.


```kotlin
val distinctFruitNames = fruits.distinctBy { fruit -> fruit.name }
    .map { fruit -> fruit.name }
```

#### Q. 첫번째 과일만 주세요 || 마지막 과일만 주세요 
```kotlin
fruits.first()
fruits.firstOrNull()
```
- `first` : 첫번째 값을 가져온다 ( 무조건 null이 아니여야함 )

  - 즉, 첫번째 값이 Null일 경우 `Exception`이 발생한다. 
- `firstOrNull` : 첫번째 값 또는 null을 가져온다.

```kotlin
fruits.last()
fruits.lastOrNull()
```
- `last` : 마지막 값을 가져온다 ( 무조건 null이 아니여야함 )

    - 즉, 첫번째 값이 Null일 경우 `Exception`이 발생한다.
- `lastOrNull` : 마지막 값 또는 null을 가져온다.

<br>

- `all / none / any`
- `count / sortedBy / sortedByDescending / distinctBy`
- `first / firstOrNull / last / lastOrNull`

### 3. List를 Map으로
#### Q. 과일 이름 -> List<과일>

```kotlin
var map: Map<String, List<Fruit>> = fruits.groupBy { fruit -> fruit.name }
```
- 이름을 기준으로 그룹핑 되게 된다.


#### Q. id -> 과일 Map이 필요해요
```kotlin
val map: Map<Long, Fruit> = fruits.associateBy{ fruit -> fruit.name } 
```
- value쪽에 List가 들어가는것이 아니라 단일객체가 들어가게 됩니다.


#### Q. 과일이름 -> List<출고가> Map이 필요해요
```kotlin
val map: Map<String, List<Long>> = fruits
    .groupBy({ fruit - > fruit.name }, { fruit - > fruit.factoryPrice })
```

#### Q. id -> List<출고가> Map이 필요해요 
```kotlin
val map: Map<String, List<Long>> = fruits
    .groupBy({ fruit - > fruit.name }, { fruit - > fruit.factoryPrice })
```


### 4. 중첩된 컬렉션 처리

```kotlin
val fruitsInList: List<List<Fruit>> = listOf(
  listOf(
    Fruit(1L, "사과", 1000, 1500)
    Fruit(2L, "사과", 1000, 1500)
    Fruit(3L, "사과", 1000, 1500)
    Fruit(4L, "사과", 1000, 1500)
  ),
listOf(
    Fruit(5L, "바나나", 1000, 1500)
    Fruit(6L, "바나나", 1000, 1500)
    Fruit(7L, "바나나 1000, 1500)
),
listOf(
    Fruit(8L, "수박", 10000, 10000)
))
```

이 상황에서, 출고가와 현재가가 동일한 과일을 골라주세요.
```kotlin
val samePriceFruits = fruitsInList.flatMap { list ->
  list.filter { fruit -> fruit.factoryPrice == fruit.currentPrice }
}
```
- 다음과 같이 리펙토링이 가능하다. 
```kotlin
val samePriceFruits = fruitsInList.flatMap { list -> list.samePriceFilter }

val List<Fruit>.samePriceFilter: List<Fruit>
    get() = this.filter(Fruit::isSamePrice)



data class Fruit(
  val id: Long,
  val name: String,
  val factoryPrice: Long,
  val currentPrice: Long,
) {
    val isSamePrice: Boolean 
        get() = factoryPrice == currentPrice
}
```

#### Q. List<List<Fruit>>를 List<Fruit>로 바꿔야할때는?
```kotlin
fruitsInList.flatten()
```


