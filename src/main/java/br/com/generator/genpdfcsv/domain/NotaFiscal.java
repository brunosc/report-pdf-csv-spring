package br.com.generator.genpdfcsv.domain;

import br.com.generator.genpdfcsv.type.ReportExport;
import br.com.generator.genpdfcsv.type.ReportTitle;

@ReportTitle("Notas Fiscais")
public class NotaFiscal {

    private int id;

    @ReportExport("Descrição")
    private String descricao;

    @ReportExport("Empresa")
    private String empresa;

    @ReportExport("Complemento")
    private String complemento;

    @ReportExport("Valor")
    private double valor;

    public NotaFiscal(int id, String descricao, String empresa, String complemento, double valor) {
        this.id = id;
        this.descricao = descricao;
        this.empresa = empresa;
        this.complemento = complemento;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public String getEmpresa() { return empresa; }

    public String getDescricao() {
        return descricao;
    }

    public String getComplemento() {
        return complemento;
    }

    public double getValor() {
        return valor;
    }

}
