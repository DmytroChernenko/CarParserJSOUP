package com.chernenko.carparser.dao;

import com.chernenko.carparser.entity.Car;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * Created by Dmytro on 12.03.16.
 */
public class HtmlCarDao {

    public void saveCarToXml(Car car) {
        JAXBContext context = null;
        String pathToXmlDir = "src/main/resources/";
        CarXMLAdapter carAdapter = new CarXMLAdapter(car);

        try {
            context = JAXBContext.newInstance(CarXMLAdapter.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(carAdapter, new File(pathToXmlDir + car.getTitle() + ".xml"));


        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public Car getCar(String fileName) {
        return null;
    }

}
