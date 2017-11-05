package br.com.generator.genpdfcsv.report.impl;

import br.com.generator.genpdfcsv.dao.NotaFiscalDAO;
import br.com.generator.genpdfcsv.domain.NotaFiscal;
import br.com.generator.genpdfcsv.domain.RelatorioDTO;
import br.com.generator.genpdfcsv.domain.ReportInfo;
import br.com.generator.genpdfcsv.report.ReportGeneratorAbstract;
import br.com.generator.genpdfcsv.type.RelatorioItemDTO;
import br.com.generator.genpdfcsv.type.ReportExport;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.*;
import com.itextpdf.tool.xml.Pipeline;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFilesImpl;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.TagProcessorFactory;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportGeneratorPDF extends ReportGeneratorAbstract {

    private Document document;
    private PdfWriter writer;

    private List<Map<String, RelatorioItemDTO>> getFormattedList(List<?> list, String[] attributes) {

        Map<String, RelatorioItemDTO> map = null;
        List<Map<String, RelatorioItemDTO>> result = new ArrayList<>();

        for (Object item: list) {

            map = new HashMap<>();

            for (Field field: item.getClass().getDeclaredFields()) {

                field.setAccessible(true);

                ReportExport reportExport = field.getDeclaredAnnotation(ReportExport.class);

                if (reportExport != null) {

                    try {

                        Object value = field.get(item);

                        RelatorioItemDTO itemDTO = new RelatorioItemDTO(reportExport.format().format(value), reportExport.align().getValue());

                        map.put(field.getName(), itemDTO);

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        map.put(field.getName(), RelatorioItemDTO.newBlankInstance());
                    }

                }

            }

            result.add(map);

        }

        return result;
    }

    @Override
    protected void createFile(ReportInfo info) throws Exception {
        String html = parseHtml(info.getList(), info.getClazz());
        geraPdfByHtml(info, html);
    }

    private String parseHtml(List<?> list, Class<?> clazz) throws Exception {

        String templateName = "/home/bruno/dev/repositories/report-pdf-csv-spring/src/main/resources/report-matriz.html";

        String docHtml = FileUtils.readFileToString(new File(templateName), StandardCharsets.UTF_8);

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_24);
        StringTemplateLoader loader = new StringTemplateLoader();
        loader.putTemplate("template", docHtml);
        cfg.setTemplateLoader(loader);

        try {

            freemarker.template.Template template = cfg.getTemplate("template");

            String title = getTitle(clazz);
            String[] columnHeaders = getHeaders(clazz);
            String[] attributes = getAttributes(clazz);
            String[] footers = null;

            RelatorioDTO dto = new RelatorioDTO(title, columnHeaders, attributes, list, footers);
            dto.setAui(getFormattedList(list, attributes));

            Map<String, Object> data = new HashMap<>();
            data.put("data", dto);

            Writer out = new StringWriter();
            template.process(data, out);
            out.flush();
            out.close();

            return out.toString();

        } catch (Exception e) {

            StringBuffer sb = new StringBuffer();
            sb.append("<html><body>");
            sb.append(e.getMessage());
            sb.append("</body></html>");
            return sb.toString();
        }
    }

    private void geraPdfByHtml(ReportInfo info, String html) throws Exception {

        openFile(info.getOutputStream(), info.getOrientation());

        final TagProcessorFactory tagProcessorFactory = Tags.getHtmlTagProcessorFactory();

        final CssFilesImpl cssFiles = new CssFilesImpl();
        cssFiles.add(XMLWorkerHelper.getInstance().getDefaultCSS());
        final StyleAttrCSSResolver cssResolver = new StyleAttrCSSResolver(cssFiles);

        final HtmlPipelineContext hpc = new HtmlPipelineContext(new CssAppliersImpl(new XMLWorkerFontProvider()));
        hpc.setAcceptUnknown(true).autoBookmark(true).setTagFactory(tagProcessorFactory);

        final HtmlPipeline htmlPipeline = new HtmlPipeline(hpc, new PdfWriterPipeline(document, writer));
        final Pipeline<?> pipeline = new CssResolverPipeline(cssResolver, htmlPipeline);

        final XMLWorker worker = new XMLWorker(pipeline, true);
        XMLParser p = new XMLParser(worker);

        ByteArrayInputStream is = new ByteArrayInputStream(html.getBytes());
        p.parse(is);

        document.close();
        info.getOutputStream().flush();
        info.getOutputStream().close();

    }

    private void openFile(OutputStream out, PdfNumber orientation) throws Exception {

        document = new Document();
        writer = PdfWriter.getInstance(document, out);

        Rotate event = new Rotate();

        event.setOrientation(orientation);

        writer.setPageEvent(event);

        document.open();
    }

    @Override
    protected void validateReportInfo(ReportInfo info) throws Exception {
        if (info.getOutputStream() == null)
            throw new Exception("OutputStream necessario para PDF");
    }

    public class Rotate extends PdfPageEventHelper {
        protected PdfNumber orientation = PdfPage.PORTRAIT;
        public void setOrientation(PdfNumber orientation) {
            this.orientation = orientation;
        }
        @Override
        public void onStartPage(PdfWriter writer, Document document) {
            writer.addPageDictEntry(PdfName.ROTATE, orientation);
        }
    }

}
