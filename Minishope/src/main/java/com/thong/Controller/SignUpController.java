package com.thong.Controller;

import java.util.List;
import java.util.Locale;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.thong.DTO.UserDTO;
import com.thong.InterfaceService.IUserService;
import com.thong.Service.MailSerive;

@Controller
@RequestMapping("SignUp/")
public class SignUpController {
	@Autowired
	private IUserService nhanVienService;
	@Autowired
	private MessageSource mes;
	@Autowired
	private MailSerive mailSerive;

	@GetMapping
	public String Default(HttpServletRequest request, ModelMap modelMap) {
		return "SignUp";
	}

	@PostMapping
	public String SignUpProccess(@ModelAttribute @Valid UserDTO nv, BindingResult bindingResult, ModelMap modelMap) {
		System.out.println(nv.toString());
		if (bindingResult.hasErrors()) {
			modelMap.addAttribute("SignUp", "Thông tin không hợp lệ");
			return "SignUp";
		}
		nv.setEnabled(false);
		nv.setNonBanned(true);
		nhanVienService.save(nv);
		mailSerive.sendMail(nv.getEmail(), "Verify create account", nv.getToken());
		modelMap.addAttribute("message", "Mã kích hoat đã được gửi đến mail của bạn vui lòng kiểm tra mail");

		modelMap.addAttribute("alert", "success");
		return "verify";
	}

	@PostMapping("/confirm-account")
	public String confirm(@RequestParam String token, ModelMap modelMap) {
		UserDTO nv =nhanVienService.findByTokenDTO(token);
		if (nv != null) {
				nv.setEnabled(true);
				nhanVienService.update(nv);
				return "redirect:/login?message=verify_success";
		}else {
				modelMap.addAttribute("message", "sai code vui lòng kiểm tra lại");
				return "verify";
			}


	}
}
