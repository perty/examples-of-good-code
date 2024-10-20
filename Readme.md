# Examples of good code

A bit ambitious, but I have some ideas on the mini-level of design.

The context is a type safe language, in this case Java, giving us the short feedback loop of the compiler.

* Parameters - how to design your interfaces.
* State - make impossible states impossible.

## Parameters

In any system of considerable size, you will have to deal with parameters, their
order, their type, and their meaning. When calling a function, you should not need to
waste energy on figuring out what the parameters are, and what they mean.

### Parameters should be of the different types

Having the same type gives the risk of mixing up the order of the parameters.

Here are two examples of bad parameter design found in the wild:

```java

public void method(BigDecimal interest, BigDecimal amortization, BigDecimal fee) {
    // ...
}

public void method1(LocalDate start, LocalDate end) {
    // ...
}
```
In the first example, not only are method1 exposed to the risk of mixing up the order of the parameters, 
but the BigDecimal class allows for huge values, which is not what we want in this case.

In the second example, we could, by accident, switch the start and end dates.

### Better

Let's make the parameters type safe, and give them a name that makes sense.

```java
public void method2(Money interest, Money amortization, Money fee) {

}
```
They are now of the type Money, which is a class that only allows for positive values. We still can
mix them up.

### Even better

`Money` is like a primitive but in the financial domain. Let's state the intention of each value
and rise the level of abstraction.

```java
public void method3(Interest interest, Amortization amortization, Fee fee) {

}
```
### Representing a date range

Instead of having two dates, we can represent a date range. The method could of course be responsible
for checking that the start date is before the end date. But, it has other responsibilities, and we
wish to focus on that. And there might be other methods that have the same need.

Therefor we introduce a DateRange class and guarantee that the start date is before the end date.

```java
public void method2(LocalDateRange dateRange) {

}

public class LocalDateRange {
    private final LocalDate start;
    private final LocalDate end;

    private LocalDateRange(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    public static DateRangeBuilder start(LocalDate start) {
        return new DateRangeBuilder(start);
    }

    public static class DateRangeBuilder {
        private final LocalDate start;

        public DateRangeBuilder(LocalDate start) {
            this.start = start;
        }

        public LocalDateRange end(LocalDate end) {
            if(end.isAfter(start)) {
                return new LocalDateRange(start, end);
            }
            throw new IllegalArgumentException("End date must be after start date");
        }
    }
    ...
```

Here we use a builder pattern to lessen the risk of mixing up the start and end date. We also guarantee
that the end date is after the start date but only at runtime.

Here's how you would use it:

```java
LocalDateRange localDateRange = LocalDateRange
        .start(LocalDate.of(2024, 10, 20))
        .end(LocalDate.of(2024, 10, 30));
```

## State

When designing your classes, you should make impossible states impossible. This means that you should
design your classes in such a way that they can never be in an invalid state.

Consider a traffic light. It can be in three lights: Red, Yellow, and Green. A naive implementation
would be to have three boolean fields, red, yellow, and green.

```java
public class TrafficLight1 {
    private boolean red;
    private boolean yellow;
    private boolean green;
    ...
```

This allows for 8 states, most of which are invalid. Instead, we can use an enum.

```java
public class TrafficLight2 {
    private State state;

    public enum State {
        RED( true, false, false),
        RED_YELLOW( true, true, false),
        GREEN( false, false, true),
        YELLOW( false, true, false);

        final boolean red;
        final boolean yellow;
        final boolean green;

        State( boolean red, boolean yellow, boolean green) {
            this.red = red;
            this.yellow = yellow;
            this.green = green;
        }
    }
    ...
```

We are now down to 4 states, all of which are valid. But one problem remains: we can still do state
transitions that are invalid. We can solve this by using a state machine.

```java
public class TrafficLight3 {
    private State state;

    public enum State {
        RED,
        RED_YELLOW,
        GREEN,
        YELLOW
    }

    public void nextState() {
        state = switch (state) {
            case RED -> RED_YELLOW;
            case RED_YELLOW -> GREEN;
            case GREEN -> YELLOW;
            case YELLOW -> RED;
        };
    }
    ...
```