package com.example.demo.entity;

import com.example.demo.type.StandardType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="item")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name", nullable = false)
    private String name;
    @Column(name="price", nullable = false)
    private Integer price;
    @Column(name="sales_from", nullable = false)
    private LocalDate salesFrom;
    @Column(name="sales_to", nullable = false)
    private LocalDate salesTo;
    @Enumerated
    @Column(name="standard_type", nullable = false)
    private StandardType standardType;
    @ManyToOne(fetch = FetchType.EAGER) // default
    private Category category;
    @Column(name="create_at", nullable = false)
    @JsonIgnore
    private LocalDateTime createAt;
    @Column(name="update_at", nullable = false)
    @JsonIgnore
    private LocalDateTime updateAt;

    @PrePersist
    private void prePersist() {
        createAt = LocalDateTime.now();
        updateAt = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        updateAt = LocalDateTime.now();
    }
}

