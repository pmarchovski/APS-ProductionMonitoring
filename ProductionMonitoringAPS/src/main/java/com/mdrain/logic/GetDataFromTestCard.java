package com.mdrain.logic;

import java.util.ArrayList;

import com.mdrain.objects.OrderDefect;
import org.apache.commons.math3.util.Precision;

public class GetDataFromTestCard {

	public static ArrayList<ArrayList<Integer>> getDeffectCollectionValuePerMonths(int defectCollectionCount,
			ArrayList<Object> orderDefectCollection, OrderDefect orders) {

		int p = 0;
		int k = 0;
		int monthCount = 12;

		ArrayList<ArrayList<Integer>> deffectByMonthCollection = new ArrayList<ArrayList<Integer>>();

		for (int r = 1; r <= monthCount; r++) {

			// Нова инстация за колекция от данни за дефекти
			ArrayList<Integer> tempDeffectCollectionValue = new ArrayList<Integer>();

			// Създаване на елементи на колекцията с нулви стойности
			for (int i = 0; i < defectCollectionCount; i++) {
				tempDeffectCollectionValue.add(0);
			}

			// Временна нова инстация за колекция от данни за дефекти
			ArrayList<Integer> m = new ArrayList<Integer>();
			// Създаване на елементи на колекцията с нулви стойности
			for (int i = 0; i < defectCollectionCount; i++) {
				m.add(0);
			}
			// Стартираме общия цикъл
			for (int i = 0; i < orderDefectCollection.size() + 1; i++) {

				// Цикъл за добавяне на стойностите за дефектите към съществуващите такива
				for (int f = 0; f < tempDeffectCollectionValue.size(); f++) {
					tempDeffectCollectionValue.set(f, tempDeffectCollectionValue.get(f) + m.get(f));
				}

				// Зануляване на временната колекция
				for (int d = 0; d < tempDeffectCollectionValue.size(); d++) {
					m.set(d, 0);
				}

				// Проверка дали индекса на общия цикъл е по-малък от броя на индексите от
				// колекцията с дефекти
				if (i < orderDefectCollection.size()) {

					orders = (OrderDefect) orderDefectCollection.get(i);

					// Условие за търсене
					if (orders.getDate().getYear() == 2018 && orders.getDate().getMonthValue() == r) {

						// Цикъл за събиране на данните от колекцията
						for (int j = 0; j < orders.getDefectsQuantityCollection().size(); j++) {
							p = orders.getDefectsQuantityCollection().get(j);

							// Сетвъне на данни към временната колекция
							m.set(j, p);
						}
					}
				}
			}
		}
		return deffectByMonthCollection;
	}

	public static ArrayList<Integer> getDeffectCollectionValuePerYear(int defectCollectionCount,
			ArrayList<Object> orderDefectCollection, OrderDefect orders, int year, String department) {

		int p = 0;
		int k = 0;
		int monthCount = 12;

		// Нова инстация за колекция от данни за дефекти
		ArrayList<Integer> tempDeffectCollectionValue = new ArrayList<Integer>();

		// Създаване на елементи на колекцията с нулви стойности
		for (int i = 0; i < defectCollectionCount; i++) {
			tempDeffectCollectionValue.add(0);
		}

		// Временна нова инстация за колекция от данни за дефекти
		ArrayList<Integer> m = new ArrayList<Integer>();
		// Създаване на елементи на колекцията с нулви стойности
		for (int i = 0; i < defectCollectionCount; i++) {
			m.add(0);
		}
		// Стартираме общия цикъл
		for (int i = 0; i < orderDefectCollection.size() + 1; i++) {

			// Цикъл за добавяне на стойностите за дефектите към съществуващите такива
			for (int f = 0; f < tempDeffectCollectionValue.size(); f++) {
				tempDeffectCollectionValue.set(f, tempDeffectCollectionValue.get(f) + m.get(f));
			}

			// Зануляване на временната колекция
			for (int d = 0; d < tempDeffectCollectionValue.size(); d++) {
				m.set(d, 0);
			}

			// Проверка дали индекса на общия цикъл е по-малък от броя на индексите от
			// колекцията с дефекти
			if (i < orderDefectCollection.size()) {

				orders = (OrderDefect) orderDefectCollection.get(i);

				if (orders.getTypeProduction() != null) {

					// Условие за търсене
					if (orders.getDate().getYear() == year && orders.getTypeProduction().equals(department)) {

						// Цикъл за събиране на данните от колекцията
						for (int j = 0; j < orders.getDefectsQuantityCollection().size(); j++) {
							p = orders.getDefectsQuantityCollection().get(j);

							// Сетвъне на данни към временната колекция
							m.set(j, p);
						}
					}
				}
			}
		}

		return tempDeffectCollectionValue;
	}

	public static ArrayList<Integer> getDeffectCollectionValuePerMonth(int defectCollectionCount,
			ArrayList<Object> orderDefectCollection, OrderDefect orders, int year, int month, String department) {

		int p = 0;
		int k = 0;
		int monthCount = 12;
		int orderQuantity;

		// Нова инстация за колекция от данни за дефекти
		ArrayList<Integer> tempDeffectCollectionValue = new ArrayList<Integer>();

		// Създаване на елементи на колекцията с нулви стойности
		for (int i = 0; i < defectCollectionCount; i++) {
			tempDeffectCollectionValue.add(0);
		}

		// Временна нова инстация за колекция от данни за дефекти
		ArrayList<Integer> m = new ArrayList<Integer>();
		// Създаване на елементи на колекцията с нулви стойности
		for (int i = 0; i < defectCollectionCount; i++) {
			m.add(0);
		}
		// Стартираме общия цикъл
		for (int i = 0; i < orderDefectCollection.size() + 1; i++) {

			// Цикъл за добавяне на стойностите за дефектите към съществуващите такива
			for (int f = 0; f < tempDeffectCollectionValue.size(); f++) {
				tempDeffectCollectionValue.set(f, tempDeffectCollectionValue.get(f) + m.get(f));
			}

			// Зануляване на временната колекция
			for (int d = 0; d < tempDeffectCollectionValue.size(); d++) {
				m.set(d, 0);
			}

			// Проверка дали индекса на общия цикъл е по-малък от броя на индексите от
			// колекцията с дефекти
			if (i < orderDefectCollection.size()) {

				orders = (OrderDefect) orderDefectCollection.get(i);

				if (orders.getTypeProduction() != null) {

					// Условие за търсене
					if (orders.getDate().getYear() == year && orders.getDate().getMonthValue() == month
							&& orders.getTypeProduction().equals(department)) {

						// Цикъл за събиране на данните от колекцията
						for (int j = 0; j < orders.getDefectsQuantityCollection().size(); j++) {
							p = orders.getDefectsQuantityCollection().get(j);

							// Сетвъне на данни към временната колекция
							m.set(j, p);
						}
					}
				}
			}

		}

		return tempDeffectCollectionValue;
	}

	public static ArrayList<Double> getDeffectCollectionValuePerMonthMainChart(int defectCollectionCount,
			ArrayList<Object> orderDefectCollection, OrderDefect orders, int year, int month, String department,
			int ordersQuantity) {

		double p = 0;
		int k = 0;
		int monthCount = 12;
		int orderQuantity;

		double ordersQuantityDouble = ordersQuantity;

		// Нова инстация за колекция от данни за дефекти
		ArrayList<Double> tempDeffectCollectionValue = new ArrayList<Double>();

		// Създаване на елементи на колекцията с нулви стойности
		for (int i = 0; i < defectCollectionCount; i++) {
			tempDeffectCollectionValue.add(0.00);
		}

		// Временна нова инстация за колекция от данни за дефекти
		ArrayList<Double> m = new ArrayList<Double>();
		// Създаване на елементи на колекцията с нулви стойности
		for (int i = 0; i < defectCollectionCount; i++) {
			m.add(0.00);
		}
		// Стартираме общия цикъл
		for (int i = 0; i < orderDefectCollection.size() + 1; i++) {

			// Цикъл за добавяне на стойностите за дефектите към съществуващите такива
			for (int f = 0; f < tempDeffectCollectionValue.size(); f++) {
				tempDeffectCollectionValue.set(f, tempDeffectCollectionValue.get(f) + m.get(f));
			}

			// Зануляване на временната колекция
			for (int d = 0; d < tempDeffectCollectionValue.size(); d++) {
				m.set(d, 0.00);
			}

			// Проверка дали индекса на общия цикъл е по-малък от броя на индексите от
			// колекцията с дефекти
			if (i < orderDefectCollection.size()) {

				orders = (OrderDefect) orderDefectCollection.get(i);

				if (orders.getTypeProduction() != null) {

					// Условие за търсене
					if (orders.getDate().getYear() == year && orders.getDate().getMonthValue() == month
							&& orders.getTypeProduction().equals(department)) {

						// Цикъл за събиране на данните от колекцията
						for (int j = 0; j < orders.getDefectsQuantityCollection().size(); j++) {
							p = Precision.round(
									(orders.getDefectsQuantityCollection().get(j) / ordersQuantityDouble * 100), 4);

							// Сетвъне на данни към временната колекция
							m.set(j, p);
						}
					}
				}
			}

		}

		return tempDeffectCollectionValue;
	}

	public static ArrayList<Double> getDeffectCollectionValuePerYearMainChart(int defectCollectionCount,
			ArrayList<Object> orderDefectCollection, OrderDefect orders, int year, String department,
			int ordersQuantity) {

		double ordersQuantityDouble = ordersQuantity;

		double p = 0;
		int k = 0;
		int monthCount = 12;

		// Нова инстация за колекция от данни за дефекти
		ArrayList<Double> tempDeffectCollectionValue = new ArrayList<Double>();

		// Създаване на елементи на колекцията с нулви стойности
		for (int i = 0; i < defectCollectionCount; i++) {
			tempDeffectCollectionValue.add(0.00);
		}

		// Временна нова инстация за колекция от данни за дефекти
		ArrayList<Double> m = new ArrayList<Double>();
		// Създаване на елементи на колекцията с нулви стойности
		for (int i = 0; i < defectCollectionCount; i++) {
			m.add(0.00);
		}
		// Стартираме общия цикъл
		for (int i = 0; i < orderDefectCollection.size() + 1; i++) {

			// Цикъл за добавяне на стойностите за дефектите към съществуващите такива
			for (int f = 0; f < tempDeffectCollectionValue.size(); f++) {
				tempDeffectCollectionValue.set(f, tempDeffectCollectionValue.get(f) + m.get(f));
			}

			// Зануляване на временната колекция
			for (int d = 0; d < tempDeffectCollectionValue.size(); d++) {
				m.set(d, 0.00);
			}

			// Проверка дали индекса на общия цикъл е по-малък от броя на индексите от
			// колекцията с дефекти
			if (i < orderDefectCollection.size()) {

				orders = (OrderDefect) orderDefectCollection.get(i);

				if (orders.getTypeProduction() != null) {

					// Условие за търсене
					if (orders.getDate().getYear() == year && orders.getTypeProduction().equals(department)) {

						// Цикъл за събиране на данните от колекцията
						for (int j = 0; j < orders.getDefectsQuantityCollection().size(); j++) {

							p = Precision.round(
									(orders.getDefectsQuantityCollection().get(j) / ordersQuantityDouble * 100), 4);

							// Сетвъне на данни към временната колекция
							m.set(j, p);
						}
					}
				}
			}
		}

		return tempDeffectCollectionValue;
	}

}
