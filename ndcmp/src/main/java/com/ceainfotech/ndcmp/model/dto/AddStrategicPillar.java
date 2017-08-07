/**
 * 
 */
package com.ceainfotech.ndcmp.model.dto;

/**
 * @author Samy
 *
 */
public class AddStrategicPillar {
	
	private String strategicPillar;
	
	private String project;
	
	private String strategicPillarSequenceNumber;
	
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
	public AddStrategicPillar() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param strategicPillarSequenceNumber
	 * @param theme
	 * @param outcomeSequenceNumber
	 * @param outcome
	 * @param outputSequenceNumber
	 * @param output
	 * @param indicator
	 * @param target
	 */
	public AddStrategicPillar(String strategicPillar, String project, String strategicPillarSequenceNumber, String theme, String outcomeSequenceNumber, String outcome,
			String outputSequenceNumber, String output, String indicator, String target) {
		this.strategicPillar = strategicPillar;
		this.project = project;
		this.strategicPillarSequenceNumber = strategicPillarSequenceNumber;
		this.theme = theme;
		this.outcomeSequenceNumber = outcomeSequenceNumber;
		this.outcome = outcome;
		this.outputSequenceNumber = outputSequenceNumber;
		this.output = output;
		this.indicator = indicator;
		this.target = target;
	}

	/**
	 * @return the strategicPillar
	 */
	public String getStrategicPillar() {
		return strategicPillar;
	}

	/**
	 * @param strategicPillar the strategicPillar to set
	 */
	public void setStrategicPillar(String strategicPillar) {
		this.strategicPillar = strategicPillar;
	}

	/**
	 * @return the project
	 */
	public String getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(String project) {
		this.project = project;
	}

	/**
	 * @return the strategicPillarSequenceNumber
	 */
	public String getStrategicPillarSequenceNumber() {
		return strategicPillarSequenceNumber;
	}

	/**
	 * @param strategicPillarSequenceNumber the strategicPillarSequenceNumber to set
	 */
	public void setStrategicPillarSequenceNumber(String strategicPillarSequenceNumber) {
		this.strategicPillarSequenceNumber = strategicPillarSequenceNumber;
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
		return "AddStrategicPillar [strategicPillar=" + strategicPillar + ", project=" + project + ", strategicPillarSequenceNumber="
				+ strategicPillarSequenceNumber + ", theme=" + theme + ", outcomeSequenceNumber=" + outcomeSequenceNumber + ", outcome=" + outcome
				+ ", outputSequenceNumber=" + outputSequenceNumber + ", output=" + output + ", indicator=" + indicator + ", target=" + target + "]";
	}

}
