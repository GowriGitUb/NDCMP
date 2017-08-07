/**
 * 
 */
package com.ceainfotech.ndcmp.model;

import java.io.Serializable;

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
@Table(name = "tbl_target")
public class Target extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "MESSAGE")
	private String message;
	
	@Column(name = "STATUS", length = 30, nullable = false)
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "OUTPUT_ID", referencedColumnName = "ID")
	private Output output;
	
	@Column(name = "SYNC_STATUS" , columnDefinition="BOOLEAN DEFAULT FALSE")
	private boolean syncStatus;
	
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

	public Target() {
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Target [message=" + message + ", status=" + status + ", output=" + output + ", syncStatus=" + syncStatus + "]";
	}
	
}
