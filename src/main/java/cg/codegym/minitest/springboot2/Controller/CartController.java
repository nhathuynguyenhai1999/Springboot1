package cg.codegym.minitest.springboot2.Controller;

import cg.codegym.minitest.springboot2.Model.Cart;
import cg.codegym.minitest.springboot2.Model.Computer;
import cg.codegym.minitest.springboot2.Service.iml.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@SessionAttributes("cart")
public class CartController {

    private final ComputerService computerService;
    @Autowired
    public CartController(ComputerService computerService) {
        this.computerService = computerService;
    }
    @ModelAttribute("cart")
    public Cart setupCart() {
        return new Cart();
    }

    @GetMapping("/cart")
    public String showCart(@ModelAttribute("cart") Cart cart, Model model) {
        model.addAttribute("cart", cart);
        return "computer/cart";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id,
                            @ModelAttribute Cart cart,
                            @RequestParam("action") String action) throws Exception {
        Optional<Computer> productOptional = computerService.findById(id);
        if (productOptional.isEmpty()) {
            return "/error_404";
        }
        if (action.equals("show")) {
            cart.addComputer(productOptional.get());
            return "redirect:/cart";
        }
        cart.addComputer(productOptional.get());
        return "redirect:/computers";
    }
}
