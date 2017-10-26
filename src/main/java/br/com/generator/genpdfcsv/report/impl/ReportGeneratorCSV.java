package br.com.generator.genpdfcsv.report.impl;

import br.com.generator.genpdfcsv.domain.ReportInfo;
import br.com.generator.genpdfcsv.report.ReportGeneratorAbstract;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ReportGeneratorCSV extends ReportGeneratorAbstract {

    private static final String NEW_LINE_SEPARATOR = "\n";

    private CSVPrinter csvPrinter = null;
    private CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

    @Override
    protected void createFile(ReportInfo info) throws Exception {

        try {

            createFile(info.getWriter());
            createHeader(getHeaders(info.getClazz()));
            createBody(info.getList(), getAttributes(info.getClazz()));
//            createFooter(info.getFooters());

        } finally {
            try {
//                fileWriter.flush();
//                fileWriter.close();
                csvPrinter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
                e.printStackTrace();
            }
        }

    }

    private void createFile(PrintWriter writer) throws Exception {
        csvPrinter = new CSVPrinter(writer, csvFileFormat);
    }

    private void createHeader(String[] columnHeaders) throws Exception {
        csvPrinter.printRecord(columnHeaders);
    }

    private void createFooter(String[] footer) throws Exception {
        csvPrinter.printRecord(footer);
    }

    private void createBody(List<?> list, String[] attributes) throws Exception {
        for (Object obj: list) {
            List record = new ArrayList();

            for (String attribute: attributes)
                record.add(getValueFromObject(obj, attribute));

            // printa uma linha
            csvPrinter.printRecord(record);

        }
    }

    @Override
    protected void validateReportInfo(ReportInfo info) throws Exception {
        if (info.getWriter() == null)
            throw new Exception("Writer necessairo para o tipo CSV");
    }
}
