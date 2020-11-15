package com.app.xml;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class CreateXmlFIle {


    public static void createXml(String content) throws Exception {

        try (OutputStream out = new FileOutputStream(new File("1.xml"))) {
            out.write(content.getBytes());
        }
    }

    public static void createXml() {

        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Source xslt = new StreamSource(CreateXmlFIle.class.getResourceAsStream("/Transform.xsl"));
            Transformer transformer = factory.newTransformer(xslt);
            Source xml = new StreamSource(new File("1.xml"));
            transformer.transform(xml, new StreamResult(new File("2.xml")));
            System.out.println("File created successfully");
        } catch (Exception e) {

            throw new RuntimeException(e);
        }


    }
}
