package br.com.generator.genpdfcsv.format;

public class FormatReportDefault implements FormatReportValue<Object> {
    @Override
    public String format(Object value) {
        return value.toString();
    }
}
