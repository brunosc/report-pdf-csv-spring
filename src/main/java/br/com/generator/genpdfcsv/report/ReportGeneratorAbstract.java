package br.com.generator.genpdfcsv.report;

import br.com.generator.genpdfcsv.domain.ReportInfo;
import br.com.generator.genpdfcsv.type.ReportExport;
import br.com.generator.genpdfcsv.type.ReportTitle;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class ReportGeneratorAbstract implements ReportGenerator {

    @Override
    public void generate(ReportInfo info) throws Exception {
        validateReportInfo(info);
        createFile(info);
    }

    protected String getValueFromObject(Object obj, String attributeName) {

        for (Field field: obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.getName().equalsIgnoreCase(attributeName))
                    return field.get(obj).toString();
            } catch (IllegalAccessException e) {
                return "";
            }
        }

        return "";
    }

    protected String[] getHeaders(Class<?> classe) {
        List<String> headers = new ArrayList<>();
        for (Field field: classe.getDeclaredFields()) {
            ReportExport reportExport = field.getDeclaredAnnotation(ReportExport.class);
            if (reportExport != null)
                headers.add(reportExport.value());
        }
        return headers.stream().toArray(String[]::new);
    }

    protected String[] getAttributes(Class<?> classe) {
        List<String> attributes = new ArrayList<>();
        for (Field field: classe.getDeclaredFields()) {
            ReportExport reportExport = field.getDeclaredAnnotation(ReportExport.class);
            if (reportExport != null)
                attributes.add(field.getName());
        }
        return attributes.stream().toArray(String[]::new);
    }

    protected String getTitle(Class<?> classe) {
        ReportTitle reportTitle = classe.getDeclaredAnnotation(ReportTitle.class);
        return reportTitle == null ? "" : reportTitle.value();
    }

    protected abstract void validateReportInfo(ReportInfo info) throws Exception;

    protected abstract void createFile(ReportInfo info) throws Exception;
}
