package com.example.demo.customer;

import com.example.demo.customer.dto.CustomerCreateRequest;
import com.example.demo.customer.dto.CustomerUpdateRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repo;

    @Transactional
    @Override
    public Customer create(CustomerCreateRequest req) {
        if (repo.existsByEmail(req.email())) {
            throw new DataIntegrityViolationException("邮箱已存在: " + req.email());
        }
        Customer c = Customer.builder()
                .name(req.name())
                .email(req.email())
                .phone(req.phone())
                .status(req.status())
                .remark(req.remark())
                .build();
        return repo.save(c);
    }

    @Transactional
    @Override
    public Customer update(Long id, CustomerUpdateRequest req) {
        Customer c = repo.findById(id).orElseThrow(()-> new EntityNotFoundException("客户不存在: " + id));
        // 如果要严格校验“改为同邮箱时不误报”，可以仅在新邮箱与旧不同时才验证
        if (!c.getEmail().equalsIgnoreCase(req.email()) && repo.existsByEmail(req.email())) {
            throw new DataIntegrityViolationException("邮箱已存在: " + req.email());
        }
        c.setName(req.name());
        c.setEmail(req.email());
        c.setPhone(req.phone());
        c.setStatus(req.status());
        c.setRemark(req.remark());
        return repo.save(c);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("客户不存在: " + id);
        }
        repo.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Customer get(Long id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("客户不存在: " + id));
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Customer> page(String keyword, Pageable pageable) {
        if (keyword == null || keyword.isBlank()) {
            return repo.findAll(pageable);
        }
        return repo.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword, keyword, pageable);
    }
}
