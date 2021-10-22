package com.mdrain.servletPrepare.admin;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.mdrain.database.DataBaseActivities;

public class UploadDataIntoCooisTables {

	
	public static void uploadDataIntoCooisOperationTables(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		DataBaseActivities dbActivities = new DataBaseActivities();
		ArrayList<Object> values;
		ArrayList<String> fieldsInto    = new ArrayList<String>();
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

				String order            = element.getElementsByTagName("element1").item(0).getTextContent();
				String activity         = element.getElementsByTagName("element2").item(0).getTextContent();
				String workCenter       = element.getElementsByTagName("element3").item(0).getTextContent();
				String wrokCenterDescr  = element.getElementsByTagName("element4").item(0).getTextContent();
				String operation        = element.getElementsByTagName("element5").item(0).getTextContent();				
				Integer quantity        = Integer.parseInt(element.getElementsByTagName("element6").item(0).getTextContent());				
				String unit             = element.getElementsByTagName("element7").item(0).getTextContent();
				String startDate        = element.getElementsByTagName("element8").item(0).getTextContent();
				String endDate          = element.getElementsByTagName("element9").item(0).getTextContent();				
				String timeUnit         = element.getElementsByTagName("element10").item(0).getTextContent();
				Double procesingTime    = Double.parseDouble(element.getElementsByTagName("element11").item(0).getTextContent());
				Double timePerPc        = Double.parseDouble(element.getElementsByTagName("element12").item(0).getTextContent());		
				String timeUnitTwo      = element.getElementsByTagName("element13").item(0).getTextContent();
				Integer numberOperators = Integer.parseInt(element.getElementsByTagName("element14").item(0).getTextContent());	
				String unitTwo          = element.getElementsByTagName("element15").item(0).getTextContent();
				String status           = element.getElementsByTagName("element16").item(0).getTextContent();
				
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
		
		req.getRequestDispatcher("/admin_servlet_create_join_table_result").forward(req, resp);
	}
	
	public static void uploadDataIntoCooisProdTables(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		HttpSession session = req.getSession();
		session.removeAttribute("before_path");
		session.removeAttribute("path");
		
		
		DataBaseActivities dbActivities = new DataBaseActivities();
		ArrayList<String> fieldsInto    = new ArrayList<String>();
		String table                    = "tb_coois_prod";

		fieldsInto.add("tb_coois_prod_order");
		fieldsInto.add("tb_coois_prod_material_number");
		fieldsInto.add("tb_coois_prod_material_description");
		fieldsInto.add("tb_coois_prod_order_type");
		fieldsInto.add("tb_coois_prod_quantity");
		fieldsInto.add("tb_coois_prod_unit");
		fieldsInto.add("tb_coois_prod_start_date");
		fieldsInto.add("tb_coois_prod_end_date");
		fieldsInto.add("tb_coois_prod_status");
		fieldsInto.add("tb_coois_prod_sequence");
		fieldsInto.add("tb_coois_prod_del_qty");
		fieldsInto.add("tb_coois_prod_so");
		fieldsInto.add("tb_coois_prod_so_item");
		fieldsInto.add("tb_coois_prod_actual_finish_date");
		fieldsInto.add("tb_coois_prod_confirm_finish_date");
		fieldsInto.add("tb_coois_prod_confirm_qty");
		fieldsInto.add("tb_coois_prod_export_date");

		final String FILENAME = req.getServletContext().getRealPath("") + "myXMLfile.xml";

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

		ArrayList<Object> orderCollection = new ArrayList<Object>();
		ResultSet result = dbActivities.select(table);

		dbActivities.truncateTable(table);

		try {
			while (result.next()) {
				orderCollection.add(result.getString("tb_coois_prod_order"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int temp = 0; temp < list.getLength(); temp++) {

			Node node = list.item(temp);

			if (node.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) node;

				String order               = element.getElementsByTagName("element1").item(0).getTextContent();
				String materialNumber      = element.getElementsByTagName("element2").item(0).getTextContent();
				String materialDescription = element.getElementsByTagName("element3").item(0).getTextContent();
				String orderType           = element.getElementsByTagName("element4").item(0).getTextContent();
				Integer orderQuantity      = Integer.parseInt(element.getElementsByTagName("element5").item(0).getTextContent());
				String unit                = element.getElementsByTagName("element6").item(0).getTextContent();
				String startDate           = element.getElementsByTagName("element7").item(0).getTextContent();
				String endDate             = element.getElementsByTagName("element8").item(0).getTextContent();
				String status              = element.getElementsByTagName("element9").item(0).getTextContent();
				String seqNumber           = element.getElementsByTagName("element10").item(0).getTextContent();
				Integer qtyDelivery        = Integer.parseInt(element.getElementsByTagName("element11").item(0).getTextContent());
				String salesOrder          = element.getElementsByTagName("element12").item(0).getTextContent();
				String salesOrderItem      = element.getElementsByTagName("element13").item(0).getTextContent();
				String actualFinDate       = element.getElementsByTagName("element14").item(0).getTextContent();
				String actualConfFinDate   = element.getElementsByTagName("element15").item(0).getTextContent();
				Integer confirmQty         = Integer.parseInt(element.getElementsByTagName("element16").item(0).getTextContent());
                String exportDate          = element.getElementsByTagName("element17").item(0).getTextContent();
				
				ArrayList<Object> values = new ArrayList<Object>();

				values.add(order);
				values.add(materialNumber);
				values.add(materialDescription);
				values.add(orderType);
				values.add(orderQuantity);
				values.add(unit);
				values.add(startDate);
				values.add(endDate);
				values.add(status);
				values.add(seqNumber);
				values.add(qtyDelivery);
				values.add(salesOrder);
				values.add(salesOrderItem);
				values.add(actualFinDate);
				values.add(actualConfFinDate);
				values.add(confirmQty);
				values.add(exportDate);

				dbActivities.insertObject(table, fieldsInto, values);
				
				ArrayList<String> column = new ArrayList<String>();
				ArrayList<Object> value  = new ArrayList<Object>();
				column.add("tb_coois_prod_status");
				column.add("tb_coois_prod_del_qty");
				value.add("TECO");
				value.add(0);
				
				dbActivities.deleteWhereLikeAnd(table, column, value);
			}
		}
		
		String buttonPath = "<form method=\"get\" action=\"admin_servlet_upload_data_into_coois_operation\">\r\n"
			+ "\r\n"
			+ "	<input type=\"submit\" value=\"REFRESH COOIS_OPERATIONS\" Class=\"butt\" id=\"click_coois_operation\">\r\n"
			+ "\r\n"
			+ "	</form>";
	
		
	session.removeAttribute("admin_servlet_upload_data_into_coois");
	session.setAttribute("admin_servlet_upload_data_into_coois", buttonPath);
	resp.sendRedirect("production_capacity.jsp");
//	req.getRequestDispatcher("production_capacity.jsp").forward(req, resp);
		
	}
	
	
}
