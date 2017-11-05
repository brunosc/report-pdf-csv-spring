package br.com.generator.genpdfcsv.type;

import br.com.generator.genpdfcsv.format.*;

public enum ReportFormatType {

    DEFAULT(new FormatReportDefault()),
    INTEGER(new FormatReportInteger()),
    BIG_DECIMAL(new FormatReportBigDecimal()),
    CNPJ(new FormatReportCNPJ());

    private FormatReportValue format;

    ReportFormatType(FormatReportValue format) {
        this.format = format;
    }

    public String format(Object value) {
        return this.format.format(value);
    }
}
