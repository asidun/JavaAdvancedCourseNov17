package com.flowergarden.run.xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.flowergarden.flowers.Rose;
import com.flowergarden.properties.FreshnessInteger;

public class RunJaxbXml {

	public static void main(String[] args) throws JAXBException {
		
		saveObject(new File("rose.xml"), new Rose(true, 12, 13.3f, new FreshnessInteger(1)));

	}
	
	public static void saveObject(File file, Object o) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(o.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(o,file);
    }

}
