package se.artcomputer.example.of.good.parameters;

public record FirstName(String value) {

    public static final double MAX_LENGTH = 50;

    public FirstName {
        if (value == null || value.isBlank() || value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("First name must not be null or empty and shorter than " + MAX_LENGTH + " characters");
        }
    }
}
