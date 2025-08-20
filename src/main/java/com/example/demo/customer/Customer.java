package com.example.demo.customer;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import java.time.Instant;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name="customers",
        indexes = {
            @Index(name = "idx_customer_email", columnList = "email"),
            @Index(name = "idx_customer_name", columnList = "name")
        },
        uniqueConstraints = {
            @UniqueConstraint(name = "uk_customer_email", columnNames = "email")
       })

@Comment("CRM 客户")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("客户项目")
    @Column(nullable = false, length = 100)
    private String name;

    @Comment("邮箱(唯一)")
    @Column(nullable = false, length=120)
    private String email;

    @Comment("手机号")
    @Column(length = 30)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 16)
    private CustomerStatus status;

    @Comment("备注")
    @Column(columnDefinition = "text")
    private String remark;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedBy
    @Column(nullable = false)
    private Instant updatedAt;
}
