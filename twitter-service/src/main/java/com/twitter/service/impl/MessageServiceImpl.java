package com.twitter.service.impl;

import com.twitter.model.dao.Message;
import com.twitter.model.dto.MessageRequest;
import com.twitter.model.dto.RestApiResponse;
import com.twitter.repository.MessageRepository;
import com.twitter.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @inheritDoc
 */
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Message> listUserMessage(int userId, String orderBy, String orderType) {
        Sort.Direction sortDirection = Sort.Direction.ASC;

        Message message = new Message();
        message.setUserId(userId);
        message.setCreatedAt(null);

        if (orderType.equals("DESC")) {
            sortDirection = Sort.Direction.DESC;
        }
        return listMessage(message, orderBy, sortDirection);
    }

    private List<Message> listMessage(Message message, String orderBy, Sort.Direction orderType) {
        Example<Message> example = Example.of(message);
        Sort sort = new Sort(orderType, orderBy);
        return this.messageRepository.findAll(example, sort);
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Message> listFollowingUserMessage(int userId) {
        return this.messageRepository.findByFollowerId(userId);
    }

    /**
     * @inheritDoc
     */
    @Override
    public RestApiResponse addMessage(final MessageRequest messageRequest) {
        RestApiResponse restApiResponse = new RestApiResponse();

        Message message = new Message();
        message.setMessage(messageRequest.getMessage());
        message.setUserId(messageRequest.getUserId());

        try {
            Message respMessage = this.messageRepository.saveAndFlush(message);
            restApiResponse.setStatus("SUCCESS");
            restApiResponse.setDetails(String.format("Message {%s}added successfully.", respMessage.getMessage()));
        } catch (Exception e) {
            restApiResponse.setStatus("FAIL");
            restApiResponse.setError(e.toString());
            restApiResponse.setDetails(e.getMessage());
        }

        return restApiResponse;
    }
}