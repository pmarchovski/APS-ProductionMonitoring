package com.mdrain.servlets;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.mdrain.database.DataBaseActivities;

public class Upload_dataIntoCooisOperationServlet extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		DataBaseActivities dbActivities = new DataBaseActivities();
		ArrayList<Object> values;
		ArrayList<String> fieldsInto = new ArrayList<String>();
		String table = "tb_coois_operation";

		fieldsInto.add("tb_coois_operation_order");
		fieldsInto.add("tb_coois_operation_activity");
		fieldsInto.add("tb_coois_operation_work_center");
		fieldsInto.add("tb_coois_operation_wc_decription");
		fieldsInto.add("tb_coois_operation_operation");
		fieldsInto.add("tb_coois_operation_quantity");
		fieldsInto.add("tb_coois_operation_unit");
		fieldsInto.add("tb_coois_operation_actual_start_date");
		fieldsInto.add("tb_coois_operation_actual_finish_date");
		fieldsInto.add("tb_coois_operation_time_unit");
		fieldsInto.add("tb_coois_operation_processing_time");
		fieldsInto.add("tb_coois_operation_time_per_pc");
		fieldsInto.add("tb_coois_operation_unit_time");
		fieldsInto.add("tb_coois_operation_number_operators");
		fieldsInto.add("tb_coois_operation_unit_operators");
		fieldsInto.add("tb_coois_operation_status");

		final String FILENAME = req.getServletContext().getRealPath("") + "xmlCooisOperations.xml";

		// Instantiate the Factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		// optional, but recommended
		// process XML securely, avoid attacks like XML External Entities (XXE)
		try {
			dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// parse XML file
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		org.w3c.dom.Document doc = null;
		try {
			doc = db.parse(new File(FILENAME));
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// optional, but recommended
		// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();

		System.out.println("Root Element :" + ((org.w3c.dom.Document) doc).getDocumentElement().getNodeName());
		System.out.println("------");

		// get <staff>
		NodeList list = doc.getElementsByTagName("record");

		dbActivities.truncateTable(table);

	
		for (int temp = 0; temp < list.getLength(); temp++) {

			Node node = list.item(temp);

			if (node.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) node;

				String order = element.getElementsByTagName("element1").item(0).getTextContent();
				String activity = element.getElementsByTagName("element2").item(0).getTextContent();
				String workCenter = element.getElementsByTagName("element3").item(0).getTextContent();
				String wrokCenterDescr = element.getElementsByTagName("element4").item(0).getTextContent();
				String operation = element.getElementsByTagName("element5").item(0).getTextContent();				
				Integer quantity = Integer
						.parseInt(element.getElementsByTagName("element6").item(0).getTextContent());				
				String unit = element.getElementsByTagName("element7").item(0).getTextContent();
				String startDate = element.getElementsByTagName("element8").item(0).getTextContent();
				String endDate = element.getElementsByTagName("element9").item(0).getTextContent();				
				String timeUnit = element.getElementsByTagName("element10").item(0).getTextContent();
				Double procesingTime = Double
						.parseDouble(element.getElementsByTagName("element11").item(0).getTextContent());
				Double timePerPc = Double
						.parseDouble(element.getElementsByTagName("element12").item(0).getTextContent());		
				String timeUnitTwo = element.getElementsByTagName("element13").item(0).getTextContent();
				Integer numberOperators = Integer
						.parseInt(element.getElementsByTagName("element14").item(0).getTextContent());	
				String unitTwo = element.getElementsByTagName("element15").item(0).getTextContent();
				String status = element.getElementsByTagName("element16").item(0).getTextContent();
				
				values = new ArrayList<Object>();

				values.add(order);
				values.add(activity);
				values.add(workCenter);
				values.add(wrokCenterDescr);
				values.add(operation);
				values.add(quantity);
				values.add(unit);
				values.add(startDate);
				values.add(endDate);
				values.add(timeUnit);
				values.add(procesingTime);
				values.add(timePerPc);
				values.add(timeUnitTwo);
				values.add(numberOperators);
				values.add(unitTwo);
				values.add(status);

				dbActivities.insertObject(table, fieldsInto, values);
			}
		}
		req.getRequestDispatcher("/create_join_table_result_servlet").forward(req, resp);
	}
}
