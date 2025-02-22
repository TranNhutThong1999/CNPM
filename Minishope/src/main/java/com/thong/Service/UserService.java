package com.thong.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.thong.DTO.UserDTO;
import com.thong.Entity.ChucVu;
import com.thong.Entity.User;
import com.thong.InterfaceDAO.IUserDAO;
import com.thong.InterfaceService.IUserService;

@Service
public class UserService implements IUserService {
	@Autowired
	private IUserDAO UserDAO;
	@Autowired
	private BCryptPasswordEncoder bCrypt;


	public int save(UserDTO n ) {
		User nv= new User(n);
		if (nv.getChucVu() == null) {
			ChucVu cv = new ChucVu();
			cv.setIdChucVu(3);
			nv.setChucVu(cv);
		}
		nv.setEnabled(false);
		nv.setNonBanned(true);
		nv.setMatKhau(bCrypt.encode(nv.getMatKhau()));
		System.out.println("mat khau " + nv.getMatKhau());
		return UserDAO.save(nv);
	}

	public boolean checkUserName(String userName) {
		return UserDAO.checkUserName(userName);

	}

	public UserDTO findOneById(int idUser) {
		User nv = UserDAO.findOneById(idUser);
		UserDTO nvDTO = new UserDTO(nv);
		return nvDTO;
	}

	public boolean checkEmail(String email) {
		// TODO Auto-generated method stub
		return UserDAO.checkEmail(email);
	}

	public User findByUserName(String userName) {
		// TODO Auto-generated method stub
		return UserDAO.findByUserName(userName);
	}

	public void update(UserDTO n) {
		User nv = new User(n);
		if (nv.getChucVu().getTenChucVu().equals("ROLE_admin")) {
			nv.setNonBanned(true);
		}
		//nv.setMatKhau(bCrypt.encode(nv.getMatKhau()));
		UserDAO.update(nv);

	}

	public User findByToken(String token) {
		// TODO Auto-generated method stub
		return UserDAO.findByToken(token);
	}

	public User findOneById2(int idUser) {
		// TODO Auto-generated method stub
		return UserDAO.findOneById(idUser);
	}

	public UserDTO findByUserNameDTO(String userName) {
		// TODO Auto-generated method stub
		User nv =UserDAO.findByUserName(userName);
		if(nv==null) {
			return null;
		}
		return new UserDTO(nv);
	}

	public User findByTokenFB(String tokenFB) {
		// TODO Auto-generated method stub
		User nv = UserDAO.findByTokenFB(tokenFB);
		if(nv==null) {
			return null;
		}
			return nv;
		
	}

	public boolean saveUserFB(User nv) {
		// TODO Auto-generated method stub
		nv.setEnabled(true);
		nv.setNonBanned(true);
		ChucVu cv = new ChucVu();
			cv.setIdChucVu(3);
		nv.setChucVu(cv);
		Integer i=UserDAO.saveUserFB(nv);
		if(i!=0) {
			return true;
		}else {
			return false;
		}
	}

	public UserDTO findByTokenDTO(String token) {
		// TODO Auto-generated method stub
		User nv = UserDAO.findByToken(token);
		if(nv==null) {
			return null;
		}
		return new UserDTO(nv);
	}

	public void update(User nv) {
		// TODO Auto-generated method stub
		if (nv.getChucVu().getTenChucVu().equals("ROLE_admin")) {
			nv.setEnabled(true);
		}
		//nv.setMatKhau(bCrypt.encode(nv.getMatKhau()));
		UserDAO.update(nv);
	}


}
