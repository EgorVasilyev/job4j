package ru.job4j.solid.ocp.worker.converter;

import ru.job4j.solid.ocp.worker.Worker;
import ru.job4j.solid.ocp.worker.Workers;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

/**
 * Class ToXML. Генерация XML
 */
public class ToXML implements Converter {
    private Marshaller jaxbMarshaller;
    public ToXML() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Workers.class);
        this.jaxbMarshaller = jaxbContext.createMarshaller();
        this.jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    }
    /**
     * Method save. Генерация XML из значений полей объектов в файл target
     * @param list лист объектов Worker
     */
    public void save(List<Worker> list, File target) throws JAXBException {
        this.jaxbMarshaller.marshal(
                new Workers(list), target);
    }
    public void show(List<Worker> list) throws JAXBException {
        this.jaxbMarshaller.marshal(
                new Workers(list), System.out);
    }
    @Override
    public String toString() {
        return "XML";
    }
}


