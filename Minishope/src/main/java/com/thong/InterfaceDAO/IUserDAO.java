package com.thong.InterfaceDAO;

import java.util.List;

import com.thong.Entity.User;

public interface IUserDAO {

	int save(User nv);

	boolean checkUserName(String userName);
	
	
	User findOneById(int idUser);
	
	boolean checkEmail(String email) ;
	
	User findByUserName(String userName);
	
	 void update(User nv);
	 
	 User findByToken(String token);
	 
	 User findByTokenFB(String tokenFB);
	 
	 Integer saveUserFB(User nv);
}
