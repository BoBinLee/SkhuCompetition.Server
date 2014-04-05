package spring01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HelloController {
	@RequestMapping("/hello.do")
	public ModelAndView hello(){
		ModelAndView result = new ModelAndView("hello");
		result.addObject("message", "hello, world!");
		return result;
	}
}
