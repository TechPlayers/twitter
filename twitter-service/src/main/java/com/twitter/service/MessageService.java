package com.twitter.service;

import com.twitter.model.dao.Message;
import com.twitter.model.dto.MessageRequest;
import com.twitter.model.dto.RestApiResponse;

import java.util.List;

/**
 * Service to handle retriving, adding , updating, Listing Message.
 */
public interface MessageService {

    /**
     * Service to add the message.
     *
     * @param messageRequest
     * @return RestApiResponse.java
     */
    RestApiResponse addMessage(final MessageRequest messageRequest);

    /**
     * Service to get list of all message added by user.
     *
     * @param userId userId of the user
     * @return RestApiResponse.java
     */
    List<Message> listUserMessage(int userId, String orderBy, String orderType);

    /**
     * Service to get list of all message added by following users.
     *
     * @param userId userId of the user
     * @return RestApiResponse.java
     */
    List<Message> listFollowingUserMessage(int userId);
}