package se.artcomputer.example.of.good.parameters;

import se.artcomputer.example.of.good.parameters.dates.LocalDateRange;
import se.artcomputer.example.of.good.parameters.dates.Service;
import se.artcomputer.example.of.good.parameters.money.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Client {

    public void moneyDealing() {
        MoneyDealing parameters = new MoneyDealing();

        parameters.method1(new BigDecimal(100L), new BigDecimal(200L), new BigDecimal(300));

        parameters.method2(Money.of(100L), Money.of(200L), Money.of(300));

        parameters.method3(Interest.of(100L), Amortization.of(200L), Fee.of(300));
    }

    public void makeDateRange() {
        Service service = new Service();

        service.method1(LocalDate.of(2024, 10, 20), LocalDate.of(2024, 10, 30));

        LocalDateRange localDateRange = LocalDateRange
                .start(LocalDate.of(2024, 10, 20))
                .end(LocalDate.of(2024, 10, 30));

        service.method2(localDateRange);
    }
}
