package ru.job4j.sqlxmlxstl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

/**
 * Class StoreXML. Генерация XML из данных базы
 */
public class StoreXML {
    private final File target;
    /**
     * Constructor StoreXML.
     * @param target Конечный файл XML
     */
    StoreXML(File target) {
        this.target = target;
    }
    /**
     * Method save. Генерация XML из данных базы в файл target
     * @param list лист объектов Entry со значениями записей из таблицы entry в БД
     */
    public void save(List<Entry> list) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(
                new Entries(list),
                target
        );
    }
}


