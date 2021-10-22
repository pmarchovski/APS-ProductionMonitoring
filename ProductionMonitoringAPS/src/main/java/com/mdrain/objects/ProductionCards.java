package com.mdrain.objects;

import java.time.LocalDate;
import java.util.ArrayList;

public class ProductionCards {

	
	LocalDate productionCardDate;
	LocalDate productionCardInputDate;
	String productionCardOperator;
	String productionCardOperation;
	String operationForMatrix;
	String productionCardUser;
	String productionCardProductType;
	String unionProdTypeAndOperationForMatrix;
	int productionCardOrder;
	int productionCardQty;
	int productionCardTime;
	boolean productionCardIsNewOperator;
	String visualControl     = "Визуален контрол";
	String other             = "Други";
	String elAss             = "Ел. монтаж";
	String preparePCB        = "Заготовка платки";
	String prepareRoller     = "Заготовка ролки";
	String mechAssOne        = "Механичен монтаж 1";
	String mechAssTwo        = "Механичен монтаж 2";
	String mechAssThree      = "Механичен монтаж 3";
	String mechAssFour       = "Механичен монтаж 4";
	String packaging         = "Опаковка";
	String cleanPlasticParts = "Почистване на пластмасови тела";
	String chipSet           = "Програмиране на чипове";
	String test              = "Тест";
	
	
	
	
	
	public String getUnionProdTypeAndOperationForMatrix() {
		return unionProdTypeAndOperationForMatrix;
	}

	public void setUnionProdTypeAndOperationForMatrix() {
		
		if (this.productionCardProductType != null && this.operationForMatrix != null) {
			
			this.unionProdTypeAndOperationForMatrix = this.productionCardProductType + " " + this.operationForMatrix;
		} else {
			this.unionProdTypeAndOperationForMatrix = "OTHERS" + " " + "Мех. монтаж";
		}
		
	}
	
	

	public String getProductionCardProductType() {
		return productionCardProductType;
	}
	
	public void setProductionCardProductType(ArrayList<Orders> ordersInfoCollection) {
		
		for (int i = 0; i < ordersInfoCollection.size(); i++) {
			
			if (this.productionCardOrder == ordersInfoCollection.get(i).getNumber()) {
				
				productionCardProductType = ordersInfoCollection.get(i).getProductType();
				
				return;
			} else {
				
				productionCardProductType = "";
			}
		}
	}
	
	
	public LocalDate getProductionCardDate() {
		return productionCardDate;
	}
	public void setProductionCardDate(LocalDate productionCardDate) {
		this.productionCardDate = productionCardDate;
	}
	
	
	
	public LocalDate getProductionCardInputDate() {
		return productionCardInputDate;
	}
	public void setProductionCardInputDate(LocalDate productionCardInputDate) {
		this.productionCardInputDate = productionCardInputDate;
	}
	
	
	
	public String getProductionCardOperator() {
		return productionCardOperator;
	}
	public void setProductionCardOperator(String productionCardOperator) {
		this.productionCardOperator = productionCardOperator;
	}
	
	
	
	public String getProductionCardOperation() {
		return productionCardOperation;
	}
	public void setProductionCardOperation(String productionCardOperation) {
		this.productionCardOperation = productionCardOperation;
	}
	
	
	public String getOperationForMatrix() {
		return operationForMatrix;
	}
	public void setOperationForMatrix() {
		
		if (this.productionCardOperation.equals(visualControl)) this.operationForMatrix = "Мех. монтаж";
		if (this.productionCardOperation.equals(other)) this.operationForMatrix         = "Мех. монтаж";
		if (this.productionCardOperation.equals(preparePCB)) this.operationForMatrix    = "Мех. монтаж";
		if (this.productionCardOperation.equals(prepareRoller)) this.operationForMatrix = "Мех. монтаж";
		if (this.productionCardOperation.equals(mechAssOne)) this.operationForMatrix    = "Мех. монтаж";
		if (this.productionCardOperation.equals(mechAssTwo)) this.operationForMatrix    = "Мех. монтаж";
		if (this.productionCardOperation.equals(mechAssThree)) this.operationForMatrix  = "Мех. монтаж";
		if (this.productionCardOperation.equals(mechAssFour)) this.operationForMatrix   = "Мех. монтаж";
		if (this.productionCardOperation.equals(packaging)) this.operationForMatrix     = "Мех. монтаж";
		if (this.productionCardOperation.equals(cleanPlasticParts)) this.operationForMatrix = "Мех. монтаж";
		if (this.productionCardOperation.equals(chipSet)) this.operationForMatrix       = "Мех. монтаж";
		if (this.productionCardOperation.equals(elAss)) this.operationForMatrix         = "Ел. монтаж";
		if (this.productionCardOperation.equals(test)) this.operationForMatrix          = "Тест";
		
		
	}
	
	public String getProductionCardUser() {
		return productionCardUser;
	}
	public void setProductionCardUser(String productionCardUser) {
		this.productionCardUser = productionCardUser;
	}
	
	
	
	public int getProductionCardOrder() {
		return productionCardOrder;
	}
	public void setProductionCardOrder(int productionCardOrder) {
		this.productionCardOrder = productionCardOrder;
	}
	
	
	
	public int getProductionCardQty() {
		return productionCardQty;
	}
	public void setProductionCardQty(int productionCardQty) {
		this.productionCardQty = productionCardQty;
	}
	
	
	
	public int getProductionCardTime() {
		return productionCardTime;
	}
	public void setProductionCardTime(int productionCardTime) {
		this.productionCardTime = productionCardTime;
	}
	
	
	
	public boolean isProductionCardIsNewOperator() {
		return productionCardIsNewOperator;
	}
	public void setProductionCardIsNewOperator(String newOperator) {
		
		if (newOperator.equals("Да")) this.productionCardIsNewOperator = true;
		if (newOperator.equals("Не") || newOperator.equals("")) this.productionCardIsNewOperator = false;

	}
}
