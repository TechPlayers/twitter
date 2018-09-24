package com.twitter.service;

import com.twitter.model.dto.FollowUserRequest;
import com.twitter.model.dto.RestApiResponse;

/**
 * Service to handle retriving, adding follower.
 */
public interface FollowerService {
    /**
     * Service to add the follower user.
     *
     * @param
     * @param followUserRequest
     * @return RestApiResponse.java
     */
    RestApiResponse addFollower(FollowUserRequest followUserRequest);
}
