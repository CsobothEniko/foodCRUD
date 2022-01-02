package com.FoodCrud.controller;

import com.FoodCrud.domain.Food;
import com.FoodCrud.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FoodController {

    @Autowired
    private FoodService service;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<Food> listfood = service.listAll();
        model.addAttribute("listfood", listfood);
        System.out.print("Get / ");
        return "index";
    }

    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("food", new Food());
        return "new";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveFood(@ModelAttribute("food") Food std) {
        service.save(std);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditStudentPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("new");
        Food std = service.get(id);
        mav.addObject("food", std);
        return mav;

    }
    @RequestMapping("/delete/{id}")
    public String deletefood(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/";
    }

}
