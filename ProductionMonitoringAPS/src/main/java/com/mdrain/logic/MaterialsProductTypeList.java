package com.mdrain.logic;

import java.util.ArrayList;

import com.mdrain.objects.ProductTypesForSkills;

public class MaterialsProductTypeList {

	
	public static String[] getMaterialsGroupList() {
		
		String[] productTypeDescription = {"SS", "CP 2\"", "CP 3\"", "CP 4\"", "DPM", "GPR 212", "GPR 415", "KM",
				"HSP 2\"", "HSP 3\"", "ECP 2\"", "ECP 3\"", "AB", "MKL 2\"", "MKL 3\"", "PRESENTER", "LPM",
				"GRUPPO DI STAMPA", "ELM 2\"", "ELM 3\"", "EPK", "EPM 2\"", "EPM 3\"", "FM 2\"", "FM 3\"", "XS", "MP",
				"MP2", "CMP", "SC", "KFM", "FHL", "TPH SOLDERING", "ROLLER MODULE", "KIT", "OTHERS", "CASSETTE",
				"DRIVER BOARD", "CHIP SET", "FFC", "OPTO ASS", "TPH PRODUCTION", "No type!!!" };
		
		return productTypeDescription;
	}
	
	
	public static ArrayList<String> getArrayListType(){
		
		String[] inputType = MaterialsProductTypeList.getMaterialsGroupList();
		ArrayList<String> productType = new ArrayList<String>(inputType.length);
		
		for (int i = 0; i < inputType.length; i++) {
			productType.add(inputType[i]);
		}
		
		return productType;
	}
	
	
	public static String[] getOperationForSkillsMatrix() {
		
		String mechAss = "Мех. монтаж";
		String elAss = "Ел. монтаж";
		String test = "Тест";
		String[] operationForSkillsMatrix = {mechAss, elAss, test};
			
		return operationForSkillsMatrix;
	}
	
	public static String[] getMaterialTypeFields() {
		
		String[] materialTypeFields = new String[getMaterialsGroupList().length * getOperationForSkillsMatrix().length];
		
		int i = 0;
		int j = 0;
		int k = 0;
		
		for (i = 0; i < getMaterialsGroupList().length; i++) {
			
			String firstField = getMaterialsGroupList()[i];
			j = 0;
			
			for (j = 0; j < getOperationForSkillsMatrix().length; j++) {
				
				materialTypeFields[k + j] = firstField + " " + getOperationForSkillsMatrix()[j];
				
				
			}
			k = k + 3;
		}	
		
		return materialTypeFields;
	}
	
	
}
