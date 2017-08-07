/**
 * 
 */
package com.ceainfotech.ndcmp.model.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Samy
 * 
 */
public class ThemeDTO {

	private Integer id;
	private String name;
	private String status;
	private List<OutcomeDTO> outcomes = new ArrayList<OutcomeDTO>();

	/**
	 * 
	 */
	public ThemeDTO() {
	}

	/**
	 * @param id
	 * @param theme
	 * @param status
	 * @param outcomes
	 */
	public ThemeDTO(Integer id, String name, String status, List<OutcomeDTO> outcomes) {
		this.id = id;
		this.name = name;
		this.status = status;
		this.outcomes = outcomes;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the theme
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param theme
	 *            the theme to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the outcomes
	 */
	public List<OutcomeDTO> getOutcomes() {
		return outcomes;
	}

	/**
	 * @param outcomes
	 *            the outcomes to set
	 */
	public void setOutcomes(List<OutcomeDTO> outcomes) {
		this.outcomes = outcomes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ThemeDTO [id=" + id + ", name=" + name + ", status=" + status + "]";
	}

}
