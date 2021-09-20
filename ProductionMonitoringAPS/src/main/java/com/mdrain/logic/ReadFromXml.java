package com.mdrain.logic;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.text.Document;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadFromXml {
	
	public void xml() throws ParserConfigurationException, SAXException, IOException, ParseException {
		
	
	 final String FILENAME = "E:\\New folder\\newXml.xml";
	
	// Instantiate the Factory
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

  

        // optional, but recommended
        // process XML securely, avoid attacks like XML External Entities (XXE)
        dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

        // parse XML file
        DocumentBuilder db = dbf.newDocumentBuilder();

        org.w3c.dom.Document doc = db.parse(new File(FILENAME));

        // optional, but recommended
        // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
        doc.getDocumentElement().normalize();

        System.out.println("Root Element :" + ((org.w3c.dom.Document) doc).getDocumentElement().getNodeName());
        System.out.println("------");

        // get <staff>
        NodeList list = doc.getElementsByTagName("record");

        for (int temp = 0; temp < list.getLength(); temp++) {

            Node node = list.item(temp);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;

                // get staff's attribute
           //     String id = element.getAttribute("id");

                // get text
                
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                String order               = element.getElementsByTagName("order").item(0).getTextContent();
                String materialNumber      = element.getElementsByTagName("materialNumber").item(0).getTextContent();
                String materialDescription = element.getElementsByTagName("materialDescription").item(0).getTextContent();
                String orderType           = element.getElementsByTagName("orderType").item(0).getTextContent();           
                Integer orderQuantity      = Integer.parseInt(element.getElementsByTagName("orderQuantity").item(0).getTextContent());
                String unit                = element.getElementsByTagName("unit").item(0).getTextContent();
                String startDate           = element.getElementsByTagName("startDate").item(0).getTextContent();
                Date startDateNew = formatter.parse(startDate);
                String endDate             = element.getElementsByTagName("endDate").item(0).getTextContent();
                Date endDateNew = formatter.parse(endDate);
                String status              = element.getElementsByTagName("status").item(0).getTextContent();
                String seqNumber           = element.getElementsByTagName("seqNumber").item(0).getTextContent();
                Integer qtyDelivery        = Integer.parseInt(element.getElementsByTagName("qtyDelivery").item(0).getTextContent());
                String salesOrder          = element.getElementsByTagName("salesOrder").item(0).getTextContent();
                String salesOrderItem      = element.getElementsByTagName("salesOrderItem").item(0).getTextContent();
                String actualFinDate       = element.getElementsByTagName("actualFinDate").item(0).getTextContent();
                Integer confirmQty         = Integer.parseInt(element.getElementsByTagName("confirmQty").item(0).getTextContent());

                ArrayList<Object> values = new ArrayList<Object>();
                
                values.add(order);
                values.add(materialNumber);
                values.add(materialDescription);
                values.add(orderType);
                values.add(orderQuantity);
                values.add(unit);
                values.add(startDateNew);
                values.add(endDateNew);
                values.add(status);
                values.add(seqNumber);
                values.add(qtyDelivery);
                values.add(salesOrder);
                values.add(salesOrderItem);
                values.add(actualFinDate);
                values.add(confirmQty);
 
            }
        }
	}
}

	

