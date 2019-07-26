/*
package org.utils;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.poi.openxml4j.opc.Configuration;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

public class PdfUtil {
    public public String Pdf(Map map,String coding,String temPath,String temName) throws IOException, TemplateException {
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding(coding);
        configuration.setClassForTemplateLoading(PdfUtil.class,temPath);
        Template template = configuration.getTemplate(temName);
        StringWriter stringWriter = new StringWriter();
        template.process(map,stringWriter);
        return stringWriter.toString();
    }

}
*/
