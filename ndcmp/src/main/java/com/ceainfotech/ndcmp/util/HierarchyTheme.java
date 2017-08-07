/**
 * 
 */
package com.ceainfotech.ndcmp.util;

import java.io.Serializable;
import java.util.List;

import com.ceainfotech.ndcmp.model.Outcome;
import com.ceainfotech.ndcmp.model.StrategicPillar;
import com.ceainfotech.ndcmp.model.Theme;

/**
 * @author Samy
 *
 */
public class HierarchyTheme implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Theme theme;
	
	private List<Outcome> outcomes;
	
	/**
	 * 
	 */
	public HierarchyTheme() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param theme
	 * @param outcomes
	 */
	public HierarchyTheme(Integer id, Theme theme, List<Outcome> outcomes) {
		super();
		this.id = id;
		this.theme = theme;
		this.outcomes = outcomes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	public List<Outcome> getOutcomes() {
		return outcomes;
	}

	public void setOutcomes(List<Outcome> outcomes) {
		this.outcomes = outcomes;
	}

	@Override
	public String toString() {
		return "HierarchyTheme [id=" + id + ", theme=" + theme + ", outcomes=" + outcomes + "]";
	}

}
