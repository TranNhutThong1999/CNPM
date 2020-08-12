package com.thong.Controller;


import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.thong.Util.MessagesUtil;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private MessagesUtil messagesUtil;
	
	@GetMapping
	public String Default(@RequestParam(required = false,defaultValue ="") String message, ModelMap modelMap) {
		if(!message.equals("")) {
			Map<String, String> messages= messagesUtil.Messages(message);
			modelMap.addAttribute("message", messages.get("message"));
			modelMap.addAttribute("alert", messages.get("alert"));
		}
		return "LogIn";
	}
	


}
