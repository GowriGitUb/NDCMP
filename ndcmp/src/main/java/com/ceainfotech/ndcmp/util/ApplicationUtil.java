/**
 * 
 */
package com.ceainfotech.ndcmp.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.ceainfotech.ndcmp.model.UserRole;
import com.ceainfotech.ndcmp.repository.UserRepository;
import com.ceainfotech.ndcmp.repository.UserRoleRepository;

/**
 * @author Samy
 * 
 */
@Component
public class ApplicationUtil implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private UserUtil userUtil;
	
	@Autowired
	private ProjectUtil projectUtil;

	@Autowired
	private ReportingPeriodUtil reportingPeriodUtil;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	 

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		userUtil.saveSuperAdmin("superadmin");
		projectUtil.saveProjectAdmin("projectadmin");
		projectUtil.addProject("UNODC");
		
		//Create user roles
		 UserRole getSuperAdminUserRole = userRoleRepository.findByName("SUPER_ADMIN");
			if (getSuperAdminUserRole == null || getSuperAdminUserRole.getId() == null) {
				UserRole userRole = new UserRole();
				userRole.setName("SUPER_ADMIN");
				userRole.setDescription("System Administrator");
				userRole.setStatus(Status.ACTIVE.getName());
				userRoleRepository.save(userRole);
			}
			
			UserRole getStatusUpdaterUserRole = userRoleRepository.findByName("STATUS_UPDATER");
			if (getStatusUpdaterUserRole == null || getStatusUpdaterUserRole.getId() == null) {
				UserRole userRole = new UserRole();
				userRole.setName("STATUS_UPDATER");
				userRole.setDescription("Status Updater");
				userRole.setStatus(Status.ACTIVE.getName());
				userRoleRepository.save(userRole);
			}
			
			UserRole getProjectAdminUserRole = userRoleRepository.findByName("PROJECT_ADMIN");
			if (getProjectAdminUserRole == null || getProjectAdminUserRole.getId() == null) {
				UserRole userRole = new UserRole();
				userRole.setName("PROJECT_ADMIN");
				userRole.setDescription("Project Administrator");
				userRole.setStatus(Status.ACTIVE.getName());
				userRoleRepository.save(userRole);
			}
			
			UserRole getStatusReviewerUserRole = userRoleRepository.findByName("STATUS_REVIEWER");
			if (getStatusReviewerUserRole == null || getStatusReviewerUserRole.getId() == null) {
				UserRole userRole = new UserRole();
				userRole.setName("STATUS_REVIEWER");
				userRole.setDescription("Status Reviewer");
				userRole.setStatus(Status.ACTIVE.getName());
				userRoleRepository.save(userRole);
			}
			
			UserRole getProjectPlannerUserRole = userRoleRepository.findByName("PROJECT_PLANNER");
			if (getProjectPlannerUserRole == null || getProjectPlannerUserRole.getId() == null) {
				UserRole userRole = new UserRole();
				userRole.setName("PROJECT_PLANNER");
				userRole.setDescription("Project Planner");
				userRole.setStatus(Status.ACTIVE.getName());
				userRoleRepository.save(userRole);
			}
			
			UserRole getStatusApproverUserRole = userRoleRepository.findByName("STATUS_APPROVER");
			if (getStatusApproverUserRole == null || getStatusApproverUserRole.getId() == null) {
				UserRole userRole = new UserRole();
				userRole.setName("STATUS_APPROVER");
				userRole.setDescription("Status Approver");
				userRole.setStatus(Status.ACTIVE.getName());
				userRoleRepository.save(userRole);
			}
			
		 
		List<String> years = new ArrayList<String>();
		years.add("2016");
		years.add("2017");
		years.add("2018");
		years.add("2019");
		years.add("2020");
		if (years != null && years.size() > 0) {
			for (String year : years) {
				 reportingPeriodUtil.saveReport(year);
			}
		}
	}

}
