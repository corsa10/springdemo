package com.example.demo.customer;

import com.example.demo.common.ApiResponse;
import com.example.demo.customer.dto.CustomerCreateRequest;
import com.example.demo.customer.dto.CustomerUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @GetMapping
    public ApiResponse<Page<Customer>> page(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "0") int size,
            @RequestParam(defaultValue = "createdAt,desc") String sort,
            @RequestParam(required = false) String keyword
    ) {
        Sort sortObj = Sort.by( sort.contains(",")
                ? Sort.Order.by(sort.split(",")[0]).with(Sort.Direction.fromString(sort.split(",")[1]))
                : Sort.Order.desc(sort)
        );
        Pageable pageable = PageRequest.of(page, size, sortObj);
        return ApiResponse.ok(service.page(keyword, pageable));
    }

    @GetMapping("/{id}")
    public ApiResponse<Customer> get(@PathVariable Long id) {
        return ApiResponse.ok(service.get(id));
    }

    @PostMapping
    public ApiResponse<Customer> create(@Valid @RequestBody CustomerCreateRequest req) {
        return ApiResponse.ok("创建成功", service.create(req));
    }

    @PutMapping("/{id}")
    public ApiResponse<Customer> update(@PathVariable Long id, @Valid @RequestBody CustomerUpdateRequest req) {
        return ApiResponse.ok("更新成功", service.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.ok("删除成功", null);
    }

}
