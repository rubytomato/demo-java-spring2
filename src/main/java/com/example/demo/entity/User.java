package com.example.demo.entity;

import com.example.demo.type.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="nick_name", nullable = false)
    private String nickName;
    @Enumerated
    @Column(name="gender", nullable = false)
    private Gender gender;
    @Column(name="prefecture_id", nullable = false)
    private Integer prefectureId;
    @Column(name="email")
    private String email;
    @Column(name="create_at", nullable = false)
    @JsonIgnore
    private LocalDateTime createAt;
    @Column(name="update_at", nullable = false)
    @JsonIgnore
    private LocalDateTime updateAt;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, // default
            cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("User")
    private List<UserOrder> userOrders;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, // default
            cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("User")
    private List<UserReview> userReviews;

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
