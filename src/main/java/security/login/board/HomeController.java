package security.login.board;

import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	 private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
		
		/**
		 * Simply selects the home view to render by returning its name.
		 */
		@RequestMapping(value = "/", method = RequestMethod.GET)
		public String home(Locale locale, Model model) {
			logger.info("Welcome home! The client locale is {}.", locale);
			
			Date date = new Date();
			DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
			
			String formattedDate = dateFormat.format(date);
			
			model.addAttribute("serverTime", formattedDate );
			
			return "home";
		}
		
		@RequestMapping(value = "/login/loginForm")
		public String loginForm(Model model) {
			logger.info("Welcome to login Form");
					
			return "login/loginForm2"; // 꾸민 페이지 가도록 물리경로 지정
		}
		
		@RequestMapping(value = "/user/userHome")
		public String userHome(Model model) {
			logger.info("Welcome to Introduction!");
					
			return "user/userHome"; // 유저 홈으로 가자!
		}
		
		@RequestMapping(value = "/admin/adminHome")
		public String adminHome(Model model) {
			logger.info("Welcome to Admin Home!");
					
			return "/admin/adminHome"; 
			// 리턴값으로 여기에 "/list2"를 주어도 이상하게 내용이 안나옴..
			// list2.jsp 만 뜨고, 게시판 글들이 안나왔다는 것..
			// home.jsp에서 [관리자 홈] 링크 a 태그로 /list2 줘야... 내용이 나왔다 
		}
		
		@RequestMapping(value = "/login/accessDenied")
		public String accessDenied(Model model) {
			logger.info("Welcome to accessDenied!");
					
			return "login/accessDenied";
		}
		
		// 입력한 아이디를 볼 수 있는 페이지 => Principal 객체 이용
		@RequestMapping(value = "/loginInfo", method = RequestMethod.GET)
		public String loginInfo(Principal principal,Model model) {
			
			//1.Controller를 통하여 Pincipal객체로 가져오는 방법
			String user_id = principal.getName();
			System.out.println("유저 아이디:" + user_id   );
			
			//2.SpringContextHolder를 통하여 가져오는 방법
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        user_id = auth.getName(); 
	        System.out.println("유저 아이디:" + user_id   );
	        
	    	//3.User 클래스로 변환 하여 가져오는 방법 (spring 클래스인 User (개발자가 만드는 bean아님))
	        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        user_id = user.getUsername();
	        System.out.println("유저 아이디:" + user_id   );
	        
	        return "home";
		}
		
}
