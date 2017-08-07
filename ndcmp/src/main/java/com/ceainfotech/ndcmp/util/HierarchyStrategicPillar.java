/**
 * 
 */
package com.ceainfotech.ndcmp.util;

import java.util.List;

import com.ceainfotech.ndcmp.model.StrategicPillar;
import com.ceainfotech.ndcmp.model.Theme;

/**
 * @author Samy
 *
 */
public class HierarchyStrategicPillar{

	
	private Integer id;
	
	private StrategicPillar strategicPillar;
	
	private List<Theme> themes;
	
	private HierarchyTheme hierarchyTheme;
	
	/**
	 * 
	 */
	public HierarchyStrategicPillar() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param strategicPillar
	 * @param themes
	 */
	public HierarchyStrategicPillar(Integer id, StrategicPillar strategicPillar, List<Theme> themes, HierarchyTheme hierarchyTheme) {
		super();
		this.id = id;
		this.strategicPillar = strategicPillar;
		this.themes = themes;
		this.hierarchyTheme = hierarchyTheme;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StrategicPillar getStrategicPillar() {
		return strategicPillar;
	}

	public void setStrategicPillar(StrategicPillar strategicPillar) {
		this.strategicPillar = strategicPillar;
	}

	public List<Theme> getThemes() {
		return themes;
	}

	public void setThemes(List<Theme> themes) {
		this.themes = themes;
	}

	public HierarchyTheme getHierarchyTheme() {
		return hierarchyTheme;
	}

	public void setHierarchyTheme(HierarchyTheme hierarchyTheme) {
		this.hierarchyTheme = hierarchyTheme;
	}

	@Override
	public String toString() {
		return "HierarchyStrategicPillar [id=" + id + ", strategicPillar=" + strategicPillar + ", themes=" + themes + ", hierarchyTheme="
				+ hierarchyTheme + "]";
	}

}
