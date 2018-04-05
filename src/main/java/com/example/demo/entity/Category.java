package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="category")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name", nullable = false)
    private String name;
    @Column(name="create_at", nullable = false)
    @JsonIgnore
    private LocalDateTime createAt;
    @Column(name="update_at", nullable = false)
    @JsonIgnore
    private LocalDateTime updateAt;

    public static Category of(Long id, String name) {
        return Category.builder()
            .id(id)
            .name(name)
            .createAt(LocalDateTime.now())
            .updateAt(LocalDateTime.now())
            .build();
    }

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
