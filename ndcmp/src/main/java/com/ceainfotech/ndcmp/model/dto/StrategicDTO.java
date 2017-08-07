/**
 * 
 */
package com.ceainfotech.ndcmp.model.dto;

import javax.persistence.Transient;

import com.ceainfotech.ndcmp.model.Indicator;
import com.ceainfotech.ndcmp.model.Outcome;
import com.ceainfotech.ndcmp.model.Output;
import com.ceainfotech.ndcmp.model.Project;
import com.ceainfotech.ndcmp.model.StrategicPillar;
import com.ceainfotech.ndcmp.model.Target;
import com.ceainfotech.ndcmp.model.Theme;

/**
 * @author bosco
 *
 */
public class StrategicDTO {
	
	StrategicPillar strategicPillar = new StrategicPillar();
	Theme theme = new Theme();
	Outcome outcome = new Outcome();
	Output output = new Output();
	Indicator indicator = new Indicator();
	Target target = new Target();
	
	@Transient
	private String strategicPillarNameError;
	@Transient
	private String pillarSNError;
	
	@Transient
	private String themeNameError;
	
	@Transient
	private String seqNumOfOutcomeError;
	@Transient
	private String seqNumOfOutputError;
	
	@Transient
	private String outcomeNameError;
	
	@Transient
	private String outputNameError;
	
	
	/**
	 * @return the outputNameError
	 */
	public String getOutputNameError() {
		return outputNameError;
	}
	/**
	 * @param outputNameError the outputNameError to set
	 */
	public void setOutputNameError(String outputNameError) {
		this.outputNameError = outputNameError;
	}
	/**
	 * @return the outcomeNameError
	 */
	public String getOutcomeNameError() {
		return outcomeNameError;
	}
	/**
	 * @param outcomeNameError the outcomeNameError to set
	 */
	public void setOutcomeNameError(String outcomeNameError) {
		this.outcomeNameError = outcomeNameError;
	}
	/**
	 * @return the themeNameError
	 */
	public String getThemeNameError() {
		return themeNameError;
	}
	/**
	 * @param themeNameError the themeNameError to set
	 */
	public void setThemeNameError(String themeNameError) {
		this.themeNameError = themeNameError;
	}
	/**
	 * @return the seqNumOfOutcomeError
	 */
	public String getSeqNumOfOutcomeError() {
		return seqNumOfOutcomeError;
	}
	/**
	 * @param seqNumOfOutcomeError the seqNumOfOutcomeError to set
	 */
	public void setSeqNumOfOutcomeError(String seqNumOfOutcomeError) {
		this.seqNumOfOutcomeError = seqNumOfOutcomeError;
	}
	/**
	 * @return the seqNumOfOutputError
	 */
	public String getSeqNumOfOutputError() {
		return seqNumOfOutputError;
	}
	/**
	 * @param seqNumOfOutputError the seqNumOfOutputError to set
	 */
	public void setSeqNumOfOutputError(String seqNumOfOutputError) {
		this.seqNumOfOutputError = seqNumOfOutputError;
	}
	/**
	 * @return the strategicPillarNameError
	 */
	public String getStrategicPillarNameError() {
		return strategicPillarNameError;
	}
	/**
	 * @param strategicPillarNameError the strategicPillarNameError to set
	 */
	public void setStrategicPillarNameError(String strategicPillarNameError) {
		this.strategicPillarNameError = strategicPillarNameError;
	}
	/**
	 * @return the pillarSNError
	 */
	public String getPillarSNError() {
		return pillarSNError;
	}
	/**
	 * @param pillarSNError the pillarSNError to set
	 */
	public void setPillarSNError(String pillarSNError) {
		this.pillarSNError = pillarSNError;
	}
	/**
	 * @return the strategicPillar
	 */
	public StrategicPillar getStrategicPillar() {
		return strategicPillar;
	}
	/**
	 * @param strategicPillar the strategicPillar to set
	 */
	public void setStrategicPillar(StrategicPillar strategicPillar) {
		this.strategicPillar = strategicPillar;
	}
	/**
	 * @return the theme
	 */
	public Theme getTheme() {
		return theme;
	}
	/**
	 * @param theme the theme to set
	 */
	public void setTheme(Theme theme) {
		this.theme = theme;
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
	 * @return the indicator
	 */
	public Indicator getIndicator() {
		return indicator;
	}
	/**
	 * @param indicator the indicator to set
	 */
	public void setIndicator(Indicator indicator) {
		this.indicator = indicator;
	}
	/**
	 * @return the target
	 */
	public Target getTarget() {
		return target;
	}
	/**
	 * @param target the target to set
	 */
	public void setTarget(Target target) {
		this.target = target;
	}

	
	
	
}
