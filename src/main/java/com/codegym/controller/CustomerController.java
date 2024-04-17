package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    ICustomerRepository iCustomerRepository;

    @GetMapping()
    public String showList(Model model){
        List<Customer> customers = iCustomerRepository.findAll();
        model.addAttribute("customers", customers);
        return "list";
    }
    @GetMapping("/{id}")
    public ModelAndView showCustomer(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("customer");
        Optional<Customer> optionalCustomer = iCustomerRepository.findById(id);
        System.out.println("hello");
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            modelAndView.addObject("customer", customer);
        }else {
            modelAndView.setViewName("error");
            modelAndView.addObject("message", "Không tìm thấy khách hàng");
        }
        return modelAndView;
    }
}
