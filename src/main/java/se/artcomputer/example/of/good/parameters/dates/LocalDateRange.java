package se.artcomputer.example.of.good.parameters.dates;

import java.time.LocalDate;

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
}
