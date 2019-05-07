package ru.job4j.sqlxmlxstl;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ConvertXSTL {
    /**
     * Method convert. Конвертирует XML в XLTS по схеме-шаблону
     * @param source исходный XML файл
     * @param dest конечный XLTS файл
     * @param scheme шаблон для конвертации
     */
    public static void convert(File source, File dest, File scheme) {
        try {
            StreamSource styleSource = new StreamSource(new FileInputStream(scheme));
            Transformer transformer = TransformerFactory.newInstance().newTransformer(styleSource);
            StreamSource streamSource = new StreamSource(new FileInputStream(source));
            StreamResult streamResult = new StreamResult(new FileOutputStream(dest));
            transformer.transform(streamSource, streamResult);
        } catch (TransformerException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
