package org.delivery.storeadmin.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class pageController {

    @RequestMapping(path = {"", "/main"})
    public ModelAndView main2(){
        return new ModelAndView("display");    //main.html로 연결
    }

    @RequestMapping("/order")
    public ModelAndView order(){
        return new ModelAndView("order/order"); //order.html로 연결
    }

}
