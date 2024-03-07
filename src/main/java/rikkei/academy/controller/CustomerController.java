package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rikkei.academy.model.Customer;
import rikkei.academy.service.ICustomerService;

import java.util.List;

@Controller
@RequestMapping(value = {"/", "/customer"})
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public String showListCustomer(Model model) {
        List<Customer> customerList = customerService.findALl();
        model.addAttribute("customerList", customerList);
        return "customer/list";
    }

    @GetMapping("/{id}")
    public String showDetail(@PathVariable Long id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "customer/detail";
    }

    @GetMapping("/create")
    public String showFormCreate(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "customer/create";
    }

//        @PostMapping("/create")
//    public String createCustomer(@RequestParam String name,
//                                 @RequestParam String address){
//        List<Customer> customerList = customerService.findALl();
//        Long id;
//        if (customerList.size()==0) {
//            id = 1L;
//        }else {
//            id = customerList.get(customerList.size()-1).getId() + 1;
//        }
//        Customer customer = new Customer(id, name, address);
//        customerService.save(customer);
//        return "redirect:/";
//    }
// Cách 2: Truyền theo Object
    @PostMapping("/create")
    public String createCustomer(Customer customer) {
        List<Customer> customerList = customerService.findALl();
        Long id;
        if (customerList.size() == 0) {
            id = 1L;
        } else {
            id = customerList.get(customerList.size() - 1).getId() + 1;
        }
        customer.setId(id);
        customerService.save(customer);
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public String showFormEdit(@PathVariable Long id, Model model){
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "customer/edit";
    }
    @PostMapping("/edit")
    public String editCustomer(Customer customer){
          customerService.save(customer);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String showFormDelete(@PathVariable Long id, Model model){
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "/customer/delete";
    }
    @PostMapping("/delete")
    public String actionDelete(Customer customer){
        customerService.deleteById(customer.getId());
        return "redirect:/";
    }
}
