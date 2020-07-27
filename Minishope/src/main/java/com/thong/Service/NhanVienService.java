package com.thong.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.thong.DTO.NhanVienDTO;
import com.thong.Entity.ChucVu;
import com.thong.Entity.NhanVien;
import com.thong.InterfaceDAO.INhanVienDAO;
import com.thong.InterfaceService.INhanVienService;

@Service
public class NhanVienService implements INhanVienService {
	@Autowired
	private INhanVienDAO nhanVienDAO;
	@Autowired
	private BCryptPasswordEncoder bCrypt;

	public NhanVienDTO checkLogin(String userName, String password) {
		NhanVien nv = nhanVienDAO.checkLogin(userName, bCrypt.encode(password));
		if (nv == null) {
			return null;
		}
		return new NhanVienDTO(nv);
	}

	public int save(NhanVienDTO n ) {
		NhanVien nv= new NhanVien(n);
		if (nv.getChucVu() == null) {
			ChucVu cv = new ChucVu();
			cv.setIdChucVu(3);
			nv.setChucVu(cv);
		}
		nv.setEnabled(false);
		nv.setNonBanned(true);
		nv.setMatKhau(bCrypt.encode(nv.getMatKhau()));
		System.out.println("mat khau " + nv.getMatKhau());
		return nhanVienDAO.save(nv);
	}

	public boolean checkUserName(String userName) {
		return nhanVienDAO.checkUserName(userName);

	}

	public List<NhanVienDTO> searchNhanVien(String keyWords, String sortBy, String typeSort, int begin, int quantity) {
		List<NhanVien> listNV = nhanVienDAO.searchNhanVien(keyWords, sortBy, typeSort, begin, quantity);

		List<NhanVienDTO> listNVDTO = new ArrayList<NhanVienDTO>();
		;
		for (NhanVien nv : listNV) {
			NhanVienDTO nvDTO = new NhanVienDTO(nv);
			listNVDTO.add(nvDTO);
		}
		return listNVDTO;
	}

	public List<NhanVienDTO> findAll(int begin, int quantity, String sortBy, String typeSort) {
		List<NhanVien> list = nhanVienDAO.findAll(begin, quantity, sortBy, typeSort);
		List<NhanVienDTO> listDTO = new ArrayList<NhanVienDTO>();
		for (NhanVien nv : list) {
			NhanVienDTO nvDTO = new NhanVienDTO(nv);
			listDTO.add(nvDTO);
		}

		return listDTO;
	}

	public void delete(List<Integer> idUser) {
		for (Integer i : idUser) {
			NhanVien nv = nhanVienDAO.findOneById(i);
			if (!nv.getChucVu().getTenChucVu().equals("ROLE_admin")) {
				nhanVienDAO.delete(nv);
			}
		}

	}

	public NhanVienDTO findOneById(int idUser) {
		NhanVien nv = nhanVienDAO.findOneById(idUser);
		NhanVienDTO nvDTO = new NhanVienDTO(nv);
		return nvDTO;
	}

	public boolean checkEmail(String email) {
		// TODO Auto-generated method stub
		return nhanVienDAO.checkEmail(email);
	}

	public NhanVien findByUserName(String userName) {
		// TODO Auto-generated method stub
		return nhanVienDAO.findByUserName(userName);
	}

	public void update(NhanVienDTO n) {
		NhanVien nv = new NhanVien(n);
		if (nv.getChucVu().getTenChucVu().equals("ROLE_admin")) {
			nv.setNonBanned(true);
		}
		//nv.setMatKhau(bCrypt.encode(nv.getMatKhau()));
		nhanVienDAO.update(nv);

	}

	public NhanVien findByToken(String token) {
		// TODO Auto-generated method stub
		return nhanVienDAO.findByToken(token);
	}

	public NhanVien findOneById2(int idUser) {
		// TODO Auto-generated method stub
		return nhanVienDAO.findOneById(idUser);
	}

	public NhanVienDTO findByUserNameDTO(String userName) {
		// TODO Auto-generated method stub
		NhanVien nv =nhanVienDAO.findByUserName(userName);
		if(nv==null) {
			return null;
		}
		return new NhanVienDTO(nv);
	}

	public NhanVien findByTokenFB(String tokenFB) {
		// TODO Auto-generated method stub
		NhanVien nv = nhanVienDAO.findByTokenFB(tokenFB);
		if(nv==null) {
			return null;
		}
			return nv;
		
	}

	public boolean saveUserFB(NhanVien nv) {
		// TODO Auto-generated method stub
		nv.setEnabled(true);
		nv.setNonBanned(true);
		ChucVu cv = new ChucVu();
			cv.setIdChucVu(3);
		nv.setChucVu(cv);
		Integer i=nhanVienDAO.saveUserFB(nv);
		if(i!=0) {
			return true;
		}else {
			return false;
		}
	}

	public NhanVienDTO findByTokenDTO(String token) {
		// TODO Auto-generated method stub
		NhanVien nv = nhanVienDAO.findByToken(token);
		if(nv==null) {
			return null;
		}
		return new NhanVienDTO(nv);
	}

	public void update(NhanVien nv) {
		// TODO Auto-generated method stub
		if (nv.getChucVu().getTenChucVu().equals("ROLE_admin")) {
			nv.setEnabled(true);
		}
		//nv.setMatKhau(bCrypt.encode(nv.getMatKhau()));
		nhanVienDAO.update(nv);
	}


}
