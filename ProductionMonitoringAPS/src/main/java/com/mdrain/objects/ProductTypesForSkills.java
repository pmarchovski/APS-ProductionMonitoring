package com.mdrain.objects;

import java.util.ArrayList;

import com.mdrain.logic.MaterialsProductTypeList;

public class ProductTypesForSkills {

	private String name;
	private String[] skills;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getSkills() {
		return skills;
	}
	public void setSkills(String[] skills) {
		this.skills = skills;
	}
	
	
	
    public void getTogetherSkills() {
		
    	
	
		ArrayList<ProductTypesForSkills> productTypeCollection = new ArrayList<ProductTypesForSkills>(); 
		
		for (int i = 0; i < MaterialsProductTypeList.getMaterialsGroupList().length; i++) {

               ProductTypesForSkills productType = new ProductTypesForSkills();
               
               productType.setName(MaterialsProductTypeList.getMaterialsGroupList()[i]);
               productType.setSkills(MaterialsProductTypeList.getOperationForSkillsMatrix());
              
               
               productTypeCollection.add(productType);
		}

	}
}
