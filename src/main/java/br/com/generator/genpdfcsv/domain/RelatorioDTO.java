package br.com.generator.genpdfcsv.domain;

import java.util.List;

public class RelatorioDTO {

    private String title;
    private String[] headers;
    private String[] attributes;
    private List<?> records;
    private String[] footers;

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
}
