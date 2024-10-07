package hello.AYU.web.event.members;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {


    @GetMapping("/adminlogin")
    public String adminMembers(Model model) {
        return "members/adminLogin";
    }
    @PostMapping("/adminlogin")
    public String adminlogin(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             Model model, HttpSession session) {

        // 간단한 로그인 처리 로직 (실제로는 데이터베이스에서 사용자 확인 필요)
        if ("admin".equals(username) && "1234".equals(password)) {
            session.setAttribute("adminLogin", true);  // 세션에 로그인 정보 저장
            return "redirect:/basic/events";  // 로그인 성공 시 이벤트 페이지로 리다이렉트
        } else {
            model.addAttribute("error", "아이디 또는 비밀번호가 틀렸습니다.");
            return "members/adminLogin";  // 로그인 실패 시 다시 로그인 폼으로 이동
        }
    }

    @GetMapping("/userlogin")
    public String userMembers(Model model) {
        return "members/userLogin";
    }

    @PostMapping("/userlogin")
    public String userlogin(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model) {

        // 간단한 로그인 처리 로직 (실제로는 데이터베이스에서 사용자 확인 필요)
        if ("admin".equals(username) && "password".equals(password)) {
            model.addAttribute("message", "로그인 성공!");
            model.addAttribute("username", username);
            return "welcome";  // 로그인 성공 시 환영 페이지로 이동
        } else {
            model.addAttribute("error", "아이디 또는 비밀번호가 틀렸습니다.");
            return "loginForm";  // 로그인 실패 시 다시 로그인 폼으로 이동
        }
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/basic/events";
    }
}
