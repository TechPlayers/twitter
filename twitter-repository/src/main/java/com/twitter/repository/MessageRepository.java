package com.twitter.repository;

import com.twitter.model.dao.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository to retrieve, add, list data from message table
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    /*
    @param followerId userId of the follower user
     */
    @Query(
            value = "SELECT m.* FROM message m " +
                    "INNER JOIN follower f ON m.user_id = f.user_id " +
                    "WHERE f.follower_user_id = ?1 " +
                    "ORDER BY created_at DESC ",
            nativeQuery = true)
    List<Message> findByFollowerId(int followerId);
}
