package se.artcomputer.example.of.good.parameters.money;

public record Amortization(Money amount) {

    public static Amortization of(long amount) {
        return new Amortization(Money.of(amount));
    }
}
