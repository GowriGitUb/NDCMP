package com.ceainfotech.ndcmp.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ceainfotech.ndcmp.model.Statuss;
import com.ceainfotech.ndcmp.repository.StatusRepository;

@Repository
public class StatusDAO {

	private final StatusRepository statusRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(CountryDAO.class);

	@Autowired
	public StatusDAO(final StatusRepository statusRepository) {
		this.statusRepository = statusRepository;
	}

	public Statuss findStatusByName(String name) {
		LOGGER.debug("Find the status by name .. {}", name);
		return statusRepository.findByName(name);
	}
	
	public Statuss findByIdAndName(Integer id, String name, String description, String statusColor) {
		LOGGER.debug("Find the status by name and id.. {}", name, id ,description);
		return statusRepository.findByIdAndNameAndDescriptionAndStatusColor(id, name,description, statusColor);
	}

	public void addStatus(Statuss status) {
		LOGGER.debug("Add new status .. {}", status);
		statusRepository.save(status);
	}

	public void updateStatus(Statuss status) {
		LOGGER.debug("update existing status .. {}", status);
		statusRepository.saveAndFlush(status);
	}

	public List<Statuss> listAllStatus() {
		LOGGER.debug("Find all the status .. {}");
		return statusRepository.findAllByOrderByNameAsc();
	}

	public Statuss getStatusById(Integer id) {
		LOGGER.debug("Find the status by id.. {}");
		return statusRepository.getById(id);
	}

	public void deleteStatusById(Integer id) {
		LOGGER.debug("Delete the status by id.. {}");
		statusRepository.delete(id);
	}

	public Statuss findStatusByDescription(String description) {
		LOGGER.debug("Find Status By Description...{}");
		return statusRepository.findByDescription(description);
	}

	public Statuss findStatusByColor(String statusColor) {
		LOGGER.debug("Find Status By Color");
		return statusRepository.findByStatusColor(statusColor);
	}

	public Statuss getByIdAndName(Integer id, String name) {
		LOGGER.debug("Find Status by Id And Name");
		return statusRepository.findByIdAndName(id,name);
	}

	public Statuss statusIdAndDescription(Integer id, String description) {
		return statusRepository.findByIdAndDescription(id,description);
	}

	public Statuss statusIdAndStatusColor(Integer id, String statusColor) {
		return statusRepository.findByIdAndStatusColor(id,statusColor);
	}
	public List<Statuss> findStatusListByDate(String lastSyncedTime, String currentTime) {

		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date lastSyncedDate = null;
		Date currentDate = null;
		try {
			lastSyncedDate = sd.parse(lastSyncedTime);
			currentDate = sd.parse(currentTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return statusRepository.findStatusListByDate(lastSyncedDate,currentDate);
	}
	
	public Integer getStatussCount(){
		return statusRepository.getStatussCount();
	}
	
	public List<Statuss> findAllByOrderByStartRangeAsc() {
		return statusRepository.findAllByOrderByStartRangeAsc();
	}

}
