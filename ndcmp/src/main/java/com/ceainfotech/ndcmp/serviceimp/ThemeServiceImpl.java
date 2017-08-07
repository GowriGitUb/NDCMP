/**
 * 
 */
package com.ceainfotech.ndcmp.serviceimp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceainfotech.ndcmp.dao.ThemeDAO;
import com.ceainfotech.ndcmp.model.StrategicPillar;
import com.ceainfotech.ndcmp.model.Theme;
import com.ceainfotech.ndcmp.service.ThemeService;

/**
 * @author bosco
 *
 */

@Service("themeService")
public class ThemeServiceImpl implements ThemeService{

	@Autowired
	ThemeDAO themeDAO;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ThemeServiceImpl.class);
	
	@Override
	public List<Theme> getAll() {
		return themeDAO.getAll();
	}
	
	@Override
	public List<Theme> getTheme(String name) {
		return themeDAO.getTheme(name);
	}

	@Override
	public Theme getById(Integer id) {
		return themeDAO.getById(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.ThemeService#getByName(java.lang.String)
	 */
	@Override
	public Theme getByName(String name) {
		return themeDAO.getByName(name);
	}

	@Override
	public void deleteByTheme(Theme theme) {
		themeDAO.deleteByTheme(theme);
	}

	@Override
	public void save(Theme theme) {
		themeDAO.save(theme);
	}

	@Override
	public void saveAndFlush(Theme theme) {
		themeDAO.saveAndFlush(theme);
	}

	@Override
	public List<Theme> getThemeByStrategicPillar(StrategicPillar strategicPillar) {
		return themeDAO.getThemeByStrategicPillar(strategicPillar);
	}
	
	@Override
	public List<Theme> findBySyncStatus(boolean syncStatus) {
		return themeDAO.findBySyncStatus(syncStatus);
	}

	@Override
	public Theme getByThemeAndStrategicPillar(String name,
			StrategicPillar strategicPillar) {
		// TODO Auto-generated method stub
		return themeDAO.getByThemeAndStrategicPillar(name, strategicPillar);
	}
	
}
