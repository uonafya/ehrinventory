/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.inventory.web.controller.drug;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.model.InventoryDrug;
import org.openmrs.module.inventory.InventoryService;
import org.springframework.validation.Errors;

public class DrugValidator {
	/**
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    public boolean supports(Class clazz) {
    	return InventoryDrug.class.equals(clazz);
    }

	/**
     * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
     */
    public void validate(Object command, Errors error) {
    	InventoryDrug drug = (InventoryDrug) command;
    	
    	if( StringUtils.isBlank(drug.getName())){
    		error.reject("inventory.drug.name.required");
    	}
    	if( drug.getCategory() == null){
    		error.reject("inventory.drug.category.required");
    	}
    	if( drug.getDrugCore() == null){
    		error.reject("inventory.drug.drug.required");
    	}
    	if( CollectionUtils.isEmpty(drug.getFormulations()) && drug.getId() == null){
    		error.reject("inventory.drug.formulation.required");
    	}
    	if( drug.getUnit() == null ){
    		error.reject("inventory.drug.unit.required");
    	}
    	InventoryService inventoryService = (InventoryService) Context.getService(InventoryService.class);
    	InventoryDrug drugE = inventoryService.getDrugByName(drug.getName());
    	if(drug.getId() != null){
    		
		//drug.setFormulations(inventoryService.getDrugById(drug.getId()).getFormulations());
		/*if(drug.getFormulations()== inventoryService.getDrugById(drug.getId()).getFormulations() ){
				drug.setFormulations(inventoryService.getDrugById(drug.getId()).getFormulations());
			}*/
    		if(drugE != null ){
    			if(drugE.getId().intValue() != drug.getId().intValue()){
    				error.reject("inventory.drug.name.existed");
    			}
    		}
    	}else{
    		if(drugE != null){
    	    		error.reject("inventory.drug.name.existed");
    		}
    	}
    	
    	
    }
}
