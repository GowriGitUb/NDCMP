/**
 * 
 */
package com.ceainfotech.ndcmp.util;

import java.io.Serializable;
import java.util.List;

import com.ceainfotech.ndcmp.model.Outcome;
import com.ceainfotech.ndcmp.model.Output;
import com.ceainfotech.ndcmp.model.StrategicPillar;
import com.ceainfotech.ndcmp.model.Theme;

/**
 * @author Samy
 *
 */
public class HierarchyOutcome implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Outcome outcome;
	
	private List<Output> outputs;
	
	/**
	 * 
	 */
	public HierarchyOutcome() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param outcome
	 * @param outputs
	 */
	public HierarchyOutcome(Integer id, Outcome outcome, List<Output> outputs) {
		super();
		this.id = id;
		this.outcome = outcome;
		this.outputs = outputs;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Outcome getOutcome() {
		return outcome;
	}

	public void setOutcome(Outcome outcome) {
		this.outcome = outcome;
	}

	public List<Output> getOutputs() {
		return outputs;
	}

	public void setOutputs(List<Output> outputs) {
		this.outputs = outputs;
	}

	@Override
	public String toString() {
		return "HierarchyOutcome [id=" + id + ", outcome=" + outcome + ", outputs=" + outputs + "]";
	}
	
}
