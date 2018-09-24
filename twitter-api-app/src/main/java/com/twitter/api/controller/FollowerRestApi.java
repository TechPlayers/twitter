package com.twitter.api.controller;

import com.twitter.model.dto.FollowUserRequest;
import com.twitter.model.dto.RestApiResponse;
import com.twitter.service.FollowerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "Follower API", description = "The API provides REST endpoints" +
        " to Add/List Follower.")
@RestController
@RequestMapping("/api/v1")
public class FollowerRestApi {
    private final FollowerService followerService;

    @Autowired
    public FollowerRestApi(FollowerService followerService) {
        this.followerService = followerService;
    }

    /**
     * Add Follower
     *
     * @param followUserRequest FollowUserRequest to add follower
     * @return RestApiResponse.java Response of adding follower
     */
    @ApiOperation(value = "Add follower using FollowUserRequest",
            response = RestApiResponse.class)
    @RequestMapping(value = "/add-follower", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public RestApiResponse addFollower(@RequestBody @Valid final FollowUserRequest followUserRequest) {
        return this.followerService.addFollower(followUserRequest);
    }
}