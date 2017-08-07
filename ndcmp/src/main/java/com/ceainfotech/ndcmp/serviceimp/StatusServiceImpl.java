/**
 * 
 */
package com.ceainfotech.ndcmp.serviceimp;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceainfotech.ndcmp.dao.StatusDAO;
import com.ceainfotech.ndcmp.model.Statuss;
import com.ceainfotech.ndcmp.service.StatusService;

/**
 * @author Samy
 * 
 */
@Service("statusService")
@Transactional
public class StatusServiceImpl implements StatusService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StatusServiceImpl.class);

	@Autowired
	StatusDAO statusDAO;

	@Override
	public Statuss findStatusByName(String name) {
		return statusDAO.findStatusByName(name);
	}

	@Override
	public void addStatus(Statuss status) {
		statusDAO.addStatus(status);
	}

	@Override
	public Statuss findByIdAndNameAndStatusColor(Integer id, String name ,  String description ,String statusColor) {
		return statusDAO.findByIdAndName(id, name, description,statusColor);
	}

	@Override
	public void updateStatus(Statuss status) {
		statusDAO.updateStatus(status);
	}

	@Override
	public List<Statuss> listAllStatus() {
		return statusDAO.listAllStatus();
	}

	@Override
	public Statuss getStatusById(Integer id) {
		return statusDAO.getStatusById(id);
	}

	@Override
	public void deleteStatusById(Integer id) {
		statusDAO.deleteStatusById(id);
	}

	@Override
	public Statuss findStatusByDescription(String description) {
		return statusDAO.findStatusByDescription(description);
	}

	@Override
	public Statuss findStatusByColor(String statusColor) {
		return statusDAO.findStatusByColor(statusColor);
	}

	@Override
	public Statuss getByIdAndName(Integer id, String name) {
		return statusDAO.getByIdAndName(id,name);
	}

	@Override
	public Statuss statusIdAndDescription(Integer id, String description) {
		return statusDAO.statusIdAndDescription(id , description);
	}

	@Override
	public Statuss statusIdAndStatusColor(Integer id, String statusColor) {
		return statusDAO.statusIdAndStatusColor(id, statusColor);
	}

	@Override
	public Integer getStatussCount() {
		return statusDAO.getStatussCount();
	}
	@Override
	public List<Statuss> findStatusListByDate(String lastSyncedTime,String currentTime) {
		return statusDAO.findStatusListByDate(lastSyncedTime, currentTime);
	}
	
	@Override
	public List<Statuss> findAllByOrderByStartRangeAsc() {
		return statusDAO.findAllByOrderByStartRangeAsc();
	}

}
