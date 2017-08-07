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
public class OutcomeDTO {

	private Integer id;
	private String sequenceNumber;
	private String name;
	private String status;
	private List<OutputDTO> outputs = new ArrayList<OutputDTO>();

	/**
	 * 
	 */
	public OutcomeDTO() {
	}

	/**
	 * @param id
	 * @param sequenceNumber
	 * @param outCome
	 * @param status
	 * @param outputs
	 */
	public OutcomeDTO(Integer id, String sequenceNumber, String name, String status, List<OutputDTO> outputs) {
		this.id = id;
		this.sequenceNumber = sequenceNumber;
		this.name = name;
		this.status = status;
		this.outputs = outputs;
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
	 * @return the outCome
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param outCome
	 *            the outCome to set
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
	 * @return the outputs
	 */
	public List<OutputDTO> getOutputs() {
		return outputs;
	}

	/**
	 * @param outputs
	 *            the outputs to set
	 */
	public void setOutputs(List<OutputDTO> outputs) {
		this.outputs = outputs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OutcomeDTO [id=" + id + ", sequenceNumber=" + sequenceNumber + ", name=" + name + ", status=" + status + "]";
	}

}
