package com.mdrain.servlets.printers_production;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mdrain.servletPrepare.admin.IncludePublicHolidaysInDataBase;
import com.mdrain.servletPrepare.admin.SendMail;
import com.mdrain.servletPrepare.admin.SendSMS;
import com.mdrain.servletPrepare.admin.UploadFilesForProductionCapacity;
import com.mdrain.servletPrepare.printers_production.AbsenceReport;
import com.mdrain.servletPrepare.printers_production.DisplayMaterialsWithoutType;
import com.mdrain.servletPrepare.printers_production.DisplayOperatorsInfo;
import com.mdrain.servletPrepare.printers_production.DisplayPrintersProductionTestCardInformation;
import com.mdrain.servletPrepare.printers_production.DisplayReportedTimeFromProductionCards;
import com.mdrain.servletPrepare.printers_production.GenerateMonthlyPresenceBlank;
import com.mdrain.servletPrepare.printers_production.GenerateProductionPlan;
import com.mdrain.servletPrepare.printers_production.GenerateSerialNumber;
import com.mdrain.servletPrepare.printers_production.GenerateSkillsMatrix;
import com.mdrain.servletPrepare.printers_production.GetHoleOperatorsInfo;
import com.mdrain.servletPrepare.printers_production.IncludeAndManageAbsence;
import com.mdrain.servletPrepare.printers_production.IncludeOperatorsInDataBase;
import com.mdrain.servletPrepare.printers_production.IncludeProductionAndTestCardsInDataBase;
import com.mdrain.servletPrepare.printers_production.OrdersInformation;
import com.mdrain.servletPrepare.printers_production.PlanedLaborCost;
import com.mdrain.servletPrepare.printers_production.PrintersProductionCapacity;
import com.mdrain.servletPrepare.printers_production.ProductionMonitoringManager;
import com.mdrain.servletPrepare.printers_production.UpdateProductionStaffInfo;
import com.mdrain.servletPrepare.printers_production.WardrobController;


public class PrintersProductionServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = req.getRequestURI();
		String[] pathArray =  path.split("/");
		
		if (pathArray[pathArray.length - 1].equals("printers_production_servlet_skills_matrix")) 
			GenerateSkillsMatrix.getOperatorsData(req, resp);
		
		
		if (pathArray[pathArray.length - 1].equals("printers_production_servlet_absence_report")) 
			AbsenceReport.bootstrap(req, resp);
		
		if (pathArray[pathArray.length - 1].equals("printers_production_servlet_display_reported_production_time_servlet")) 
			DisplayReportedTimeFromProductionCards.bootstrap(req, resp);
		
		
		if (pathArray[pathArray.length - 1].equals("printers_production_servlet_orders_information_servlet")) 
			OrdersInformation.productionOrderInformation(req, resp);
		
		
		if (pathArray[pathArray.length - 1].equals("printers_production_servlet_operators_data_servlet")) 
			UpdateProductionStaffInfo.updateProductionStaffInformation(req, resp);	
		
		
		if (pathArray[pathArray.length - 1].equals("printers_production_servlet_get_hole_operators_info_servlet")) 
			GetHoleOperatorsInfo.bootstrap(req, resp);	
		
		
		if (pathArray[pathArray.length - 1].equals("printers_production_servlet_get_production_plan_servlet")) 
			GenerateProductionPlan.bootstrap(req, resp);
		
		
		if (pathArray[pathArray.length - 1].equals("printers_production_servlet_test_card_display_info_servlet")) 
			DisplayPrintersProductionTestCardInformation.bootstrap(req, resp);
		
		
		if (pathArray[pathArray.length - 1].equals("printers_production_servlet_production_capacity_servlet")) {
			PrintersProductionCapacity bootstrap = new PrintersProductionCapacity();
			bootstrap.boodstrap(req, resp);
		}
		
		
		if (pathArray[pathArray.length - 1].equals("printers_production_servlet_input_production_card")) 
			IncludeProductionAndTestCardsInDataBase.includeTestCardsInDataBase(req, resp);
		
		
		if (pathArray[pathArray.length - 1].equals("printers_production_servlet_presents_blank")) 
			GenerateMonthlyPresenceBlank.generateExcelPresenceBlank(req, resp);
		
		if (pathArray[pathArray.length - 1].equals("printers_production_servlet_include_absence")) 
			IncludePublicHolidaysInDataBase.includePublicHolidays(req, resp);
		
		
		if (pathArray[pathArray.length - 1].equals("printers_production_servlet_display_operators_info")) 
			DisplayOperatorsInfo.getOperatorsData(req, resp);
		
		
		if (pathArray[pathArray.length - 1].equals("printers_production_servlet_display_material_without_type")) 
			DisplayMaterialsWithoutType.getMaterialsWithoutType(req, resp);
		
		if (pathArray[pathArray.length - 1].equals("printers_production_servlet_update_material_without_type")) 
			DisplayMaterialsWithoutType.updateMaterial(req, resp);
		
		if (pathArray[pathArray.length - 1].equals("printers_production_servlet_planed_labor_cost")) {
			PlanedLaborCost boodstrap = new PlanedLaborCost();
			
			boodstrap.boodstrap(req, resp);
		}
		
		if (pathArray[pathArray.length - 1].equals("printers_production_servlet_wardrob_empty_info")) 
			WardrobController.displayEmptyWardrob(req, resp);
		
		
		if (pathArray[pathArray.length - 1].equals("printers_production_servlet_wardrob_info")) 
			WardrobController.displayWardrobInfo(req, resp);
		
		
		if (pathArray[pathArray.length - 1].equals("printers_production_servlet_generate_serial_number")) 
			GenerateSerialNumber.bootstrap(req, resp);

		if (pathArray[pathArray.length - 1].equals("printers_production_servlet_send_mail")) {
	
				SendSMS.bootstrapSendSms(req, resp);
		}
		
		if (pathArray[pathArray.length - 1].equals("printers_production_servlet_display_production_orders_dashboard")) {
			
			ProductionMonitoringManager.displayProductionDashboard(req, resp);
	}
			
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String path = req.getRequestURI();
		String[] pathArray =  path.split("/");
		
		if (pathArray[pathArray.length - 1].equals("printers_production_servlet_skills_matrix")) 
			GenerateSkillsMatrix.createExcelSkillsMatrix(req, resp);
		

		if (pathArray[pathArray.length - 1].equals("printers_production_servlet_operators_data_servlet")) 
			IncludeOperatorsInDataBase.includeOperatorsInDataBase(req, resp);
		
		if (pathArray[pathArray.length - 1].equals("printers_production_servlet_input_production_card")) 
			IncludeProductionAndTestCardsInDataBase.includeProductionCardsInDataBase(req, resp);
		
		if (pathArray[pathArray.length - 1].equals("printers_production_servlet_presents_blank")) 
			GenerateMonthlyPresenceBlank.generatePresenceBlank(req, resp);
		
		if (pathArray[pathArray.length - 1].equals("printers_production_servlet_include_absence")) 
			IncludeAndManageAbsence.includeAndManageAbsence(req, resp);
		
		
		if (pathArray[pathArray.length - 1].equals("printers_production_servlet_display_operators_info")) 
			DisplayOperatorsInfo.getOperatorsDataExcel(req, resp);
	}

}
