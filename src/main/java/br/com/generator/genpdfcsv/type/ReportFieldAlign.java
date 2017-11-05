package br.com.generator.genpdfcsv.type;

public enum ReportFieldAlign {

    LEFT("text-align: left;"),
    CENTER("text-align: center;"),
    RIGHT("text-align: right;");

    private final String align;

    ReportFieldAlign(String align) {
        this.align = align;
    }

    public String getValue() {
        return align;
    }

}
