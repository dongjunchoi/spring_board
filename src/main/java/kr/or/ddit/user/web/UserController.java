package kr.or.ddit.user.web;

import java.io.FileInputStream;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;

@RequestMapping("user")
@Controller
public class UserController {
	
	@Resource(name = "userService")
	private UserService userService;
	
	
	@RequestMapping(path = "login",method = RequestMethod.GET)
	public String login() {
		
		return "board/login";
		
	}
	
	
	@RequestMapping(path = "login",method = RequestMethod.POST)
	public String loginUser(String userid,String pass, HttpSession session, Model model) {
		UserVo user = userService.selectUser(userid);
		
		
		if( user !=null && pass.equals(user.getPass())) {
			session.setAttribute("S_USER", user); 
			return "redirect:/board/mainController";
			
		}else {
			model.addAttribute("userid", userid);
			return "board/login";
		}
		
	}
	
	@RequestMapping(path = "logout",method = RequestMethod.GET)
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/user/login";
		
		
	}
	
	
	@RequestMapping("profile")
	public void profile(HttpServletResponse resp, String userid, HttpServletRequest req) {

		resp.setContentType("image");
		
		
		
		UserVo userVo = userService.selectUser(userid);
		
		String path = "";
		if(userVo.getRealfilename() == null) {
			path = req.getServletContext().getRealPath("/image/unknown.png");
		}else {
		
			path = userVo.getRealfilename();
		}
		
		try {
			
			FileInputStream fis = new FileInputStream(path);
			ServletOutputStream sos = resp.getOutputStream();
			
			byte[] buff = new byte[512];
			while(fis.read(buff)!=-1) {
				
				sos.write(buff);
				
			}
			
			
			fis.close();
			sos.flush();
			sos.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
}
