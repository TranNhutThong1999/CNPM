package com.thong.CustomValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.thong.DTO.UserDTO;
import com.thong.InterfaceService.IUserService;

public class IsExistConstraintValidator implements ConstraintValidator<IsExist, String> {
	@Autowired
	private IUserService nhanVienService;

	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if (value != null) {
			System.out.println(value);
			UserDTO nv = nhanVienService.findByUserNameDTO(value);
			if(nv!=null) {
				return false;
			}else {
				return true;
			}
		}
		return true;
	}

}
