package br.com.generator.genpdfcsv.domain;

import br.com.generator.genpdfcsv.type.RelatorioItemDTO;

import java.util.List;
import java.util.Map;

public class RelatorioDTO {

    private String title;
    private String[] headers;
    private String[] attributes;
    private List<?> records;
    private String[] footers;

    private List<Map<String, RelatorioItemDTO>> aui;

    public RelatorioDTO(String title, String[] headers, String[] attributes, List<?> records, String[] footers) {
        this.title = title;
        this.headers = headers;
        this.attributes = attributes;
        this.records = records;
        this.footers = footers;
    }

    public String getTitle() {
        return title;
    }

    public String[] getHeaders() {
        return headers;
    }

    public String[] getAttributes() {
        return attributes;
    }

    public String[] getFooters() {
        return footers;
    }

    public List<?> getRecords() {
        return records;
    }

    public List<Map<String, RelatorioItemDTO>> getAui() {
        return aui;
    }

    public void setAui(List<Map<String, RelatorioItemDTO>> aui) {
        this.aui = aui;
    }
}
