package com.thong.Entity;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.thong.CustomValidation.IsExist;
import com.thong.DTO.NhanVienDTO;

@Entity(name = "nhanvien")
public class NhanVien {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idNhanVien;
	private String hoTen;
	private String diaChi;
	private String gioiTinh;
	private String email;
	private String CMND;
	@Column(nullable = false, columnDefinition = "BIT", length = 1)
	private boolean isEnabled;
	private String token;
	private Timestamp timeToken;
	private String soDT;
	private String tenDangNhap;
	private String matKhau;
	private boolean isNonBanned;
	private String ToKenFB;

	@OneToOne
	@JoinColumn(name = "idChucVu")
	private ChucVu chucVu;

	public String getGioiTinh() {
		return gioiTinh;
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

	public String isGioiTinh() {
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

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setTokenRamdom() {
		this.token = UUID.randomUUID().toString();
	}

	public void setTimeTokenFuture(int minutes) {
		Calendar time = Calendar.getInstance();
		time.add(Calendar.MINUTE, minutes);
		System.out.println("time tamp " + new Timestamp(time.getTime().getTime()));
		this.timeToken = new Timestamp(time.getTime().getTime());
	}

	public Date getTimeToken() {
		return timeToken;
	}

	public void setTimeToken(Timestamp timeToken) {
		this.timeToken = timeToken;
	}

	public boolean isAfterTime() {
		Calendar time = Calendar.getInstance();
		Timestamp timetamp = new Timestamp(time.getTime().getTime());
		System.out.println("time" + timetamp);
		return timetamp.after(this.timeToken);
	}

	public NhanVien() {
		super();
		// this.isEnabled=false;
		this.token = UUID.randomUUID().toString();
	}

	public NhanVien(NhanVien nv) {
		this.chucVu = nv.getChucVu();
		this.CMND = nv.getCMND();
		this.diaChi = nv.getDiaChi();
		this.email = nv.getEmail();
		this.gioiTinh = nv.getGioiTinh();
		this.hoTen = nv.getHoTen();
		this.idNhanVien = nv.getIdNhanVien();
		this.isEnabled = nv.isEnabled();
		this.matKhau = nv.getMatKhau();
		this.soDT = nv.getSoDT();
		this.tenDangNhap = nv.getTenDangNhap();
		this.timeToken = (Timestamp) nv.getTimeToken();
		this.token = nv.getToken();
		this.ToKenFB=nv.getToKenFB();
		this.isNonBanned=nv.isNonBanned();

	}
	public NhanVien (NhanVienDTO nv) {
		this.chucVu = nv.getChucVu();
		this.CMND = nv.getCMND();
		this.diaChi = nv.getDiaChi();
		this.email = nv.getEmail();
		this.gioiTinh = nv.getGioiTinh();
		this.hoTen = nv.getHoTen();
		this.idNhanVien = nv.getIdNhanVien();
		this.isEnabled = nv.isEnabled();
		this.matKhau = nv.getMatKhau();
		this.soDT = nv.getSoDT();
		this.tenDangNhap = nv.getTenDangNhap();
		this.timeToken = (Timestamp) nv.getTimeToken();
		this.token = nv.getToken();
		this.ToKenFB=nv.getToKenFB();
		this.isNonBanned=nv.isNonBanned();
	}


	public boolean isNonBanned() {
		return isNonBanned;
	}

	public void setNonBanned(boolean isNonBanned) {
		this.isNonBanned = isNonBanned;
	}

	
	public String getToKenFB() {
		return ToKenFB;
	}

	public void setToKenFB(String toKenFB) {
		ToKenFB = toKenFB;
	}

	@Override
	public String toString() {
		return "NhanVien [idNhanVien=" + idNhanVien + ", hoTen=" + hoTen + ", diaChi=" + diaChi + ", gioiTinh="
				+ gioiTinh + ", email=" + email + ", CMND=" + CMND + ", isEnabled=" + isEnabled + ", token=" + token
				+ ", timeToken=" + timeToken + ", soDT=" + soDT + ", tenDangNhap=" + tenDangNhap + ", matKhau="
				+ matKhau + ", chucVu=" + chucVu + "]";
	}

}
