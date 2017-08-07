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
public class StrategicPillarDTO {

	private Integer id;
	private String name;
	private String status;
	private List<ThemeDTO> themes = new ArrayList<ThemeDTO>();

	/**
	 * 
	 */
	public StrategicPillarDTO() {
	}

	/**
	 * @param id
	 * @param name
	 * @param status
	 * @param themes
	 */
	public StrategicPillarDTO(Integer id, String name, String status, List<ThemeDTO> themes) {
		this.id = id;
		this.name = name;
		this.status = status;
		this.themes = themes;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
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
	 * @return the themes
	 */
	public List<ThemeDTO> getThemes() {
		return themes;
	}

	/**
	 * @param themes
	 *            the themes to set
	 */
	public void setThemes(List<ThemeDTO> themes) {
		this.themes = themes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StrategicPillarDTO [id=" + id + ", name=" + name + ", status=" + status + "]";
	}

}
