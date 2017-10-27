package br.com.generator.genpdfcsv.resource;

import br.com.generator.genpdfcsv.dao.NotaFiscalDAO;
import br.com.generator.genpdfcsv.domain.NotaFiscal;
import br.com.generator.genpdfcsv.domain.ReportInfo;
import br.com.generator.genpdfcsv.report.ReportGenerator;
import br.com.generator.genpdfcsv.report.impl.ReportGeneratorCSV;
import br.com.generator.genpdfcsv.report.impl.ReportGeneratorPDF;
import com.itextpdf.text.pdf.PdfPage;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/report")
public class ReportResource {

    @RequestMapping(value = "/pdf", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<?> pdf(HttpServletResponse response) {

        try {

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"pdf-file.pdf\"");

            ReportGenerator pdfGen = new ReportGeneratorPDF();

            ReportInfo infoPDF = getInfoPDF(response.getOutputStream());

            pdfGen.generate(infoPDF);

            response.flushBuffer();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/csv", method = RequestMethod.GET, produces = "text/csv")
    public void csv(HttpServletResponse response) {

        try {

            ReportInfo info = getInfoCSV(response.getWriter());
            ReportGenerator csvGen = new ReportGeneratorCSV();

            response.setHeader("Content-Disposition", "attachment; filename=\"csv-file.csv\"");

            csvGen.generate(info);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private ReportInfo getInfoCSV(PrintWriter writer) {
        List<NotaFiscal> list = NotaFiscalDAO.findAll();

        ReportInfo info = new ReportInfo.Builder()
                .list(list)
                .writer(writer)
                .clazz(NotaFiscal.class)
                .build();

        return info;
    }

    @RequestMapping(value = "/int", method = RequestMethod.GET)
    public ResponseEntity<List<NotaFiscal>> intercept(@RequestParam("report") String report) {

        List<NotaFiscal> list = NotaFiscalDAO.findAll();

        return ResponseEntity.ok(list);

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
