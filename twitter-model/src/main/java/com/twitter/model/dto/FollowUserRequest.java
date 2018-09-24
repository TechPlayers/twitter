package com.twitter.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * FollowUserRequest for api to follow the user
 */
@Data
@ApiModel(description = "Request to follow user")
public class FollowUserRequest {

    @ApiModelProperty(value = "userId of the user", required = true)
    @NotNull(message = "userId must not be empty.")
    private Integer userId;


    @ApiModelProperty(value = "userId of the follower user", required = true)
    @NotNull(message = "followerUserId must not be empty.")
    private Integer followerUserId;
}