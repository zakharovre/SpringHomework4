package ru.geekbrains.hw4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import ru.geekbrains.hw4.models.Product;
import ru.geekbrains.hw4.services.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public String getAllProducts(Model model){
        model.addAttribute("Products", productService.getAll());
        return "productsView";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product){
        productService.addOrUpdate(product);
        return "redirect:/products/all";
    }

    @GetMapping("/remove/{id}")
    public String removeProduct(@PathVariable int id){
        productService.removeProductById(id);
        return "redirect:/products/all";
    }

    @GetMapping("/getid")
    public String getid(Model model,
                        @RequestParam(required = false, name = "id") int id)
    {
        try {
            model.addAttribute("up", productService.getById(id));
            return "uniqueProduct";
        } catch (Exception e){
            return "redirect:/products/all";
        }
    }
}
