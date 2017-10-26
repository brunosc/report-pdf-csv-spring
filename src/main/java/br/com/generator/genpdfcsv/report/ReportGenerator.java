package br.com.generator.genpdfcsv.report;

import br.com.generator.genpdfcsv.domain.ReportInfo;

import java.io.ByteArrayOutputStream;

public interface ReportGenerator {

    void generate(ReportInfo info) throws Exception;

}
