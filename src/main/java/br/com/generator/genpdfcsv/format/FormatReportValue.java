package br.com.generator.genpdfcsv.format;

public interface FormatReportValue<T> {
    String format(T value);
}
