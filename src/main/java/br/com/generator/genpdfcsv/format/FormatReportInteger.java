package br.com.generator.genpdfcsv.format;

public class FormatReportInteger implements FormatReportValue<Integer> {
    @Override
    public String format(Integer value) {
        return value.toString() + " int";
    }
}
