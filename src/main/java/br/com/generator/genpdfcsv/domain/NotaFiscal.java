package br.com.generator.genpdfcsv.domain;

import br.com.generator.genpdfcsv.type.ReportExport;
import br.com.generator.genpdfcsv.type.ReportFieldAlign;
import br.com.generator.genpdfcsv.type.ReportFormatType;
import br.com.generator.genpdfcsv.type.ReportTitle;

import java.math.BigDecimal;

@ReportTitle("Notas Fiscais")
public class NotaFiscal {

    @ReportExport(value = "ID", format = ReportFormatType.INTEGER)
    private Integer id;

    @ReportExport("Descrição")
    private String descricao;

    @ReportExport("Empresa")
    private String empresa;

    @ReportExport(value = "CNPJ", format = ReportFormatType.CNPJ)
    private String cnpj;

    @ReportExport(value = "Valor", format = ReportFormatType.BIG_DECIMAL, align = ReportFieldAlign.RIGHT)
    private BigDecimal valor;

    public NotaFiscal(int id, String descricao, String empresa, String cnpj, BigDecimal valor) {
        this.id = id;
        this.descricao = descricao;
        this.empresa = empresa;
        this.cnpj = cnpj;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public String getEmpresa() { return empresa; }

    public String getDescricao() {
        return descricao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public BigDecimal getValor() {
        return valor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[id=");
        sb.append(id);
        sb.append(", descricao=");
        sb.append(descricao);
        sb.append(", empresa=");
        sb.append(empresa);
        sb.append(", cnpj=");
        sb.append(cnpj);
        sb.append(", valor=");
        sb.append(valor);
        sb.append("]");

        return sb.toString();
    }
}
