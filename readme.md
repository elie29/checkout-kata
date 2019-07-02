# Best practices of OO and Object Calisthenics

## SOLID
  1. Single responsibility principle
  2. Open/closed principle
  3. Liskov substitution principle
  4. Interface segregation principle
  5. Dependency inversion principle

## 9 OC Rules
  1. Only One Level Of Indentation Per Method
  2. Don't Use The ELSE Keyword
  3. Wrap All Primitives And Strings
  4. First-Class Collections
  5. One Dot Per Line
  6. Don't Abbreviate
  7. Keep All Entities Small
  8. No Classes With More Than Two Instance Variables
  9. No Getters/Setters/Properties

## TDD
   1. Write the test
   2. Make it compile
   3. Watch it fail
   4. Make it pass
   5. Refactor
      a. SOLID
      b. 9 OC Rules
   6. Replay test

## Supermarket Pricing Problem
> The purpose is to implement the code for a supermarket checkout that calculates the total price of a number of items.

### Acceptance Criteria
  1. API accepts items in any order
  2. API accepts the same item several time
  3. API total method reflects the total amount of scanned items at any time.
  4. Pricing changes frequently.
  5. Special discount or others features could be requested later.

#### Checkout Calculation Sample
    Item  Price TTC  Special Price
    ----  ----------  -------------
    A     50          3 for 130
    B     30          2 for 50
    C     20
    D     15

#### API (KISS)
    Checkout checkout = new Checkout();
    checkout.scan('A')
    checkout.scan('B');
    checkout.total(); // would return 80
    checkout.scan('D');
    checkout.total() // would return 95

## Run the tests
mvn clean test

## Run the program
mvn clean install && java -jar target/checkout-1.0-SNAPSHOT.jar
