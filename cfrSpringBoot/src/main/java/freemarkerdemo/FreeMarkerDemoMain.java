package freemarkerdemo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerDemoMain {
	
	public static void main(String[] args) throws IOException, TemplateException {		
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
		cfg.setDirectoryForTemplateLoading(new File("E:\\"));
		Template template = cfg.getTemplate("freemarker.html");
		Writer out = new FileWriter(new File("E:\\hello.html"));
		Map<String,Object> root = new HashMap<String,Object>();
		FreeMarkerDemoBean vo = new FreeMarkerDemoBean();
        vo.setId("111");
        vo.setName("哈哈哈");
        root.put("voo", vo);
        template.process(root, out);
        out.flush();
        out.close();
        System.out.println("Success.");
	}
}