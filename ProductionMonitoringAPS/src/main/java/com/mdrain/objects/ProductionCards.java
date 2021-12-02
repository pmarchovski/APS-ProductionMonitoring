package com.mdrain.objects;

import java.time.LocalDate;
import java.util.ArrayList;

import com.mdrain.variables.Variables;

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
	Variables variables = new Variables();
	
	public String getUnionProdTypeAndOperationForMatrix() {
		return unionProdTypeAndOperationForMatrix;
	}

	public void setUnionProdTypeAndOperationForMatrix() {
		
		if (this.productionCardProductType != null && this.operationForMatrix != null) {
			
			this.unionProdTypeAndOperationForMatrix = this.productionCardProductType + " " + this.operationForMatrix;
		} else {
			this.unionProdTypeAndOperationForMatrix = variables.OTHERS + " " + variables.GLOBAL_MECH_ASS;
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
		
		if (this.productionCardOperation.equals(variables.VISUAL_CONTROL)) this.operationForMatrix      = variables.GLOBAL_MECH_ASS;
		if (this.productionCardOperation.equals(variables.OTHER)) this.operationForMatrix               = variables.GLOBAL_MECH_ASS;
		if (this.productionCardOperation.equals(variables.PREPARE_PCB)) this.operationForMatrix         = variables.GLOBAL_MECH_ASS;
		if (this.productionCardOperation.equals(variables.PREPARE_ROLLER)) this.operationForMatrix      = variables.GLOBAL_MECH_ASS;
		if (this.productionCardOperation.equals(variables.MECH_ASS_ONE)) this.operationForMatrix        = variables.GLOBAL_MECH_ASS;
		if (this.productionCardOperation.equals(variables.MECH_ASS_TWO)) this.operationForMatrix        = variables.GLOBAL_MECH_ASS;
		if (this.productionCardOperation.equals(variables.MECH_ASS_THREE)) this.operationForMatrix      = variables.GLOBAL_MECH_ASS;
		if (this.productionCardOperation.equals(variables.MECH_ASS_FOUR)) this.operationForMatrix       = variables.GLOBAL_MECH_ASS;
		if (this.productionCardOperation.equals(variables.PACKAGING)) this.operationForMatrix           = variables.GLOBAL_MECH_ASS;
		if (this.productionCardOperation.equals(variables.CLEAN_PLASTIC_PARTS)) this.operationForMatrix = variables.GLOBAL_MECH_ASS;
		if (this.productionCardOperation.equals(variables.CHIP_SET)) this.operationForMatrix            = variables.GLOBAL_MECH_ASS;
		if (this.productionCardOperation.equals(variables.EL_ASS)) this.operationForMatrix              = variables.GLOBAL_ELL_ASS;
		if (this.productionCardOperation.equals(variables.TEST)) this.operationForMatrix                = variables.GLOBAL_TEST;
			
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
		
		if (newOperator.equals(variables.YES)) this.productionCardIsNewOperator = true;
		if (newOperator.equals(variables.NO) || newOperator.equals("")) this.productionCardIsNewOperator = false;

	}
}
