package com.chernenko.carparser.dao;

import com.chernenko.carparser.entity.Car;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by Dmytro on 12.03.16.
 */
public class CarDao {

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

    public Car readOneCarFromXml(String filename) {
        Car result = null;

        try {

            File file = new File(filename);
            JAXBContext jaxbContext = JAXBContext.newInstance(CarXMLAdapter.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            CarXMLAdapter adapter = (CarXMLAdapter) jaxbUnmarshaller.unmarshal(file);
            result = adapter.getCar();

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return result;
    }

}
