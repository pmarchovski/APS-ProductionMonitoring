package com.mdrain.singletons;

import java.util.ArrayList;

import com.mdrain.database.DataBaseActivities;
import com.mdrain.objects.Orders;

public class Singleton {

	static DataBaseActivities instance         = null;
	static Orders instanceOrders               = null;
	static ArrayList<String> instanceArrayList = null;

	/**
	 * DataBaseActivities
	 * @return instance
	 */
	public static DataBaseActivities getInstance() {

		if (instance == null) {
			instance = new DataBaseActivities();
		} else {
			return instance;
		}

		return instance;

	}

	/**
	 * Orders
	 * @return instanceOrder
	 */
	public static Orders getInstanceOrders() {

		if (instanceOrders == null) {
			instanceOrders = new Orders();
		} else {
			return instanceOrders;
		}
		return instanceOrders;
	}
	
	public static ArrayList<String> getInstanceArrayList(){
		
		if (instanceArrayList == null) {
			instanceArrayList= new ArrayList<String>();
		} else {
			return instanceArrayList;
		}
		return instanceArrayList;
	}
}
