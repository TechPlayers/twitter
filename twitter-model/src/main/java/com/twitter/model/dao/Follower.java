package com.twitter.model.dao;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Date;

/**
 * Entity for the Follower. I have made very simple table we can also add active column etc ,
 * also we can make it with prefix like twit_
 */
@Data
@Table(
        name = "follower",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"userId", "followerUserId"})}
)
@Entity
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "userId", nullable = false)
    private int userId;

    @Column(name = "followerUserId", nullable = false)
    private int followerUserId;

    @Column(name = "created_at")
    private Date createdAt = new Date();
}