package com.test.project.service;

import com.test.project.entity.Customer;
import com.test.project.repository.BaseRepository;
import com.test.project.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerService extends BaseService<Customer, Long> {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public BaseRepository<Customer, Long> getRepository() {
        return customerRepository;
    }
}
