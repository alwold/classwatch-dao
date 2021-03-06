package com.alwold.classwatch.school;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;

/**
 *
 * @author alwold
 */
public abstract class BaseSchoolPlugin implements SchoolPlugin {
	private static Logger logger = Logger.getLogger(BaseSchoolPlugin.class);
	
	protected void serializeDoc(Document doc) {
		if (logger.isTraceEnabled()) {
			try {
				XMLSerializer xmls = new XMLSerializer(System.out, new OutputFormat());
				xmls.serialize(doc);
				File file = File.createTempFile("debug", ".html");
				xmls = new XMLSerializer(new FileWriter(file), new OutputFormat());
				xmls.serialize(doc);
				System.out.println("saved to file: "+file.getPath());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
}
