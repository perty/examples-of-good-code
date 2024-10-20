package se.artcomputer.example.of.good.parameters.money;

public record Fee(Money amount) {

    public static Fee of(long amount) {
        return new Fee(Money.of(amount));
    }
}
