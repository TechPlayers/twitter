package com.twitter.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * MessageRequest for api to post message
 */
@Data
@ApiModel(description = "Request to post message")
public class MessageRequest {

    @ApiModelProperty(value = "userId of the user.", required = true)
    @NotNull(message = "userId must not be empty.")
    private Integer userId;

    @ApiModelProperty(value = "The message user wants to post. " +
            "message length must be between 2 and 140", required = true)
    @NotNull(message = "Message must not be empty")
    @Size(min = 2, max = 140)
    private String message;
}