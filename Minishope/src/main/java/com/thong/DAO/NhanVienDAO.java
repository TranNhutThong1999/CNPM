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
import com.thong.Entity.User;
import com.thong.InterfaceDAO.INhanVienDAO;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class NhanVienDAO  implements INhanVienDAO {
	@Autowired
	private SessionFactory sessionFactory;

	
	
	
	public int save(User nv) {
		Session session = sessionFactory.getCurrentSession();
		return (Integer) session.save(nv);
	}

	public void update(User nv) {
		Session session = sessionFactory.getCurrentSession();
		session.update(nv);
	}

	public boolean checkUserName(String userName) {
		System.out.println(userName);
		Session session = sessionFactory.getCurrentSession();
		try {

			User nv = (User) session.createQuery("from user where tenDangNhap = '" + userName + "'")
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
			User nv = (User) session.createQuery("from user where email=?").setParameter(1, email)
					.getSingleResult();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}


	public List<User> searchNhanVien(String keyWords, String sortBy, String typeSort, int begin, int quantity) {
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
			query = session.createQuery("from user where tenDangNhap like '%" + keyWords + "%' or idChucVu = "
					+ value + " or email like '" + keyWords + "' ");
		} else {

			query = session
					.createQuery("from user where tenDangNhap like '%" + keyWords + "%' or idChucVu = " + value
							+ " or email like '%" + keyWords + "%' " + order)
					.setFirstResult(begin).setMaxResults(quantity);
		}
		List<User> list = query.list();
		for (User nhanVien : list) {
			System.out.println("khong null" + nhanVien.toString());
		}
		return list;
	}

	public void delete(User nv) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(nv);
	}

	public User findOneById(int idUser) {
		Session session = sessionFactory.getCurrentSession();
		User nv = session.get(User.class, idUser);
		return nv;
	}

	public User findByUserName(String userName) {
		System.out.println("vao DAO");
		User nv = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			nv = (User) session.createQuery("from user nv where nv.tenDangNhap= '" + userName + "'")
					.getSingleResult();

			return nv;

		} catch (Exception e) {
			System.out.println("null");
			return nv;
		}
	}
	public User findByToken(String token) {
		User nv = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			nv = (User) session.createQuery("from user nv where nv.token = '" + token + "'")
					.getSingleResult();
			return nv;

		} catch (Exception e) {
			System.out.println("null DAO");
			return nv;
		}
	}
	public User findByTokenFB(String tokenFB) {
		User nv = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			nv = (User) session.createQuery("from user nv where nv.tokenFB = '" + tokenFB + "'")
					.getSingleResult();
			return nv;

		} catch (Exception e) {
			System.out.println("null DAO");
			return nv;
		}
	}
	public Integer saveUserFB(User nv) {
		Session session = sessionFactory.getCurrentSession();
		return (Integer) session.save(nv);
	}
}
