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

package org.openmrs.module.ehrinventory.schedule;

import org.openmrs.api.context.Context;
import org.openmrs.module.ehrinventory.InventoryService;
import org.openmrs.scheduler.tasks.AbstractTask;

/**
 * <p> Class: ExampleTask </p>
 * <p> Package: org.openmrs.module.inventory.schedule </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Feb 24, 2011 4:31:57 PM </p>
 * <p> Update date: Feb 24, 2011 4:31:57 PM </p>
 **/
public class ExampleTask  extends AbstractTask {
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		try {
			Context.openSession();
			// do something
			InventoryService inventoryService = (InventoryService) Context.getService(InventoryService.class);
			System.out.println("store 1: "+inventoryService.getStoreById(1));
			System.out.println("tao la chuyen day ok? ");
			Context.closeSession();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void authenticate() {
		// TODO Auto-generated method stub
		super.authenticate();
	}

	@Override
	public void shutdown() {
		System.out.println("tao can shutdown ok?");
		super.shutdown();
	}

}
