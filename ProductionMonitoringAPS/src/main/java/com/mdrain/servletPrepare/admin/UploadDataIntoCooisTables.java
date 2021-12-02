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
import com.mdrain.singletons.Singleton;

public class UploadDataIntoCooisTables {

	
	public static void uploadDataIntoCooisOperationTables(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		DataBaseActivities dbActivities = Singleton.getInstance();
		String table                    = "tb_coois_operation";
		
		String[] fieldsInto = {
				"tb_coois_operation_order",
				"tb_coois_operation_activity",
				"tb_coois_operation_work_center",
				"tb_coois_operation_wc_decription",
				"tb_coois_operation_operation",
				"tb_coois_operation_quantity",
				"tb_coois_operation_unit",
				"tb_coois_operation_actual_start_date",
				"tb_coois_operation_actual_finish_date",
				"tb_coois_operation_time_unit",
				"tb_coois_operation_processing_time",
				"tb_coois_operation_time_per_pc",
				"tb_coois_operation_unit_time",
				"tb_coois_operation_number_operators",
				"tb_coois_operation_unit_operators",
				"tb_coois_operation_status"
		};

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
				

				Object[] values = {
						order,
						activity,
						workCenter,
						wrokCenterDescr,
						operation,
						quantity,
						unit,
						startDate,
						endDate,
						timeUnit,
						procesingTime,
						timePerPc,
						timeUnitTwo,
						numberOperators,
						unitTwo,
						status
				};
				
				dbActivities.insertObject(table, fieldsInto, values);
			}
		}
		
		req.getRequestDispatcher("/admin_servlet_create_join_table_result").forward(req, resp);
	}
	
	public static void uploadDataIntoCooisProdTables(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		HttpSession session = req.getSession();
		session.removeAttribute("before_path");
		session.removeAttribute("path");	
		DataBaseActivities dbActivities = Singleton.getInstance();
		String table                    = "tb_coois_prod";

		
		String[] fieldsInto = {
				"tb_coois_prod_order",
				"tb_coois_prod_material_number",
				"tb_coois_prod_material_description",
				"tb_coois_prod_order_type",
				"tb_coois_prod_quantity",
				"tb_coois_prod_unit",
				"tb_coois_prod_start_date",
				"tb_coois_prod_end_date",
				"tb_coois_prod_status",
				"tb_coois_prod_sequence",
				"tb_coois_prod_del_qty",
				"tb_coois_prod_so",
				"tb_coois_prod_so_item",
				"tb_coois_prod_actual_finish_date",
				"tb_coois_prod_confirm_finish_date",
				"tb_coois_prod_confirm_qty",
				"tb_coois_prod_export_date",
				"tb_coois_prod_customer"
				
		};
		

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


		// get <staff>
		NodeList list = doc.getElementsByTagName("record");

		dbActivities.truncateTable(table);

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
                String customer            = element.getElementsByTagName("element18").item(0).getTextContent();
				
				Object[] values = {
						order,
						materialNumber,
						materialDescription,
						orderType,
						orderQuantity,
						unit,
						startDate,
						endDate,
						status,
						seqNumber,
						qtyDelivery,
						salesOrder,
						salesOrderItem,
						actualFinDate,
						actualConfFinDate,
						confirmQty,
						exportDate,
						customer
				};
				
				
				dbActivities.insertObject(table, fieldsInto, values);
				
				String[] column = {"tb_coois_prod_status", "tb_coois_prod_del_qty"};
				Object[] value  = {"TECO", 0};
					
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
		
	}
}
