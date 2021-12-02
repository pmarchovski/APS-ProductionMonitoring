package com.mdrain.logic;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import com.mdrain.database.DataBaseActivities;
import com.mdrain.objects.Absence;
import com.mdrain.objects.Comment;
import com.mdrain.objects.Materials;
import com.mdrain.objects.Operators;
import com.mdrain.objects.Orders;
import com.mdrain.objects.ProductionCards;
import com.mdrain.objects.Task;
import com.mdrain.objects.Users;
import com.mdrain.objects.Wardrobe;
import com.mdrain.objects.work_clothes.Appron;
import com.mdrain.objects.work_clothes.Heater;
import com.mdrain.objects.work_clothes.Slipper;
import com.mdrain.singletons.Singleton;


public class SetObjectInfo {

	public static DataBaseActivities dbActivities = Singleton.getInstance();

	public static ArrayList<Materials> getMaterialGroupFromDataBase() {

		String table                             = "tb_product_type";
		ArrayList<Materials> materialsCollection = new ArrayList<Materials>();
		ResultSet result                         = dbActivities.select(table);

		if (result != null) {		
			try {
				while (result.next()) {

					String materialNumber      = result.getString("tb_product_type_number");
					String materialDescription = result.getString("tb_product_type_description");
					String materialType        = result.getString("tb_product_type_type");
					
					Materials material = new Materials(materialNumber, 
							                           materialDescription,
							                           materialType);
					
					materialsCollection.add(material);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			getMaterialGroupFromDataBase();
		}
		return materialsCollection;
	}

	public static ArrayList<Operators> getOperatorsDataFromDataBase() {

		String table                                    = "tb_operators";
		ArrayList<Operators> operatorsDataCollection    = new ArrayList<Operators>();
		ArrayList<Object> productionCardsInfoCollection = getDataProductionCardFromDataBase();
		ResultSet result                                = dbActivities.select(table);
		result                                          = dbActivities.sort(table, "tb_operators_operator_name");

		if (result != null) {
		
		try {
			while (result.next()) {
				
				String operatorName                        = result.getString("tb_operators_operator_name");
				String operatorTeamLeader                  = result.getString("tb_operators_team_leader");
				String operatorGender                      = result.getString("tb_operators_gender");
				String operatorIsActive                    = result.getString("tb_operators_isActive");
				String operatorIsMotherhood                = result.getString("tb_operators_isMotherhood");
				ArrayList<Object> operatorSkillsCollection = productionCardsInfoCollection;
				String numberWardRobe                      = result.getString("tb_operators_wardrobe");				
				
				Operators operator = new Operators();

				operator.setFullName(operatorName);
				operator.setTeamLeader(operatorTeamLeader);
				operator.setGender(operatorGender);
				operator.setIsActive(operatorIsActive);
				operator.setIsMotherhood(operatorIsMotherhood);
				operator.setSkillsCollectionFromProductionCards(operatorSkillsCollection);
				operator.setNumberWardrobe(numberWardRobe);

				operatorsDataCollection.add(operator);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else {
			getOperatorsDataFromDataBase();
		}
		return operatorsDataCollection;
	}

	
	public static ArrayList<Orders> getOrdersInfoFromDataBaseOne() {

		String table                       = "tb_coois_prod";
		ArrayList<Orders> ordersCollection = new ArrayList<Orders>();
		ResultSet result                   = dbActivities.select(table);

		if (result != null) {
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
		} else {
			getOrdersInfoFromDataBaseOne();
		}
		return ordersCollection;

	}
	
	public static ArrayList<Orders> getOrdersInfoFromDataBase() {

		String table                                 = "tb_coois_prod";
		ArrayList<Orders> ordersCollection           = new ArrayList<Orders>();
		ArrayList<Materials> materialTypeCollection  = new ArrayList<Materials>();
		ArrayList<Orders> cooisOperationFromDataBase = new ArrayList<Orders>();

		materialTypeCollection = getMaterialGroupFromDataBase();
		cooisOperationFromDataBase = getDataCooisOperationFromDataBase();

		ResultSet result = dbActivities.select(table);

		if (result != null) {
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
				order.setCustomer(result.getString("tb_coois_prod_customer"), order.getMaterialNumber());

				ordersCollection.add(order);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} else {
			getOrdersInfoFromDataBase();
		}
		return ordersCollection;

	}
	
	
	
	public static ArrayList<Orders> getFullProductionOrdersInfo(){
		
		
		String table                                 = "tb_coois_prod";
		ArrayList<Orders> ordersCollection           = new ArrayList<Orders>();
		ArrayList<Materials> materialTypeCollection  = new ArrayList<Materials>();
		ArrayList<Orders> cooisOperationFromDataBase = new ArrayList<Orders>();

		materialTypeCollection = getMaterialGroupFromDataBase();
		cooisOperationFromDataBase = getDataCooisOperationFromDataBase();

		ResultSet result = dbActivities.select(table);
		result = dbActivities.sort(table, "tb_coois_prod_start_date");

		if (result != null) {
		try {
			while (result.next()) {

				Orders order = new Orders();

				order.setNumber(result.getInt("tb_coois_prod_order"));
				String materialNumber = result.getString("tb_coois_prod_material_number");
				order.setMaterialNumber(materialNumber);
                
				order.setMaterialDescription(result.getString("tb_coois_prod_material_description"));
				order.setQuantity(result.getInt("tb_coois_prod_quantity"));
				LocalDate dateString = Date.date(result.getString("tb_coois_prod_start_date"));
				order.setStartDate(dateString);
				LocalDate endDataString = Date.date(result.getString("tb_coois_prod_end_date"));
				order.setEndDate(endDataString);
				order.setDelQuantity(result.getInt("tb_coois_prod_del_qty"));
				order.setStatus(result.getString("tb_coois_prod_status"));
				LocalDate exportDateString = Date.date(result.getString("tb_coois_prod_export_date"));
				order.setExportDate(exportDateString);
				order.setProductType(materialTypeCollection, order);
				order.setProductionLine(cooisOperationFromDataBase, order);
				order.setTeamLeader(order.getProductionLine());
				order.setNumberOfDaysForProduction();
				order.setWeek(dateString);	
				order.setMaterialProducedLong(order.getMaterialNumber(), order.getStartDate(), ordersCollection);
				order.setCustomer(result.getString("tb_coois_prod_customer"), order.getMaterialNumber());
				
				ordersCollection.add(order);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} else {
			getFullProductionOrdersInfo();
		}
		return ordersCollection;

	}
	

	public static ArrayList<Orders> getDataCooisOperationFromDataBase() {

		String table                                   = "tb_coois_operation";
		ArrayList<Orders> cooisOperationDataCollection = new ArrayList<Orders>();
		ResultSet result                               = dbActivities.select(table);

		if (result != null) {
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
		} else {
			getDataCooisOperationFromDataBase();
		}
		return cooisOperationDataCollection;
	}

	public static ArrayList<Object> getDataProductionCardFromDataBase() {

		String table                                   = "tb_production_card_information";
		ArrayList<Object> productionCardInfoCollection = new ArrayList<Object>();
		ArrayList<Orders> ordersInfoCollection         = getOrdersInfoFromDataBase();
		ResultSet result                               = dbActivities.select(table);
		result                                         = dbActivities.sort(table, "production_card_information_operator");

		if (result != null) {
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
		} else {
			getDataProductionCardFromDataBase();
		}
		return productionCardInfoCollection;
	}
	
	public static ArrayList<Absence> getAbsenceInfoCollection() {

		String table                             = "tb_operators_absence";
		ArrayList<Absence> absenceInfoCollection = new ArrayList<Absence>();
		ResultSet result                         = dbActivities.select(table);
		result                                   = dbActivities.sort(table, "tb_operators_absence_operator_name");
		if (result != null) {
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
		} else {
			getAbsenceInfoCollection();
		}
		return absenceInfoCollection;
	}

	public static ArrayList<Wardrobe> getWardrobeInfoCollectionFromDatabase() {

		String table                               = "tb_wardrobe";
		ArrayList<Wardrobe> wardrobeInfoCollection = new ArrayList<Wardrobe>();
		ResultSet result                           = dbActivities.select(table);
		if (result != null) {
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
		} else {
			getWardrobeInfoCollectionFromDatabase();
		}
		return wardrobeInfoCollection;
	}

	public static String getOperatorInfoWhere(String table, String name) {

		String myResult  = "";
		ResultSet result = dbActivities.select(table);
		if (result != null) {
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
		}
		return myResult;
	}
	
	
	public static ArrayList<String> getFreeWardrobeInfoFromDatabase(){
		
		ArrayList<String> freeWardrobeCollection   = new ArrayList<String>();
		ArrayList<Wardrobe> wardrobeInfoCollection = getWardrobeInfoCollectionFromDatabase();
		
		for (int i = 0; i < wardrobeInfoCollection.size(); i++) {
			
			if (wardrobeInfoCollection.get(i).getWardrobeName().equals("")) {
				freeWardrobeCollection.add(wardrobeInfoCollection.get(i).getWardrobeNumber());
			}
		}
		
		return freeWardrobeCollection;
	}
	
	
	public static ArrayList<Task> getTaskFromDataBase(){
		
		ArrayList<Task> taskCollection = new ArrayList<Task>();
		String table                   = "tb_tsks";
		ResultSet result               = dbActivities.select(table);
		
		if (result != null) {
		try {
			while (result.next()) {
				
				Task task = new Task();
				
				task.setTitle(result.getString("tb_tsks_title"));
				task.setOwner(result.getString("tb_tsks_owner"));
				task.setCreator(result.getString("tb_tsks_creator"));
				task.setStatus(result.getString("tb_tsks_status"));
				task.setContent(result.getString("tb_tsks_content"));	
				task.setTargetDate(result.getString("tb_tsks_targetDate"));
				task.setCreateDate(result.getString("tb_tsks_createDate"));
				task.setMembers(result.getString("tb_tsks_members"));
				task.setTaskNumber(result.getInt("tb_tsks_taskNumber"));
				task.setNumberTaskTargetDateChanges(result.getInt("tb_tsks_number_change_target_date"));
				
				taskCollection.add(task);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} else {
			getTaskFromDataBase();
		}
		return taskCollection;
	}
	
	
	public static ArrayList<Users> getUsersFromDataBase(){
		
		ArrayList<Users> userCollection = new ArrayList<Users>();
		String table                    = "tb_users";
		ResultSet result                = dbActivities.select(table);
		
		if (result != null) {
		try {
			while (result.next()) {
				
				Users user = new Users();
				
				user.setFullName(result.getString("tb_users_full_name"));
				user.setUserName(result.getString("tb_users_user_name"));
				user.setEmail(result.getString("tb_users_email"));
				user.setType(result.getString("tb_users_type"));
				user.setAvatar(result.getString("tb_users_avatar"));
				
				userCollection.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} else {
			getUsersFromDataBase();
		}
		return userCollection;
	}
	
	
	public static ArrayList<Comment> getCommentFromDataBase(){
		
		ArrayList<Comment> commentCollection = new ArrayList<Comment>();
		String table                         = "tb_comment";
		ArrayList<Users> users               = getUsersFromDataBase();	
		ResultSet result                     = dbActivities.select(table);
		
		if (result != null) {
		try {
			while (result.next()) {
				
				Comment comment = new Comment();
				
				for (int i = 0; i < users.size(); i++) {
					if (result.getString("tb_comment_user").equals(users.get(i).getFullName())) {
						comment.setUser(users.get(i));
					}
				}
				comment.setTaskNumber(result.getInt("tb_comment_taskNumber"));
				comment.setCommentDate(Date.date(result.getString("tb_comment_commentDate")));
				comment.setCommentContent(result.getString("tb_comment_commentContent"));
				comment.setCommentTime(result.getTime("tb_comment_time"));
				commentCollection.add(comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} else {
			getCommentFromDataBase();
		}
		return commentCollection;
	}
	
	public static ArrayList<Object> getAppronFromDataBase() {

		String table                       = "tb_operators";
		ArrayList<Object> appronCollection = new ArrayList<Object>();
		ResultSet result                   = dbActivities.select(table);

		if (result != null) {
		
		try {
			while (result.next()) {

				Appron appron = new Appron();

				appron.setNumber(result.getString("tb_operators_number_apron"));
				
				if (result.getString("tb_operators_date_change_appron") == null) {
					appron.setClothesDate(LocalDate.of(2021, 1, 1));
				} else {
					appron.setClothesDate(Date.date(result.getString("tb_operators_date_change_appron")));
				}
				
				
				appronCollection.add(appron);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else {
			getAppronFromDataBase();
		}
		return appronCollection;
	}
	
	
	
	public static ArrayList<Object> getHeaterFromDataBase() {

		String table                       = "tb_operators";
		ArrayList<Object> heaterCollection = new ArrayList<Object>();
		ResultSet result                   = dbActivities.select(table);

		if (result != null) {
		
		try {
			while (result.next()) {

				Heater heater = new Heater();

				heater.setNumber(result.getString("tb_operators_number_heater"));	

				if (result.getString("tb_operators_date_change_heater") == null) {
					heater.setClothesDate(LocalDate.of(2021, 1, 1));
				} else {
					heater.setClothesDate(Date.date(result.getString("tb_operators_date_change_heater")));
				}
				
				heaterCollection.add(heater);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else {
			getHeaterFromDataBase();
		}
		return heaterCollection;
	}
	
	public static ArrayList<Object> getSlipperFromDataBase() {

		String table                        = "tb_operators";
		ArrayList<Object> slipperCollection = new ArrayList<Object>();
		ResultSet result                    = dbActivities.select(table);

		if (result != null) {
		
		try {
			while (result.next()) {

				Slipper slipper = new Slipper();

				slipper.setNumber(result.getString("tb_operators_number_slippers"));
				
				if (result.getString("tb_operators_date_change_slippers") == null) {
					slipper.setClothesDate(LocalDate.of(2021, 1, 1));
				} else {
					slipper.setClothesDate(Date.date(result.getString("tb_operators_date_change_slippers")));
				}
						
				slipperCollection.add(slipper);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else {
			getSlipperFromDataBase();
		}
		return slipperCollection;
	}
}
					 

