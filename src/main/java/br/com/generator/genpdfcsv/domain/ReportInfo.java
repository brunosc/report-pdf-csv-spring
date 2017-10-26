package br.com.generator.genpdfcsv.domain;

import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfPage;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

public class ReportInfo {

    private Class<?> clazz;
    private List<?> list;
    private String[] footers;
    private PdfNumber orientation;
    private PrintWriter writer;
    private OutputStream outputStream;

    private ReportInfo(Builder builder) {
        list = builder.list;
        footers = builder.footers;
        orientation = builder.orientation;
        writer = builder.writer;
        outputStream = builder.outputStream;
        clazz = builder.clazz;
    }

    public static class Builder {

        private Class<?> clazz;
        private List<?> list;

        // Opcionais
        private String[] footers = null;
        private PdfNumber orientation = PdfPage.PORTRAIT;
        private PrintWriter writer = null;
        private OutputStream outputStream = null;

        public Builder list(List<?> list) {
            this.list = list;
            return this;
        }

        public Builder footers(String[] footers) {
            this.footers = footers;
            return this;
        }

        public Builder orientation(PdfNumber orientation) {
            this.orientation = orientation;
            return this;
        }

        public Builder writer(PrintWriter writer) {
            this.writer = writer;
            return this;
        }

        public Builder outputStream(OutputStream outputStream) {
            this.outputStream = outputStream;
            return this;
        }

        public Builder clazz(Class<?> clazz) {
            this.clazz = clazz;
            return this;
        }

        public ReportInfo build() {
            return new ReportInfo(this);
        }
    }

    public List<?> getList() {
        return list;
    }

    public String[] getFooters() {
        return footers;
    }

    public PrintWriter getWriter() { return writer; }

    public OutputStream getOutputStream() { return outputStream; }

    public PdfNumber getOrientation() { return orientation; }

    public Class<?> getClazz() { return clazz; }
}
