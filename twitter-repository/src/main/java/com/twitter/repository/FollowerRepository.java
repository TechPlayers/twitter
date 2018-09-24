package com.twitter.repository;

import com.twitter.model.dao.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to retrive, add, list data from follower table
 */
@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {
}
