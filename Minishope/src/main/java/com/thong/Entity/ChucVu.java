package com.thong.Entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="chucvu")
public class ChucVu  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idChucVu;
	private String tenChucVu;

	
	public ChucVu() {
		super();
	}

	public int getIdChucVu() {
		return idChucVu;
	}

	public void setIdChucVu(int idChucVu) {
		this.idChucVu = idChucVu;
	}

	public String getTenChucVu() {
		return tenChucVu;
	}

	public void setTenChucVu(String tenChucVu) {
		this.tenChucVu = tenChucVu;
	}

	@Override
	public String toString() {
		return "ChucVu [idChucVu=" + idChucVu + ", tenChucVu=" + tenChucVu + "]";
	}

	

}
