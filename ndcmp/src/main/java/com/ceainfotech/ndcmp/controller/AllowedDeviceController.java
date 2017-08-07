/**
 * 
 */
package com.ceainfotech.ndcmp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ceainfotech.ndcmp.model.AllowedDeviceInfo;
import com.ceainfotech.ndcmp.service.AllowedDeviceService;
import com.ceainfotech.ndcmp.util.ConstantUtil;
import com.ceainfotech.ndcmp.util.PrincipalUtil;

/**
 * @author Gowri
 * 
 */
@Controller
@RequestMapping(value = "/api/**")
@PropertySource(value = { "classpath:application.properties" })
public class AllowedDeviceController {

	@Autowired
	AllowedDeviceService allowedDeviceService;

	@Autowired(required = true)
	public HttpServletRequest request;
	
	@Autowired
	private ConstantUtil constantUtil;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	PrincipalUtil principalUtil;
	
	String action,module="";



	/**
	 * List all registered device information
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "allowdDeviceList", method = RequestMethod.GET)
	public ModelAndView listAllAllowedDeviceInfo(Model model) {
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
			List<AllowedDeviceInfo> listAllowedDevice = allowedDeviceService.listAllAllowedDevice();
			model.addAttribute("listAllowedDevice", listAllowedDevice);
			model.addAttribute("allowedDeviceCount", environment.getRequiredProperty("alloweddevice.count"));
			return new ModelAndView("allowedDevices/allowedDeviceList");
		}else{
			return new ModelAndView("login");
		}
	}

	/**
	 * Approve device
	 * @throws Exception 
	 */

	@RequestMapping(value = "approveDevice", method = RequestMethod.GET)
	public ModelAndView approveDevice(@RequestParam Integer id, Model model) throws Exception {
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
			if (id != null) {
				AllowedDeviceInfo allowedDeviceInfo = allowedDeviceService.getById(id);
				if (allowedDeviceInfo != null) {
					Integer allowedDeviceCount = Integer.parseInt(environment.getRequiredProperty("alloweddevice.count"));
					if (allowedDeviceService.getActiveMobileDeiveCount(true) <= allowedDeviceCount) {
						HttpSession session = request.getSession();
						Object object = session.getAttribute("username");
						Date date = new Date();
						SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
						allowedDeviceInfo.setApprovedBy(object.toString());
						allowedDeviceInfo.setApprovedOn(dateformat.format(date));
						allowedDeviceInfo.setDeviceStatus(true);
						allowedDeviceService.deviceRegisteration(allowedDeviceInfo);
						
						action="Approve Device "+allowedDeviceInfo.getMobileName()+" is Approved";
						module="Status Module";
						constantUtil.saveAudition(action, module, request);
						
					} else {
						model.addAttribute("allowedDeviceMsg", "You have reached maximum limit of mobile devices ");
						List<AllowedDeviceInfo> listAllowedDevice = allowedDeviceService.listAllAllowedDevice();
						model.addAttribute("listAllowedDevice", listAllowedDevice);
						return new ModelAndView("allowedDevices/allowedDeviceList");
					}
				}
			}
			return new ModelAndView("redirect:allowdDeviceList");
		}else{
			return new ModelAndView("login");
		}
		
	}

	/**
	 * Dis approve the device
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "disApproveDevice", method = RequestMethod.GET)
	public ModelAndView disapproveDevice(@RequestParam Integer id, Model model) throws Exception {
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
			if (id != null) {
				AllowedDeviceInfo allowedDeviceInfo = allowedDeviceService.getById(id);
				if (allowedDeviceInfo != null) {
					HttpSession session = request.getSession();
					Object object = session.getAttribute("username");
					Date date = new Date();
					allowedDeviceInfo.setModifiedBy(object.toString());
					allowedDeviceInfo.setModificationTime(date);
					allowedDeviceInfo.setDeviceStatus(false);
					allowedDeviceService.deviceRegisteration(allowedDeviceInfo);
					
					action="Approve Device "+allowedDeviceInfo.getMobileName()+" is disApproved";
					module="Status Module";
					constantUtil.saveAudition(action, module, request);
					
					return new ModelAndView("redirect:allowdDeviceList");
				}
			}
			return new ModelAndView("redirect:allowdDeviceList");
		}else{
			return new ModelAndView("login");
		}
		
	}

	/**
	 * Delete allowed device information
	 * 
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "deleteDevice", method = RequestMethod.GET)
	public ModelAndView deleteDevice(@RequestParam Integer id) throws Exception {
		if (id != null) {
//			allowedDeviceService.deleteById(id);
			AllowedDeviceInfo allowedDeviceInfo = allowedDeviceService.getById(id);
			if (allowedDeviceInfo != null) {
				HttpSession session = request.getSession();
				Object object = session.getAttribute("username");
				Date date = new Date();
				allowedDeviceInfo.setModifiedBy(object.toString());
				allowedDeviceInfo.setModificationTime(date);
				allowedDeviceInfo.setDeleteStatus(true);
				allowedDeviceService.deviceRegisteration(allowedDeviceInfo);
				action="Allowed Device "+allowedDeviceInfo.getMobileName()+" is Deleted";
				module="Status Module";
				constantUtil.saveAudition(action, module, request);
			}
			
			return new ModelAndView("redirect:allowdDeviceList");
		}
		return null;
	}
}
