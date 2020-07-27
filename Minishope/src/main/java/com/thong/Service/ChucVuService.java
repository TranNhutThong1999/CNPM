package com.thong.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thong.Entity.ChucVu;
import com.thong.InterfaceDAO.IChucVuDAO;
import com.thong.InterfaceService.IChucVuService;

@Service
public class ChucVuService implements IChucVuService{
	@Autowired
	private IChucVuDAO chucVuDAO;
	public List<ChucVu> findAll() {
		// TODO Auto-generated method stub
		return chucVuDAO.findAll();
	}

}
