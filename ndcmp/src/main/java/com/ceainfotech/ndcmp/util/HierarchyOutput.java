/**
 * 
 */
package com.ceainfotech.ndcmp.util;

import java.io.Serializable;
import java.util.List;

import com.ceainfotech.ndcmp.model.Indicator;
import com.ceainfotech.ndcmp.model.KeyActivity;
import com.ceainfotech.ndcmp.model.Outcome;
import com.ceainfotech.ndcmp.model.Output;
import com.ceainfotech.ndcmp.model.SubActivity;
import com.ceainfotech.ndcmp.model.Target;

/**
 * @author Samy
 *
 */
public class HierarchyOutput implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Output output;
	
	private List<KeyActivity> keyActivities;
	
	private List<SubActivity> subActivities;
	
	private List<Indicator> indicators;
	
	private List<Target> targets;
	
	/**
	 * 
	 */
	public HierarchyOutput() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param output
	 * @param keyActivities
	 * @param subActivities
	 * @param indicators
	 * @param targets
	 */
	public HierarchyOutput(Integer id, Output output, List<KeyActivity> keyActivities, List<SubActivity> subActivities, List<Indicator> indicators,
			List<Target> targets) {
		super();
		this.id = id;
		this.output = output;
		this.keyActivities = keyActivities;
		this.subActivities = subActivities;
		this.indicators = indicators;
		this.targets = targets;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Output getOutput() {
		return output;
	}

	public void setOutput(Output output) {
		this.output = output;
	}

	public List<KeyActivity> getKeyActivities() {
		return keyActivities;
	}

	public void setKeyActivities(List<KeyActivity> keyActivities) {
		this.keyActivities = keyActivities;
	}

	public List<SubActivity> getSubActivities() {
		return subActivities;
	}

	public void setSubActivities(List<SubActivity> subActivities) {
		this.subActivities = subActivities;
	}

	public List<Indicator> getIndicators() {
		return indicators;
	}

	public void setIndicators(List<Indicator> indicators) {
		this.indicators = indicators;
	}

	public List<Target> getTargets() {
		return targets;
	}

	public void setTargets(List<Target> targets) {
		this.targets = targets;
	}

	@Override
	public String toString() {
		return "HierarchyOutput [id=" + id + ", output=" + output + ", keyActivities=" + keyActivities + ", subActivities=" + subActivities
				+ ", indicators=" + indicators + ", targets=" + targets + "]";
	}

}
