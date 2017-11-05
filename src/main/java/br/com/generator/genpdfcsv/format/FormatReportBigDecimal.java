package br.com.generator.genpdfcsv.format;

import java.math.BigDecimal;

public class FormatReportBigDecimal implements FormatReportValue<BigDecimal> {

    @Override
    public String format(BigDecimal value) {
        return value.doubleValue() + " bigDecimal";
    }

}
