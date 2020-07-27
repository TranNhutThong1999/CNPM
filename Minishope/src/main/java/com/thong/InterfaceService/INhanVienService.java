package com.thong.InterfaceService;

import java.util.List;

import com.thong.DTO.NhanVienDTO;
import com.thong.Entity.NhanVien;

public interface INhanVienService {
	NhanVienDTO checkLogin(String userName, String password);

	int save(NhanVienDTO nv);

	boolean checkUserName(String userName);

	List<NhanVienDTO> searchNhanVien(String keyWords, String sortBy, String typeSort, int begin, int quantity);

	List<NhanVienDTO> findAll(int begin, int quantity, String sortBy, String typeSort);

	void delete(List<Integer> idUser);

	NhanVienDTO findOneById(int idUser);

	NhanVien findOneById2(int idUser);

	boolean checkEmail(String email);
	
	NhanVienDTO findByUserNameDTO(String userName);

	NhanVien findByUserName(String userName);

	void update(NhanVienDTO nv);
	
	void update(NhanVien nv);

	NhanVien findByToken(String token);
	
	NhanVienDTO findByTokenDTO(String token);
	
	NhanVien findByTokenFB(String tokenFB);
	
	boolean saveUserFB(NhanVien nv);
}
