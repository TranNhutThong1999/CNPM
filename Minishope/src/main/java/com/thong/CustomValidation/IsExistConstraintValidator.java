package com.thong.CustomValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.thong.DTO.NhanVienDTO;
import com.thong.InterfaceService.INhanVienService;

public class IsExistConstraintValidator implements ConstraintValidator<IsExist, String> {
	@Autowired
	private INhanVienService nhanVienService;

	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if (value != null) {
			System.out.println(value);
			NhanVienDTO nv = nhanVienService.findByUserNameDTO(value);
			if(nv!=null) {
				return false;
			}else {
				return true;
			}
		}
		return true;
	}

}
