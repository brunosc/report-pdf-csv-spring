package br.com.generator.genpdfcsv.filter;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class CustomResponseWrapper extends HttpServletResponseWrapper {

    private PrintWriter writer;
    private ByteOutputStream output;

    public byte[] getBytes() {
        writer.flush();
        return output.getBytes();
    }

    public CustomResponseWrapper(HttpServletResponse response) {
        super(response);
        output = new ByteOutputStream();
        writer = new PrintWriter(output);
    }

    @Override
    public PrintWriter getWriter() {
        return writer;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return  output;
    }

    public static class ByteOutputStream extends ServletOutputStream {
        private ByteArrayOutputStream bos = new ByteArrayOutputStream();

        @Override
        public void write(int b) throws IOException {
            bos.write(b);
        }

        public byte[] getBytes() {
            return bos.toByteArray();
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {

        }
    }

}
