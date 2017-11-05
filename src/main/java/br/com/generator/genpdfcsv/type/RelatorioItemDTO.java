package br.com.generator.genpdfcsv.type;

public class RelatorioItemDTO {

    public RelatorioItemDTO(String value, String css) {
        this.value = value;
        this.css = css;
    }

    private String value;
    private String css;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCss() {
        return css;
    }

    public void setCss(String css) {
        this.css = css;
    }

    public static RelatorioItemDTO newBlankInstance() {
        return new RelatorioItemDTO("", "");
    }
}
