/**
 * 
 */
package com.ceainfotech.ndcmp.model.dto;

/**
 * @author Samy
 *
 */
public class AddOutcome {
	
	private String theme;
	
	private String outcomeSequenceNumber;
	
	private String outcome;
	
	private String outputSequenceNumber;
	
	private String output;
	
	private String indicator;
	
	private String target;
	
	/**
	 * 
	 */
	public AddOutcome() {
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
	public AddOutcome(String theme, String outcomeSequenceNumber, String outcome, String outputSequenceNumber, String output,
			String indicator, String target) {
		this.theme = theme;
		this.outcomeSequenceNumber = outcomeSequenceNumber;
		this.outcome = outcome;
		this.outputSequenceNumber = outputSequenceNumber;
		this.output = output;
		this.indicator = indicator;
		this.target = target;
	}

	/**
	 * @return the theme
	 */
	public String getTheme() {
		return theme;
	}

	/**
	 * @param theme the theme to set
	 */
	public void setTheme(String theme) {
		this.theme = theme;
	}

	/**
	 * @return the outcomeSequenceNumber
	 */
	public String getOutcomeSequenceNumber() {
		return outcomeSequenceNumber;
	}

	/**
	 * @param outcomeSequenceNumber the outcomeSequenceNumber to set
	 */
	public void setOutcomeSequenceNumber(String outcomeSequenceNumber) {
		this.outcomeSequenceNumber = outcomeSequenceNumber;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AddTheme [theme=" + theme + ", outcomeSequenceNumber=" + outcomeSequenceNumber
				+ ", outcome=" + outcome + ", outputSequenceNumber=" + outputSequenceNumber + ", output=" + output + ", indicator=" + indicator
				+ ", target=" + target + "]";
	}

}
