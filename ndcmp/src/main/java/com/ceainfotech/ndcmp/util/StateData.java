/**
 * 
 */
package com.ceainfotech.ndcmp.util;

import java.util.List;

import com.ceainfotech.ndcmp.model.Agency;
import com.ceainfotech.ndcmp.model.States;

/**
 * @author bosco
 *
 */
public class StateData {

	private States states;
	
	private List<States> listStates;
	
	private Agency agency;
	
	private List<Agency> agencies;
	
	public StateData() {
	}

	/**
	 * @return the states
	 */
	public States getStates() {
		return states;
	}

	/**
	 * @param states the states to set
	 */
	public void setStates(States states) {
		this.states = states;
	}

	/**
	 * @return the listStates
	 */
	public List<States> getListStates() {
		return listStates;
	}

	/**
	 * @param listStates the listStates to set
	 */
	public void setListStates(List<States> listStates) {
		this.listStates = listStates;
	}

	
	/**
	 * @return the agency
	 */
	public Agency getAgency() {
		return agency;
	}

	/**
	 * @param agency the agency to set
	 */
	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	/**
	 * @return the agencies
	 */
	public List<Agency> getAgencies() {
		return agencies;
	}

	/**
	 * @param agencies the agencies to set
	 */
	public void setAgencies(List<Agency> agencies) {
		this.agencies = agencies;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StateData [states=" + states + ", listStates=" + listStates
				+ ", agency=" + agency + ", agencies=" + agencies + "]";
	}
}
