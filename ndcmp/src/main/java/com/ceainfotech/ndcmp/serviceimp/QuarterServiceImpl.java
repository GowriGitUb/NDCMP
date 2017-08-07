/**
 * 
 */
package com.ceainfotech.ndcmp.serviceimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import com.ceainfotech.ndcmp.dao.QuarterDAO;
import com.ceainfotech.ndcmp.model.Quarter;
import com.ceainfotech.ndcmp.service.QuarterService;

/**
 * @author Gowri
 *
 */
@Service
@Transactional
public class QuarterServiceImpl implements QuarterService {

	@Autowired
	private QuarterDAO quarterDAO; 
	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.QuarterService#getById(java.lang.Integer)
	 */
	@Override
	public Quarter getById(Integer id) {
		if(id != null){
			return quarterDAO.getById(id);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.QuarterService#addQuarter(com.ceainfotech.ndcmp.model.Quarter)
	 */
	@Override
	public void addQuarter(Quarter quarter) {
		quarterDAO.addQuarter(quarter);
	}

	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.QuarterService#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		if(id != null){
			quarterDAO.delete(id);
		}
	}

	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.QuarterService#listAllQuarters()
	 */
	@Override
	public List<Quarter> listAllQuarters() {
		return quarterDAO.listAllQuarters();
	}

	@Override
	public Quarter findByNameAndStatus(String name, String status) {
		if(name != null && status != null){
			return quarterDAO.findByNameAndStatus(name, status);
		}
		return null;
	}

	@Override
	public Quarter findByIdAndNameAndStatus(Integer id, String name,String status) {
		if(id != null && name != null && status != null){
			return quarterDAO.findByIdAndNameAndStatus(id, name, status);
		}
		return null;
	}
}
