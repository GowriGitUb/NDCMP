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

/**
 * @author bosco
 * 
 */
@Entity
@Table(name = "tbl_output")
public class Output extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "SEQUENCE_NUMBER", length = 25, nullable = false)
	private String sequenceNumber;

	@Column(name = "OUTPUT", length = 250, nullable = false)
	private String output;

	@Column(name = "STATUS", length = 20)
	private String status;

	@ManyToOne
	@JoinColumn(name = "OUTCOME_ID", referencedColumnName = "ID")
	private Outcome outcome;

	@Column(name = "SYNC_STATUS" , columnDefinition="BOOLEAN DEFAULT FALSE")
	private boolean syncStatus;

	private transient List<KeyActivity> keyActivities;
	
	private transient List<Indicator> indicators;
	
	private transient List<Target> targets;
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
	 * @return the output
	 */
	public String getOutput() {
		return output;
	}

	/**
	 * @param output the output to set
	 */
	public void setOutput(String output) {
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
	 * @return the outcome
	 */
	public Outcome getOutcome() {
		return outcome;
	}

	/**
	 * @param outcome the outcome to set
	 */
	public void setOutcome(Outcome outcome) {
		this.outcome = outcome;
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
	 * @return the keyActivities
	 */
	public List<KeyActivity> getKeyActivities() {
		return keyActivities;
	}

	/**
	 * @param keyActivities the keyActivities to set
	 */
	public void setKeyActivities(List<KeyActivity> keyActivities) {
		this.keyActivities = keyActivities;
	}

	/**
	 * @return the indicators
	 */
	public List<Indicator> getIndicators() {
		return indicators;
	}

	/**
	 * @param indicators the indicators to set
	 */
	public void setIndicators(List<Indicator> indicators) {
		this.indicators = indicators;
	}

	/**
	 * @return the targets
	 */
	public List<Target> getTargets() {
		return targets;
	}

	/**
	 * @param targets the targets to set
	 */
	public void setTargets(List<Target> targets) {
		this.targets = targets;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((outcome == null) ? 0 : outcome.hashCode());
		result = prime * result + ((output == null) ? 0 : output.hashCode());
		result = prime * result
				+ ((sequenceNumber == null) ? 0 : sequenceNumber.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + (syncStatus ? 1231 : 1237);
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Output other = (Output) obj;
		if (outcome == null) {
			if (other.outcome != null)
				return false;
		} else if (!outcome.equals(other.outcome))
			return false;
		if (output == null) {
			if (other.output != null)
				return false;
		} else if (!output.equals(other.output))
			return false;
		if (sequenceNumber == null) {
			if (other.sequenceNumber != null)
				return false;
		} else if (!sequenceNumber.equals(other.sequenceNumber))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (syncStatus != other.syncStatus)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Output [sequenceNumber=" + sequenceNumber + ", output=" + output + ", status=" + status + ", outcome=" + outcome + ", syncStatus="
				+ syncStatus + "]";
	}

}
