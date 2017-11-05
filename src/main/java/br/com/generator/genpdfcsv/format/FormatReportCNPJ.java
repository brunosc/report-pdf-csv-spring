package br.com.generator.genpdfcsv.format;

public class FormatReportCNPJ implements FormatReportValue<String> {

    @Override
    public String format(String value) {
        return "CNPJ Formatado";
    }

}
