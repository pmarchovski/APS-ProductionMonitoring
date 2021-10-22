package com.mdrain.servletPrepare.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mdrain.database.DataBaseActivities;

public class JoinAndCreateTableResult {

	public static void createAndJoinTableResult(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		
		String table                         = "tb_result";
		String point                         = ".";
		String tbCooisProd                   = "tb_coois_prod";
		String tbCooisOperation              = "tb_coois_operation";
		String cooisProdOrder                = "tb_coois_prod_order";
		String cooisProdMaterialNumber       = "tb_coois_prod_material_number";
		String cooisProdMaterialDescription  = "tb_coois_prod_material_description";
		String cooisProdQuantity             = "tb_coois_prod_quantity";
		String cooisProdStartDate            = "tb_coois_prod_start_date";
		String cooisProdEndDate              = "tb_coois_prod_end_date";
		String cooisProdStatus               = "tb_coois_prod_status";
		String cooisProdDeliveryQuantity     = "tb_coois_prod_del_qty";
		String cooisOperationOrder           = "tb_coois_operation_order";
		String cooisOperationWorkCenter      = "tb_coois_operation_work_center";
		String cooisOperationProcessingTime  = "tb_coois_operation_processing_time";
		String cooisOperationTimePerPc       = "tb_coois_operation_time_per_pc";
		String cooisOperationNumberOperators = "tb_coois_operation_number_operators";
		String columnONOne                   = tbCooisProd + point + cooisProdOrder;
		String columnONTwo                   = tbCooisOperation + point + cooisOperationOrder;
		
		HttpSession session = req.getSession();
		ArrayList<String> column = new ArrayList<String>();

		column.add(tbCooisProd + point + cooisProdOrder);
		column.add(tbCooisProd + point + cooisProdMaterialNumber);
		column.add(tbCooisProd + point + cooisProdMaterialDescription);
		column.add(tbCooisProd + point + cooisProdQuantity);
		column.add(tbCooisProd + point + cooisProdStartDate);
		column.add(tbCooisProd + point + cooisProdEndDate);
		column.add(tbCooisProd + point + cooisProdStatus);
		column.add(tbCooisProd + point + cooisProdDeliveryQuantity);
		column.add(tbCooisOperation + point + cooisOperationWorkCenter);
		column.add(tbCooisOperation + point + cooisOperationProcessingTime);
		column.add(tbCooisOperation + point + cooisOperationTimePerPc);
		column.add(tbCooisOperation + point + cooisOperationNumberOperators);

		DataBaseActivities dbActivities = new DataBaseActivities();

		dbActivities.deleteTable(table);
		dbActivities.createTable(tbCooisProd, tbCooisOperation, column, columnONOne, columnONTwo);
		
		session.removeAttribute("admin_servlet_upload_data_into_coois");
		resp.sendRedirect("production_capacity.jsp");
		
		
	}
	
}
