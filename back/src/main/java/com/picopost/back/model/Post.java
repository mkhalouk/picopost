package com.picopost.back.model;

import javax.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "posts")  
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String photo;

    private String caption;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
