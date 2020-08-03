package com.thong.InterfaceService;

import java.util.List;

import com.thong.DTO.UserDTO;
import com.thong.Entity.User;

public interface INhanVienService {
	UserDTO checkLogin(String userName, String password);

	int save(UserDTO nv);

	boolean checkUserName(String userName);

	List<UserDTO> searchNhanVien(String keyWords, String sortBy, String typeSort, int begin, int quantity);

	List<UserDTO> findAll(int begin, int quantity, String sortBy, String typeSort);

	void delete(List<Integer> idUser);

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
