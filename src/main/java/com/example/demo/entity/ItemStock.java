package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="item_stock")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="stock", nullable = false)
    private Integer stock;
    @OneToOne(fetch = FetchType.EAGER) // default
    private Item item;
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
