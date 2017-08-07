/**
 * 
 */
package com.ceainfotech.ndcmp.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


import com.ceainfotech.ndcmp.model.Auditing;
import com.ceainfotech.ndcmp.model.LoginAudit;
import com.ceainfotech.ndcmp.repository.AuditRepository;
import com.ceainfotech.ndcmp.repository.LoginAuditRepository;

/**
 * @author Gowri
 *
 */
@Component
public final class ConstantUtil {

	public static String DD_MM_YYYY_HH_MM_ss = "dd-MM-yyyy HH-mm-ss";

	public static SimpleDateFormat dateFormat = null;
	
	public static final String SUPERADMIN_ROLE = "SUPER_ADMIN";
	
	public static final String PROJECTADMIN_ROLE = "PROJECT_ADMIN";
	
	public static final String STATUS_REVIEWER_ROLE = "STATUS_REVIEWER";
	
	public static final String STATUS_UPDATER = "STATUS_UPDATER";
	
	public static final String STATUS_APPROVER = "STATUS_APPROVER";
	
	public static final String  LEAD_AGENCY = "LEAD_AGENCY";
	
	public static final String PARTNER_AGENCY = "PARTNER_AGENCY";
	
	public static final String PROJECT_PLANNER = "PROJECT_PLANNER";
	
	public static final String SUPERADMIN_USERNAME = "superadmin";
	
	
	@Autowired
	private AuditRepository auditRepository;
	
	@Autowired
	private LoginAuditRepository loginAuditRepository;

	public static String getDateString(Date date, String format) {
		dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	public static String getRequestRemoteAddr(HttpServletRequest request) throws Exception{
		
		String ip = request.getRemoteAddr();
		if (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
		    InetAddress inetAddress = InetAddress.getLocalHost();
		    String ipAddress = inetAddress.getHostAddress();
		    ip = ipAddress;
		}
		return ip;
	}
	
	public static String getMyIP(){
		URL ipAdress;
		 String host="";

        try {
            ipAdress = new URL("http://bot.whatismyipaddress.com/");

            BufferedReader in = new BufferedReader(new InputStreamReader(ipAdress.openStream()));

            host = in.readLine();
            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return host;
	}
	
	/*public static String getSystemIp() throws SocketException{
		int count=0;
		String ip="";
		Enumeration e = NetworkInterface.getNetworkInterfaces();
		while(e.hasMoreElements())
		{
		    NetworkInterface n = (NetworkInterface) e.nextElement();
		    Enumeration ee = n.getInetAddresses();
		    while (ee.hasMoreElements())
		    {
		    	count++;
		        InetAddress i = (InetAddress) ee.nextElement();
		        if(count == 2){
		        	 ip=i.toString().substring(1);
		        }
		    }
		}	
		return ip;
	}*/
	
	public void saveAudition(String action,String module,HttpServletRequest request) throws Exception{
		Auditing auditing=new Auditing();
		auditing.setAction(action);
		auditing.setModuleName(module);
		auditing.setUser(PrincipalUtil.getPrincipal());
		auditing.setModificationDate(new Date());
		auditing.setHost(ConstantUtil.getRequestRemoteAddr(request));
		auditRepository.save(auditing);
	}
	
	public void saveLoggingHistory(String username,String userRole,String agencyCode,HttpServletRequest request) throws Exception{
		
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
		 Date date = new Date();
		 String loginDate=simpleDateFormat.format(date);
		 
		 
		LoginAudit loginAudit=new LoginAudit();
		loginAudit.setAccessedFrom(getRequestRemoteAddr(request));
		loginAudit.setLoginUser(username);
		loginAudit.setHost("");
		loginAudit.setUserRole(userRole);
		loginAudit.setAgencyCode(agencyCode);
		loginAudit.setLoginDate(loginDate);
		loginAuditRepository.save(loginAudit);
	}
	
	/**
	 * Gowri System
	 */
//	public static String CONTEXT_PATH ="/opt/BoscoIts/Projects/ndcmp/ws2/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/ndcmp/static/export/";
//	public static String REPORT_PATH = "http://192.168.1.158:8080/ndcmp/static/export/";
//	
//	public static String CONTEXT_PATH ="/opt/BoscoIts/Projects/ndcmp/ws2/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/ndcmp/static/export/";
//	public static String REPORT_PATH = "http://192.168.1.158:8080/ndcmp/static/export/";
	

	/**
	 * Prem System
	 */
//	public static String CONTEXT_PATH ="/home/bosco/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/ndcmp/static/export/";
//	public static String REPORT_PATH = "http://192.168.1.137:8080/ndcmp/static/export/";
//	
	/**
	 * Samy System
	 */
//	public static String CONTEXT_PATH ="/BOSCO_PROJECTS/UNODC/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/ndcmp/static/export/";
//	public static String REPORT_PATH = "http://192.168.1.132:8081/ndcmp/static/export/";
	
	/**
	 * Mani System
	 */
//	public static String CONTEXT_PATH ="/opt/Mani/Bosco-Its/Projects/Eclipse-Workspace/NDCMP/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/ndcmp/static/export/";
//	public static String REPORT_PATH = "http://192.168.1.128:8080/ndcmp/static/export/";
	
	
	
	/**
	 * Jeeva System
	 */
//	public static String CONTEXT_PATH ="/opt/workspace/NDCMP/NewWorksapace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/ndcmp/static/export/";
//	public static String REPORT_PATH = "http://192.168.1.162:8080/ndcmp/static/export/";
	


// **********************//**********************************//******************************************************//********************************
	
		/**
		 * Pushpa Ranjitha A
		 */
		public static String CONTEXT_PATH ="E:/Bosco Soft/Workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/ndcmp/static/export/";
		public static String REPORT_PATH = "http://192.168.1.168:8080/ndcmp/static/export/";
//		
		/**
		 * Ubuntu Server - CEA Server (EC2)
		 */
//		public static String CONTEXT_PATH ="/usr/lib/apache-tomcat-7.0.64/webapps/ndcmp/static/report/";
//		public static String REPORT_PATH = "http://apps.ceainfotech.com:8080/ndcmp/static/report/";
//		public static String APP_URL = "http://apps.ceainfotech.com:8080/ndcmp";
		
//		public static String CONTEXT_PATH ="/usr/lib/apache-tomcat-7.0.64/webapps/ndcmp/static/report/";
//		public static String REPORT_PATH = "http://35.154.12.134:8080/ndcmp/static/report/";
//		public static String APP_URL = "http://35.154.12.134:8080/ndcmp";
		
		/**
		 * Client System
		 */
//		public static String CONTEXT_PATH ="D:/Tomcat-27/webapps/ndcmp/static/report/";
//		public static String REPORT_PATH = "http://localhost:8080/ndcmp/static/report/";
//		public static String APP_URL = "http://localhost:8080/ndcmp";
}
