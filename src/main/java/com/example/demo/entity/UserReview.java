package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="user_review")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="review_at", nullable = false)
    private LocalDateTime reviewAt;
    @Column(name="review", nullable = false)
    private String review;
    @OneToOne(fetch = FetchType.EAGER) // default
    private UserOrder userOrder;
    @ManyToOne(fetch = FetchType.EAGER) // default
    @JsonBackReference("User")
    private User user;
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
