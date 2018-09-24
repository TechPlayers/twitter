package com.twitter.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Response for calling RestAPI for POST method etc.
 */
@Data
@ApiModel(description = "Response generated.")
public class RestApiResponse {

    @ApiModelProperty(value = "The reason for the failure.")
    private String error;

    @ApiModelProperty(value = "status of operation. " +
            "Its returns SUCCESS operation successfully else it returns FAIL.")
    private String status;

    @ApiModelProperty(value = "Details error message for the failure.")
    private String details;
}