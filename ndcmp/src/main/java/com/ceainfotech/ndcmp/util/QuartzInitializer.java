/**
 * 
 */
package com.ceainfotech.ndcmp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author jeeva
 *
 */
@Component
public class QuartzInitializer implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	private ModuleUtilTask moduleUtilTask;

	@Autowired
	private FeatureUtilTasks featureUtilTasks;
	@Autowired
	private ModuleFeatureUtilsTask moduleFeatureUtilsTask;
	@Autowired
	private SuperAdminModulesUtillTask superAdminModulesUtillTask;
	
	

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {

		// Save user Roles
		try {

			moduleUtilTask.saveAll();

			featureUtilTasks.saveAll();

			moduleFeatureUtilsTask.saveAll();
			
			superAdminModulesUtillTask.saveAll();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
