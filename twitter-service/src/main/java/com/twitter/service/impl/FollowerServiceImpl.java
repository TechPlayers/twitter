package com.twitter.service.impl;

import com.twitter.model.dao.Follower;
import com.twitter.model.dto.FollowUserRequest;
import com.twitter.model.dto.RestApiResponse;
import com.twitter.repository.FollowerRepository;
import com.twitter.service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @inheritDoc
 */
@Service
public class FollowerServiceImpl implements FollowerService {
    private final FollowerRepository followerRepository;

    @Autowired
    public FollowerServiceImpl(final FollowerRepository followerRepository) {
        this.followerRepository = followerRepository;
    }

    /**
     * @inheritDoc
     */
    @Override
    public RestApiResponse addFollower(FollowUserRequest followUserRequest) {
        RestApiResponse restApiResponse = new RestApiResponse();

        if (followUserRequest.getFollowerUserId().equals(followUserRequest.getUserId())) {
            restApiResponse.setStatus("FAIL");
            restApiResponse.setError("");
            restApiResponse.setDetails("userId and followerUserId must not be same.");
            return restApiResponse;
        }

        Follower follower = new Follower();
        follower.setFollowerUserId(followUserRequest.getFollowerUserId());
        follower.setUserId(followUserRequest.getUserId());

        try {
            this.followerRepository.saveAndFlush(follower);
            restApiResponse.setStatus("SUCCESS");
            restApiResponse.setDetails("Follower added successfully.");
        } catch (Exception e) {
            restApiResponse.setStatus("FAIL");
            restApiResponse.setError(e.toString());
            restApiResponse.setDetails(e.getMessage());
        }

        return restApiResponse;
    }
}