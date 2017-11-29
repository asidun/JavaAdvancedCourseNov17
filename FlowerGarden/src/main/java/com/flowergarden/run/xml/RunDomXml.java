package com.flowergarden.run.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class RunDomXml {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// создали фабрику строителей, сложный и грамосткий процесс (по реже
		// выполняйте это действие)
		// dbf.setValidating(false); // не делать проверку валидации
		DocumentBuilder db = dbf.newDocumentBuilder(); // создали конкретного
														// строителя документа
		Document doc = db.parse(new File("sample.xml")); // стооитель построил
															// документ
		// Document - тоже является нодом, и импленментирует методы
		visit(doc, 0);
	}

	public static void visit(Node node, int level) {
		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node childNode = list.item(i); // текущий нод
			process(childNode, level + 1); // обработка
			visit(childNode, level + 1); // рекурсия
		}
	}

	public static void process(Node node, int level) {
		for (int i = 0; i < level; i++) {
			System.out.print('\t');
		}
		System.out.print(node.getNodeName());
		if (node instanceof Element) {
			Element e = (Element) node;
			// работаем как с элементом (у него есть атрибуты и схема)
		}
		System.out.println();
	}
}
