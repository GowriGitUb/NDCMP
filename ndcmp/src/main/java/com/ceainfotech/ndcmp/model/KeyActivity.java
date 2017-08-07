/**
 * 
 */
package com.ceainfotech.ndcmp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Gowri
 *
 */
@Entity
@Table(name = "tbl_key_activity")
public class KeyActivity extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name  = "SEQUENCE_NUMBER", length = 25, nullable = false)
	private String sequenceNumber;
	
	@Column(name  = "NAME")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "OUTPUT")
	private Output output;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "SYNC_STATUS" , columnDefinition="BOOLEAN DEFAULT FALSE")
	private boolean syncStatus;
	
	private transient List<SubActivity> subActivities;
	
	@Transient
	private String error;
	
	
	
	
	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

	public KeyActivity() {
	}

	/**
	 * @return the sequenceNumber
	 */
	public String getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * @param sequenceNumber the sequenceNumber to set
	 */
	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
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

	/**
	 * @return the output
	 */
	public Output getOutput() {
		return output;
	}

	/**
	 * @param output the output to set
	 */
	public void setOutput(Output output) {
		this.output = output;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the syncStatus
	 */
	public boolean isSyncStatus() {
		return syncStatus;
	}

	/**
	 * @param syncStatus the syncStatus to set
	 */
	public void setSyncStatus(boolean syncStatus) {
		this.syncStatus = syncStatus;
	}

	/**
	 * @return the subActivities
	 */
	public List<SubActivity> getSubActivities() {
		return subActivities;
	}

	/**
	 * @param subActivities the subActivities to set
	 */
	public void setSubActivities(List<SubActivity> subActivities) {
		this.subActivities = subActivities;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "KeyActivity [sequenceNumber=" + sequenceNumber + ", name=" + name + ", output=" + output + ", status=" + status + ", syncStatus="
				+ syncStatus + "]";
	}
	
}
