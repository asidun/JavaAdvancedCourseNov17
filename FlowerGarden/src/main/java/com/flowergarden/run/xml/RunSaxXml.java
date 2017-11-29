package com.flowergarden.run.xml;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RunSaxXml {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();

		DefaultHandler handler = new DefaultHandler() {

			public void startElement(String uri, String localName, String qName, Attributes attributes)
					throws SAXException {
				System.out.println("Start Element :" + qName);
			}

			public void endElement(String uri, String localName, String qName) throws SAXException {
				System.out.println("End Element :" + qName);
			}

			public void characters(char ch[], int start, int length) throws SAXException {
				System.out.println("Characters : " + new String(ch, start, length));
			}

		};
		
		saxParser.parse("sample.xml", handler); 


	}

}
