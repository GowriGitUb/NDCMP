/**
 * 
 */
package com.ceainfotech.ndcmp.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ceainfotech.ndcmp.model.StrategicPillar;
import com.ceainfotech.ndcmp.model.Theme;
import com.ceainfotech.ndcmp.repository.ThemeRepository;
import com.ceainfotech.ndcmp.repository.UserRepository;

/**
 * @author bosco
 *
 */
@Repository
public class ThemeDAO  {
	
	private final ThemeRepository themeRepository;

	public ThemeDAO(final ThemeRepository themeRepository){
		this.themeRepository = themeRepository;
	}
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDAO.class);
	
	@Transactional(readOnly = true)
	public List<Theme> getAll() {
		return themeRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Theme> getTheme(String status) {
		return themeRepository.findByStatus(status);
	}
	
	@Transactional(readOnly = false)
	public void deleteByTheme(Theme theme) {
		themeRepository.delete(theme);
	}
	
	@Transactional(readOnly = true)
	public Theme getById(Integer id) {
		return themeRepository.getById(id);
	}
	
	@Transactional(readOnly = true)
	public Theme getByName(String name) {
		return themeRepository.getByName(name);
	}

	public void save(Theme theme) {
		themeRepository.save(theme);
	}

	public void saveAndFlush(Theme theme) {
		themeRepository.saveAndFlush(theme);
	}

	public List<Theme> getThemeByStrategicPillar(StrategicPillar strategicPillar) {
		return themeRepository.findByStrategicPillar(strategicPillar);
	}
	
	@Transactional(readOnly = true)
	public List<Theme> findBySyncStatus(boolean syncStatus){
		return themeRepository.findBySyncStatus(syncStatus);
	}
	
	
	@Transactional(readOnly=true)
	public Theme getByThemeAndStrategicPillar(String name,
			StrategicPillar strategicPillar) {
		return themeRepository.findByNameAndStrategicPillar(name, strategicPillar);
	}

}
