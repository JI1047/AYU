package hello.AYU.web.event.basic;


import hello.AYU.domain.event.Event;
import hello.AYU.domain.event.EventRepository;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/basic/events")
public class BasicEventController {

    EventRepository eventRepository=new EventRepository();
    @GetMapping
    public String events() {
        // 정적 리소스 폴더의 basic/0index.html로 리다이렉트
        return "redirect:/0index.html";
    }
    @GetMapping("/left")
    public String left() {
        return "2left"; // resources/templates/2left.html 파일을 렌더링
    }

    @GetMapping("/login")
    public String loginForm() {

        return "basic/loginForm"; // 로그인 페이지로 이동
    }



    //
//    @GetMapping("/{itemId}")
//    public String item(@PathVariable Long itemId, Model model) {
//        Event event = eventRepository.findById(itemId);
//        model.addAttribute("event", event);
//        return "basic/event";
//    }
//
    @GetMapping("/add")
    public String addForm(Model model, HttpSession session) {
        if (session.getAttribute("adminLogin") == null) {
            return "redirect:/members/adminlogin";  // 로그인하지 않은 경우 로그인 페이지로 리다이렉트
        }
        model.addAttribute("event", new Event());
        return "basic/addForm";
    }

    @PostMapping("/add")
    public String addEvent(@ModelAttribute("event") Event event) {

        eventRepository.save(event);
        return "redirect:/basic/events/"+ event.getId();

    }
//

//
//    @GetMapping("/edit/{itemId}")
//    public String editForm(@PathVariable Long itemId, Model model) {
//
//        Event event = eventRepository.findById(itemId);
//        model.addAttribute("event", event);
//        return "basic/editForm";
//    }
//
//    @PostMapping("/edit/{itemId}")
//    public String edit(@PathVariable Long itemId, @ModelAttribute Event event) {
//        eventRepository.update(event, itemId);
//        return "redirect:/basic/events/" + itemId; // itemId를 문자열로 직접 추가
//    }
//
//
//    @PostConstruct
//    public void init() {
//
//        eventRepository.save(new Event("축제", "화", "아리관"));
//        eventRepository.save(new Event("시험", "수", "수리관"));
//
//    }

//}

}
