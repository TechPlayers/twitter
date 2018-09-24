package com.twitter.model.dao;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Entity for the Message
 */
@Data
@Table(name = "message")
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "userId", nullable = false)
    private int userId;

    @Column(name = "message", length = 140, nullable = false)
    private String message;

    @Column(name = "created_at")
    private Date createdAt = new Date();
}