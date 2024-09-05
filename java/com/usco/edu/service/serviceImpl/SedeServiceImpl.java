package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.ISedeDao;
import com.usco.edu.entities.Sede;
import com.usco.edu.service.ISedeService;

@Service
public class SedeServiceImpl implements ISedeService{
	
	@Autowired
	private ISedeDao sedeDao;

	@Override
	public List<Sede> findAll() {
		return sedeDao.sedesAll();
	}

}
