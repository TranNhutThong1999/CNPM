package com.thong.DTO;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.thong.CustomValidation.IsExist;
import com.thong.Entity.ChucVu;
import com.thong.Entity.NhanVien;

public class NhanVienDTO {
	private int idNhanVien;
	private String hoTen;
	private String diaChi;
	private String gioiTinh;

	@NotEmpty(message = "Email không được để trống")
	@Email(message = "Email không hợp lệ")
	private String email;
	private String CMND;
	private String soDT;

	@NotEmpty(message = "Tên đăng nhập không được để trống")
	@Pattern(regexp = "^[a-zA-Z]+[0-9]+", message = "Tên đăng nhập phải bao gồm chữ cái và số")
	@Size(min = 6, max = 30, message = "Tên đăng nhập từ 6 đến 30 kí tự")
	@IsExist
	private String tenDangNhap;
	private ChucVu chucVu;
	private boolean isEnabled;

	@NotEmpty(message = "Mật khẩu không được để trống")
	@Size(min = 5, max = 25, message = "Mật khẩu từ 5 đến 25 kí tự")
	//@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\\\d)[A-Za-z\\\\d]{6,20}$",message = "Mật khẩu phải bao gồm chữ cái và số")
	private String matKhau;
	private Timestamp timeToken;
	private String token;
	private boolean isNonBanned;
	private String ToKenFB;

	public NhanVienDTO() {
		this.token = UUID.randomUUID().toString();
	}

	public NhanVienDTO(NhanVien nv) {
		this.idNhanVien = nv.getIdNhanVien();
		this.hoTen = nv.getHoTen();
		this.diaChi = nv.getDiaChi();
		this.gioiTinh = nv.getGioiTinh();
		this.email = nv.getEmail();
		this.CMND = nv.getCMND();
		this.soDT = nv.getSoDT();
		this.tenDangNhap = nv.getTenDangNhap();
		this.chucVu = nv.getChucVu();
		this.isEnabled = nv.isEnabled();
		this.matKhau = nv.getMatKhau();
		this.ToKenFB=nv.getToKenFB();
		this.isNonBanned=nv.isNonBanned();
	}

	public int getIdNhanVien() {
		return idNhanVien;
	}

	public void setIdNhanVien(int idNhanVien) {
		this.idNhanVien = idNhanVien;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCMND() {
		return CMND;
	}

	public void setCMND(String cMND) {
		CMND = cMND;
	}

	public String getSoDT() {
		return soDT;
	}

	public void setSoDT(String soDT) {
		this.soDT = soDT;
	}

	public String getTenDangNhap() {
		return tenDangNhap;
	}

	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}

	public ChucVu getChucVu() {
		return chucVu;
	}

	public void setChucVu(ChucVu chucVu) {
		this.chucVu = chucVu;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public Timestamp getTimeToken() {
		return timeToken;
	}

	public void setTimeToken(Timestamp timeToken) {
		this.timeToken = timeToken;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isAfterTime() {
		Calendar time = Calendar.getInstance();
		Timestamp timetamp = new Timestamp(time.getTime().getTime());
		System.out.println("time" + timetamp);
		return timetamp.after(this.timeToken);
	}

	public void setTimeTokenFuture(int minutes) {
		Calendar time = Calendar.getInstance();
		time.add(Calendar.MINUTE, minutes);
		System.out.println("time tamp " + new Timestamp(time.getTime().getTime()));
		this.timeToken = new Timestamp(time.getTime().getTime());
	}

	public void setTokenRamdom() {
		this.token = UUID.randomUUID().toString();
	}

	public void setNonBanned(boolean isNonBanned) {
		this.isNonBanned = isNonBanned;
	}



	public boolean isNonBanned() {
		return isNonBanned;
	}

	public String getToKenFB() {
		return ToKenFB;
	}

	public void setToKenFB(String toKenFB) {
		ToKenFB = toKenFB;
	}



	
}
