package com.thong.InterfaceService;

import java.util.List;

import com.thong.DTO.UserDTO;
import com.thong.Entity.User;

public interface INhanVienService {

	int save(UserDTO nv);

	boolean checkUserName(String userName);

	UserDTO findOneById(int idUser);

	User findOneById2(int idUser);

	boolean checkEmail(String email);
	
	UserDTO findByUserNameDTO(String userName);

	User findByUserName(String userName);

	void update(UserDTO nv);
	
	void update(User nv);

	User findByToken(String token);
	
	UserDTO findByTokenDTO(String token);
	
	User findByTokenFB(String tokenFB);
	
	boolean saveUserFB(User nv);
}
