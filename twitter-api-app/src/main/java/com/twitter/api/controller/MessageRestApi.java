package com.twitter.api.controller;

import com.twitter.model.dao.Message;
import com.twitter.model.dto.MessageRequest;
import com.twitter.model.dto.RestApiResponse;
import com.twitter.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Message API", description = "The API provides REST endpoints" +
        " to Add/List message.")
@RestController
@RequestMapping("/api/v1")
public class MessageRestApi {
    private final MessageService messageService;

    @Autowired
    public MessageRestApi(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * Add message
     *
     * @param messageRequest MessageRequest to add
     * @return RestApiResponse.java Response of posting a message
     */
    @ApiOperation(value = "Post message using MessageRequest",
            response = RestApiResponse.class)
    @RequestMapping(value = "/post-message", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public RestApiResponse addMessage(@RequestBody @Valid final MessageRequest messageRequest) {
        return this.messageService.addMessage(messageRequest);
    }

    /**
     * List all message posted by the user
     *
     * @param userId userId of the user, you want to retrive message of
     * @return List<Message> List of the message
     */
    @ApiOperation(
            value = "List all message posted by the user",
            response = Message.class)
    @GetMapping(
            value = "/list-message/{userId}/{orderBy}/{{orderType}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public List<Message> listUserMessage(@PathVariable("userId") final int userId,
                                         @RequestParam(value = "orderBy", required = false, defaultValue = "createdAt")
                                             final String orderBy,
                                         @RequestParam(value = "orderType", required = false, defaultValue = "DESC")
                                             final String orderType) {
        return this.messageService.listUserMessage(userId, orderBy, orderType);
    }

    /**
     * List all message posted by the following users
     *
     * @param userId userId of the follower user
     * @return List<Message> List of the message
     */
    @ApiOperation(
            value = "List all message posted by the following users",
            response = Message.class)
    @GetMapping(
            value = "/list-following-message/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public List<Message> listFollowingUserMessage(@PathVariable("userId") final int userId) {
        return this.messageService.listFollowingUserMessage(userId);
    }
}