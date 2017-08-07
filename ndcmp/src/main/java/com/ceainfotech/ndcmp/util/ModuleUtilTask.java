/**
 * 
 */
package com.ceainfotech.ndcmp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceainfotech.ndcmp.model.Module;
import com.ceainfotech.ndcmp.service.ModuleService;

/**
 * @author jeeva
 *
 */
@Service
public class ModuleUtilTask {

	@Autowired
	private ModuleService moduleService;
	
	public void save(Modules modules) {
		Module module = moduleService.getByModule(modules.getName());
		if(module == null || module.getId() == null) {
			module = new Module();
			module.setModule(modules.getName());
			module.setOrderId(modules.getOrderId());
			moduleService.save(module);
		}
	}
	
	public void saveAll() {
		for (Modules modules : Modules.values()) {
			save(modules);
		}
	}
}
