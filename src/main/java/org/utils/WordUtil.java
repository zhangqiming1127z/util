package org.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;
import java.util.UUID;

public class WordUtil {
    public static void downLoadWord(String templatePath,String name,Map map,HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateException {
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
        configuration.setClassForTemplateLoading(WordUtil.class,templatePath);
        Template template = configuration.getTemplate(name);
        File file = new File("D:/"+ UUID.randomUUID().toString() +".doc"); //导出文件
        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            template.process(map,out);
            FileUtil.downloadFile(request,response,file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (out!=null){
                out.close();
            }
        }
    }
}
