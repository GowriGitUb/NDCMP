/**
 * 
 */
package com.ceainfotech.ndcmp.util;

import java.util.List;

import com.ceainfotech.ndcmp.model.Agency;
import com.ceainfotech.ndcmp.model.KeyActivity;
import com.ceainfotech.ndcmp.model.SubActivity;

/**
 * @author Gowri
 *
 */
public class ProjectCoptData {

	private String name;
	
	private KeyActivity keyActivity;
	
	private SubActivity subActivity;

	private List<Agency> leadAgency;

	private List<Agency> partnerAgency;
	
	private String mov;
	
	

	/**
	 * @return the mov
	 */
	public String getMov() {
		return mov;
	}

	/**
	 * @param mov the mov to set
	 */
	public void setMov(String mov) {
		this.mov = mov;
	}

	/**
	 * @return the keyActivity
	 */
	public KeyActivity getKeyActivity() {
		return keyActivity;
	}

	/**
	 * @param keyActivity
	 *            the keyActivity to set
	 */
	public void setKeyActivity(KeyActivity keyActivity) {
		this.keyActivity = keyActivity;
	}

	/**
	 * @return the leadAgency
	 */
	public List<Agency> getLeadAgency() {
		return leadAgency;
	}

	/**
	 * @param leadAgency
	 *            the leadAgency to set
	 */
	public void setLeadAgency(List<Agency> leadAgency) {
		this.leadAgency = leadAgency;
	}

	/**
	 * @return the partnerAgency
	 */
	public List<Agency> getPartnerAgency() {
		return partnerAgency;
	}

	/**
	 * @param partnerAgency
	 *            the partnerAgency to set
	 */
	public void setPartnerAgency(List<Agency> partnerAgency) {
		this.partnerAgency = partnerAgency;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public SubActivity getSubActivity() {
		return subActivity;
	}

	public void setSubActivity(SubActivity subActivity) {
		this.subActivity = subActivity;
	}

}
