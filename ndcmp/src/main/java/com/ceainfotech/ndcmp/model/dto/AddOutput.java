/**
 * 
 */
package com.ceainfotech.ndcmp.model.dto;

/**
 * @author Samy
 *
 */
public class AddOutput {
	
	private String outcome;
	
	private String outputSequenceNumber;
	
	private String output;
	
	private String indicator;
	
	private String target;
	private Integer outcomeId;
	
	
	
	/**
	 * @return the outcomeId
	 */
	public Integer getOutcomeId() {
		return outcomeId;
	}

	/**
	 * @param outcomeId the outcomeId to set
	 */
	public void setOutcomeId(Integer outcomeId) {
		this.outcomeId = outcomeId;
	}

	/**
	 * 
	 */
	public AddOutput() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param strategicPillar
	 * @param theme
	 * @param outcomeSequenceNumber
	 * @param outcome
	 * @param outputSequenceNumber
	 * @param output
	 * @param indicator
	 * @param target
	 */
	public AddOutput(String theme, String outcomeSequenceNumber, String outcome, String outputSequenceNumber, String output,
			String indicator, String target) {
		this.outcome = outcome;
		this.outputSequenceNumber = outputSequenceNumber;
		this.output = output;
		this.indicator = indicator;
		this.target = target;
	}

	/**
	 * @return the outcome
	 */
	public String getOutcome() {
		return outcome;
	}

	/**
	 * @param outcome the outcome to set
	 */
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	/**
	 * @return the outputSequenceNumber
	 */
	public String getOutputSequenceNumber() {
		return outputSequenceNumber;
	}

	/**
	 * @param outputSequenceNumber the outputSequenceNumber to set
	 */
	public void setOutputSequenceNumber(String outputSequenceNumber) {
		this.outputSequenceNumber = outputSequenceNumber;
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
	 * @return the indicator
	 */
	public String getIndicator() {
		return indicator;
	}

	/**
	 * @param indicator the indicator to set
	 */
	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

	/**
	 * @return the target
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return "AddOutput [outcome=" + outcome + ", outputSequenceNumber="
				+ outputSequenceNumber + ", output=" + output + ", indicator="
				+ indicator + ", target=" + target + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	

}
