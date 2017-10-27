package br.com.generator.genpdfcsv.filter;

import br.com.generator.genpdfcsv.dao.NotaFiscalDAO;
import br.com.generator.genpdfcsv.domain.NotaFiscal;
import br.com.generator.genpdfcsv.domain.ReportInfo;
import br.com.generator.genpdfcsv.report.ReportGenerator;
import br.com.generator.genpdfcsv.report.impl.ReportGeneratorPDF;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@ControllerAdvice
public class CustomResponseAdvice implements ResponseBodyAdvice<List> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public List beforeBodyWrite(List responseEntity, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        System.out.println("before write = " + responseEntity.size());

        HttpServletRequest servletRequest = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest();

        Boolean report = ServletRequestUtils.getBooleanParameter(servletRequest, "report", false);

        if (report) {

            try {

                mediaType = MediaType.APPLICATION_PDF;
                serverHttpResponse.getHeaders().add("Content-Disposition", "attachment; filename=\"pdf-file.pdf\"");

                ReportGenerator pdfGen = new ReportGeneratorPDF();
                ReportInfo infoPDF = getInfoPDF(serverHttpResponse.getBody());

                pdfGen.generate(infoPDF);

                return null;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("    >> report = " + report);

        return responseEntity;
    }

    private ReportInfo getInfoPDF(OutputStream outputStream) {
        List<NotaFiscal> list = NotaFiscalDAO.findAll();

        ReportInfo info = new ReportInfo.Builder()
                .list(list)
                .outputStream(outputStream)
                .clazz(NotaFiscal.class)
//                                        .orientation(PdfPage.LANDSCAPE)
                .build();

        return info;
    }
}
