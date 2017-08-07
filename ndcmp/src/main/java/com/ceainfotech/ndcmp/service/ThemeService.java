/**
 * 
 */
package com.ceainfotech.ndcmp.service;

import java.util.List;

import com.ceainfotech.ndcmp.model.StrategicPillar;
import com.ceainfotech.ndcmp.model.Theme;

/**
 * @author bosco
 * 
 */
public interface ThemeService {
	
	public List<Theme> getAll();

	public List<Theme> getTheme(String name);

	public Theme getById(Integer id);
	
	public Theme getByName(String name);

	public void deleteByTheme(Theme theme);

	public void save(Theme theme);

	public void saveAndFlush(Theme theme);
	
	public List<Theme> getThemeByStrategicPillar(StrategicPillar strategicPillar);
	
	public List<Theme> findBySyncStatus(boolean syncStatus);

	public Theme getByThemeAndStrategicPillar(String name,StrategicPillar strategicPillar);
}
