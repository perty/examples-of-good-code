package se.artcomputer.example.of.good.parameters.money;

public record Interest(Money amount) {

    public static Interest of(long l) {
        return new Interest(Money.of(l));
    }
}
