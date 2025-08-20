package com.example.demo.customer;

import com.example.demo.customer.dto.CustomerCreateRequest;
import com.example.demo.customer.dto.CustomerUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface CustomerService {
    Customer create(CustomerCreateRequest req);
    Customer update(Long id, CustomerUpdateRequest req);
    void delete(Long id);
    Customer get(Long id);
    Page<Customer> page(String keyword, Pageable pageable);
}
