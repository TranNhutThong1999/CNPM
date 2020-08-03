package com.thong.InterfaceDAO;

import java.util.List;

import com.thong.Entity.User;

public interface INhanVienDAO {
	User checkLogin(String userName, String password);

	int save(User nv);

	boolean checkUserName(String userName);

	List<User> searchNhanVien(String keyWords,String sortBy,String typeSort,int begin,int quantity);
	
	List<User> findAll(int begin,int quantity,String sortBy,String typeSort);
	
	void delete (User nv);
	
	User findOneById(int idUser);
	
	boolean checkEmail(String email) ;
	
	User findByUserName(String userName);
	
	 void update(User nv);
	 
	 User findByToken(String token);
	 
	 User findByTokenFB(String tokenFB);
	 
	 Integer saveUserFB(User nv);
}
