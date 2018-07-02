package com.test.project.controller;

import com.test.project.entity.Customer;
import com.test.project.service.BaseService;
import com.test.project.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController extends BaseController<Customer, Long> {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        super(customerService);
        this.customerService = customerService;
    }

    @Override
    public BaseService<Customer, Long> getService() {
        return customerService;
    }
}
