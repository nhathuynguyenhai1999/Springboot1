package cg.codegym.minitest.springboot2.Controller;

import cg.codegym.minitest.springboot2.Model.Cart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShopController {
    @ModelAttribute("cart")
    public Cart setupCart(){
        return new Cart();
    }

    @GetMapping("/shopping-cart")
    public ModelAndView showCart (@SessionAttribute(value = "cart", required = false) Cart cart){
        if(cart == null){
            return new ModelAndView("error_404");
        }
        ModelAndView modelAndView = new ModelAndView("/computer/cart");
        modelAndView.addObject("cart",cart);
        return modelAndView;
    }
}
