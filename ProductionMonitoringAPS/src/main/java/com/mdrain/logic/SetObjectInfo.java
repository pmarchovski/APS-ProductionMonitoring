package com.mdrain.logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.mdrain.database.DataBaseActivities;
import com.mdrain.objects.Absence;
import com.mdrain.objects.Materials;
import com.mdrain.objects.Operators;
import com.mdrain.objects.Orders;
import com.mdrain.objects.ProductionCards;
import com.mdrain.objects.Wardrobe;

public class SetObjectInfo {

	static SetObjectInfo instanceProdCard = null;

	public static ArrayList<Materials> getMaterialGroupFromDataBase() {

		String table = "tb_product_type";
		ResultSet result = null;
		DataBaseActivities dbActivities = new DataBaseActivities();
		ArrayList<Materials> materialsCollection = new ArrayList<Materials>();

		result = dbActivities.select(table);

		try {
			while (result.next()) {

				Materials material = new Materials();

				material.setMaterialNumber(result.getString("tb_product_type_number"));
				material.setMaterialDescription(result.getString("tb_product_type_description"));
				material.setMaterialGroup(result.getString("tb_product_type_type"));
				materialsCollection.add(material);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return materialsCollection;

	}

	public static ArrayList<Operators> getOperatorsDataFromDataBase() {

		String table = "tb_operators";
		ResultSet result = null;
		DataBaseActivities dbActivities = new DataBaseActivities();
		ArrayList<Operators> operatorsDataCollection = new ArrayList<Operators>();
		ArrayList<Object> productionCardsInfoCollection = getDataProductionCardFromDataBase();
		result = dbActivities.select(table);
		result = dbActivities.sort(table, "tb_operators_operator_name");

		try {
			while (result.next()) {

				Operators operator = new Operators();

				operator.setFullName(result.getString("tb_operators_operator_name"));
				operator.setTeamLeader(result.getString("tb_operators_team_leader"));
				operator.setGender(result.getString("tb_operators_gender"));
				operator.setIsActive(result.getString("tb_operators_isActive"));
				operator.setIsMotherhood(result.getString("tb_operators_isMotherhood"));
				operator.setSkillsCollectionFromProductionCards(productionCardsInfoCollection);
				operator.setNumberWardrobe(result.getString("tb_operators_wardrobe"));

				operatorsDataCollection.add(operator);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return operatorsDataCollection;
	}

	
	public static ArrayList<Orders> getOrdersInfoFromDataBaseOne() {

		String table = "tb_coois_prod";
		ResultSet result = null;
		DataBaseActivities dbActivities = new DataBaseActivities();
		ArrayList<Orders> ordersCollection = new ArrayList<Orders>();

		result = dbActivities.select(table);

		try {
			while (result.next()) {

				Orders order = new Orders();

				order.setNumber(result.getInt("tb_coois_prod_order"));
				order.setMaterialNumber(result.getString("tb_coois_prod_material_number"));
				order.setMaterialDescription(result.getString("tb_coois_prod_material_description"));
				order.setQuantity(result.getInt("tb_coois_prod_quantity"));
				LocalDate dateString = Date.date(result.getString("tb_coois_prod_start_date"));
				order.setStartDate(dateString);

				ordersCollection.add(order);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ordersCollection;

	}
	
	
	
	public static ArrayList<Orders> getOrdersInfoFromDataBase() {

		String table = "tb_coois_prod";
		ResultSet result = null;
		DataBaseActivities dbActivities = new DataBaseActivities();
		ArrayList<Orders> ordersCollection = new ArrayList<Orders>();
		ArrayList<Materials> materialTypeCollection = new ArrayList<Materials>();
		ArrayList<Orders> cooisOperationFromDataBase = new ArrayList<Orders>();

		materialTypeCollection = getMaterialGroupFromDataBase();
		cooisOperationFromDataBase = getDataCooisOperationFromDataBase();

		result = dbActivities.select(table);

		try {
			while (result.next()) {

				Orders order = new Orders();

				order.setNumber(result.getInt("tb_coois_prod_order"));
				order.setMaterialNumber(result.getString("tb_coois_prod_material_number"));
				order.setMaterialDescription(result.getString("tb_coois_prod_material_description"));
				order.setQuantity(result.getInt("tb_coois_prod_quantity"));
				LocalDate dateString = Date.date(result.getString("tb_coois_prod_start_date"));
				order.setStartDate(dateString);
				LocalDate endDataString = Date.date(result.getString("tb_coois_prod_end_date"));
				order.setEndDate(endDataString);
				order.setStatus(result.getString("tb_coois_prod_status"));
				LocalDate exportDateString = Date.date(result.getString("tb_coois_prod_export_date"));
				order.setExportDate(exportDateString);
				order.setProductType(materialTypeCollection, order);
				order.setProductionLine(cooisOperationFromDataBase, order);
				order.setTeamLeader(order.getProductionLine());

				ordersCollection.add(order);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ordersCollection;

	}

	public static ArrayList<Orders> getDataCooisOperationFromDataBase() {

		String table = "tb_coois_operation";
		ResultSet result = null;
		DataBaseActivities dbActivities = new DataBaseActivities();
		ArrayList<Orders> cooisOperationDataCollection = new ArrayList<Orders>();

		result = dbActivities.select(table);

		try {
			while (result.next()) {

				Orders orderCooisOperation = new Orders();

				orderCooisOperation.setNumber(Integer.parseInt(result.getString("tb_coois_operation_order")));
				orderCooisOperation.setTimeForOnePc(result.getDouble("tb_coois_operation_time_per_pc"));
				orderCooisOperation.setNumberOfOperatorsForOrder(result.getInt("tb_coois_operation_number_operators"));
				orderCooisOperation.setWorkCenter(result.getString("tb_coois_operation_work_center"));

				cooisOperationDataCollection.add(orderCooisOperation);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cooisOperationDataCollection;
	}

	public static ArrayList<Object> getDataProductionCardFromDataBase() {

		String table = "tb_production_card_information";
		ResultSet result = null;
		DataBaseActivities dbActivities = new DataBaseActivities();
		ArrayList<Object> productionCardInfoCollection = new ArrayList<Object>();
		ArrayList<Orders> ordersInfoCollection = getOrdersInfoFromDataBase();

		result = dbActivities.select(table);
		result = dbActivities.sort(table, "production_card_information_operator");

		try {
			while (result.next()) {

				ProductionCards productionCard = new ProductionCards();

				LocalDate prodCardDateString = Date.date(result.getString("production_card_information_date"));
				productionCard.setProductionCardDate(prodCardDateString);
				productionCard.setProductionCardOrder(result.getInt("production_card_information_order"));
				productionCard.setProductionCardOperator(result.getString("production_card_information_operator"));
				productionCard.setProductionCardOperation(result.getString("production_card_information_operation"));
				productionCard.setProductionCardQty(result.getInt("production_card_information_quantity"));
				productionCard.setProductionCardTime(result.getInt("production_card_information_time"));
				productionCard
						.setProductionCardIsNewOperator(result.getString("production_card_information_isNewOperator"));
				productionCard.setProductionCardUser(result.getString("production_card_information_user"));
				LocalDate inputDate = Date.date(result.getString("production_card_information_input_date"));
				productionCard.setProductionCardInputDate(inputDate);
				productionCard.setOperationForMatrix();
				productionCard.setProductionCardProductType(ordersInfoCollection);
				productionCard.setUnionProdTypeAndOperationForMatrix();

				productionCardInfoCollection.add(productionCard);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return productionCardInfoCollection;
	}

	public static ArrayList<Absence> getAbsenceInfoCollection() {

		String table = "tb_operators_absence";
		ResultSet result = null;
		DataBaseActivities dbActivities = new DataBaseActivities();
		ArrayList<Absence> absenceInfoCollection = new ArrayList<Absence>();

		result = dbActivities.select(table);
		result = dbActivities.sort(table, "tb_operators_absence_operator_name");

		try {
			while (result.next()) {

				Absence absence = new Absence();

				absence.setOperatorName(result.getString("tb_operators_absence_operator_name"));
				LocalDate absenceDate = Date.date(result.getString("tb_operators_absence_add_date"));
				absence.setAbsenceDate(absenceDate);
				absence.setAbsenceHours(result.getDouble("tb_operators_absence_hour"));
				absence.setHolidayFromHours(result.getInt("tb_operators_absence_holiday_from_hours"));
				absence.setUser(result.getString("tb_operators_absence_user"));
				LocalDate inputDate = Date.date(result.getString("tb_operators_absence_date_input"));
				absence.setInputDate(inputDate);

				absenceInfoCollection.add(absence);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return absenceInfoCollection;
	}

	public static ArrayList<Wardrobe> getWardrobeInfoCollectionFromDatabase() {

		String table = "tb_wardrobe";
		ResultSet result = null;
		DataBaseActivities dbActivities = new DataBaseActivities();
		ArrayList<Wardrobe> wardrobeInfoCollection = new ArrayList<Wardrobe>();

		result = dbActivities.select(table);

		try {
			while (result.next()) {

				Wardrobe wardrobe = new Wardrobe();

				wardrobe.setWardrobeName(result.getString("tb_wardrobe_name"));
				wardrobe.setWardrobeNumber(result.getString("tb_wardrobe_number"));

				wardrobeInfoCollection.add(wardrobe);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return wardrobeInfoCollection;
	}

	public static String getOperatorInfoWhere(String table, String name) {

		String myResult = "";
		ResultSet result = null;
		DataBaseActivities dbActivities = new DataBaseActivities();
		result = dbActivities.select(table);

		try {
			while (result.next()) {

				if (result.getString("tb_wardrobe_name").equals(name)) {
					myResult = result.getString("tb_wardrobe_number");

				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return myResult;
	}
	
	
	public static ArrayList<String> getFreeWardrobeInfoFromDatabase(){
		
		ArrayList<String> freeWardrobeCollection = new ArrayList<String>();
		ArrayList<Wardrobe> wardrobeInfoCollection = getWardrobeInfoCollectionFromDatabase();
		
		for (int i = 0; i < wardrobeInfoCollection.size(); i++) {
			
			if (wardrobeInfoCollection.get(i).getWardrobeName().equals("")) {
				freeWardrobeCollection.add(wardrobeInfoCollection.get(i).getWardrobeNumber());
			}
		}
		
		return freeWardrobeCollection;
	}
	
	

	public static SetObjectInfo getInstanceProdCard() {

		if (instanceProdCard == null) {
			instanceProdCard = new SetObjectInfo();
			return instanceProdCard;
		}
		return null;
	}
}
