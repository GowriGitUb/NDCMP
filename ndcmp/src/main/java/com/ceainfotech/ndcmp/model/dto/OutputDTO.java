/**
 * 
 */
package com.ceainfotech.ndcmp.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.ceainfotech.ndcmp.model.ReportingPeriod;

/**
 * @author Samy
 * 
 */
public class OutputDTO {

	private Integer id;
	private String sequenceNumber;
	private String name;
	private String status;
	private List<KeyActivityDTO> keyActivities = new ArrayList<KeyActivityDTO>();
	private List<IndicatorDTO> indicators = new ArrayList<IndicatorDTO>();
	private List<TargetDTO> targets = new ArrayList<TargetDTO>();

	/**
	 * 
	 */
	public OutputDTO() {
	}

	/**
	 * @param id
	 * @param sequenceNumber
	 * @param status
	 * @param keyActivities
	 */
	public OutputDTO(Integer id, String sequenceNumber, String name,
			String status, List<KeyActivityDTO> keyActivities,
			List<IndicatorDTO> indicators, List<TargetDTO> targets) {
		this.id = id;
		this.sequenceNumber = sequenceNumber;
		this.name = name;
		this.status = status;
		this.keyActivities = keyActivities;
		this.indicators = indicators;
		this.targets = targets;
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
	 * @return the sequenceNumber
	 */
	public String getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * @param sequenceNumber
	 *            the sequenceNumber to set
	 */
	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
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
	 * @return the keyActivities
	 */
	public List<KeyActivityDTO> getKeyActivities() {
		return keyActivities;
	}

	/**
	 * @param keyActivities
	 *            the keyActivities to set
	 */
	public void setKeyActivities(List<KeyActivityDTO> keyActivities) {
		this.keyActivities = keyActivities;
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
	 * @return the indicators
	 */
	public List<IndicatorDTO> getIndicators() {
		return indicators;
	}

	/**
	 * @param indicators
	 *            the indicators to set
	 */
	public void setIndicators(List<IndicatorDTO> indicators) {
		this.indicators = indicators;
	}

	/**
	 * @return the targets
	 */
	public List<TargetDTO> getTargets() {
		return targets;
	}

	/**
	 * @param targets
	 *            the targets to set
	 */
	public void setTargets(List<TargetDTO> targets) {
		this.targets = targets;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OutputDTO [id=" + id + ", sequenceNumber=" + sequenceNumber
				+ ", name=" + name + ", status=" + status + ", keyActivities="
				+ keyActivities + ", indicators=" + indicators + ", targets="
				+ targets + "]";
	}

}
