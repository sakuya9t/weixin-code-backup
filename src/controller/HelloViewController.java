package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloViewController {
	@RequestMapping(value = "/hello", produces = "text/html; charset=utf-8")  
    public ModelAndView getHelloHomePage() {  
        ModelAndView view = new ModelAndView("Hello");  
        return view;  
	}
}
