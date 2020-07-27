package com.thong.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thong.Entity.ChucVu;
import com.thong.Entity.NhanVien;
import com.thong.InterfaceDAO.INhanVienDAO;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class NhanVienDAO  implements INhanVienDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public List<NhanVien> findAll(int begin, int quantity, String sortBy, String typeSort) {
		Session session = sessionFactory.getCurrentSession();
		Query query = null;
		String order = "order by " + sortBy + " " + typeSort;
		if (typeSort == null || typeSort.isEmpty()) {
			order = "";
		}
		if (begin == -1) {
			query = session.createQuery("from nhanvien  " + order);
		} else {
			query = session.createQuery("from nhanvien " + order).setFirstResult(begin).setMaxResults(quantity);
		}
		List<NhanVien> list = query.list();
		return list;
	}

	public NhanVien checkLogin(String userName, String password) {
		try {
			Session session = sessionFactory.getCurrentSession();
			NhanVien nhanVien = (NhanVien) session
					.createQuery("from nhanvien where tenDangNhap='" + userName + "' and matKhau='" + password + "'")
					.getSingleResult();
			if (nhanVien != null) {
				return nhanVien;
			}
		} catch (Exception e) {
			return null;
		}
		return null;

	}
	
	public int save(NhanVien nv) {
		Session session = sessionFactory.getCurrentSession();
		return (Integer) session.save(nv);
	}

	public void update(NhanVien nv) {
		Session session = sessionFactory.getCurrentSession();
		session.update(nv);
	}

	public boolean checkUserName(String userName) {
		System.out.println(userName);
		Session session = sessionFactory.getCurrentSession();
		try {

			NhanVien nv = (NhanVien) session.createQuery("from nhanvien where tenDangNhap = '" + userName + "'")
					.getSingleResult();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public boolean checkEmail(String email) {
		try {
			Session session = sessionFactory.getCurrentSession();
			NhanVien nv = (NhanVien) session.createQuery("from nhanvien where email=?").setParameter(1, email)
					.getSingleResult();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}


	public List<NhanVien> searchNhanVien(String keyWords, String sortBy, String typeSort, int begin, int quantity) {
		Session session = sessionFactory.getCurrentSession();
		Query query = null;
		String order = "order by " + sortBy + " " + typeSort;
		if (typeSort == null || typeSort.isEmpty()) {
			order = "";
		}
		int value = 0;
		try {
			ChucVu cv = (ChucVu) session.createQuery("from chucvu where tenChucVu like '%" + keyWords + "%'")
					.getSingleResult();
			value = cv.getIdChucVu();
		} catch (Exception e) {

		}

		if (begin == -1) {
			query = session.createQuery("from nhanvien where tenDangNhap like '%" + keyWords + "%' or idChucVu = "
					+ value + " or email like '" + keyWords + "' ");
		} else {

			query = session
					.createQuery("from nhanvien where tenDangNhap like '%" + keyWords + "%' or idChucVu = " + value
							+ " or email like '%" + keyWords + "%' " + order)
					.setFirstResult(begin).setMaxResults(quantity);
		}
		List<NhanVien> list = query.list();
		for (NhanVien nhanVien : list) {
			System.out.println("khong null" + nhanVien.toString());
		}
		return list;
	}

	public void delete(NhanVien nv) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(nv);
	}

	public NhanVien findOneById(int idUser) {
		Session session = sessionFactory.getCurrentSession();
		NhanVien nv = session.get(NhanVien.class, idUser);
		return nv;
	}

	public NhanVien findByUserName(String userName) {
		System.out.println("vao DAO");
		NhanVien nv = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			nv = (NhanVien) session.createQuery("from nhanvien nv where nv.tenDangNhap= '" + userName + "'")
					.getSingleResult();

			return nv;

		} catch (Exception e) {
			System.out.println("null");
			return nv;
		}
	}
	public NhanVien findByToken(String token) {
		NhanVien nv = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			nv = (NhanVien) session.createQuery("from nhanvien nv where nv.token = '" + token + "'")
					.getSingleResult();
			return nv;

		} catch (Exception e) {
			System.out.println("null DAO");
			return nv;
		}
	}
	public NhanVien findByTokenFB(String tokenFB) {
		NhanVien nv = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			nv = (NhanVien) session.createQuery("from nhanvien nv where nv.tokenFB = '" + tokenFB + "'")
					.getSingleResult();
			return nv;

		} catch (Exception e) {
			System.out.println("null DAO");
			return nv;
		}
	}
	public Integer saveUserFB(NhanVien nv) {
		Session session = sessionFactory.getCurrentSession();
		return (Integer) session.save(nv);
	}
}
