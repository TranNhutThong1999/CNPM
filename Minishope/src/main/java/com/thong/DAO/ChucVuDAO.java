package com.thong.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thong.Entity.ChucVu;
import com.thong.InterfaceDAO.IChucVuDAO;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ChucVuDAO implements IChucVuDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public List<ChucVu> findAll(){
		Session session = sessionFactory.getCurrentSession();
		List<ChucVu> cv = session.createQuery("from chucvu").getResultList();
		
		return cv;	
	}
}
