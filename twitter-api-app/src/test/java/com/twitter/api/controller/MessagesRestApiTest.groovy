package com.twitter.api.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.twitter.model.dto.MessageRequest
import com.twitter.service.MessageService
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

/**
 * Groovy Test to do unit testing of MessageRestApiTest
 * Note: all test cases covering message adding scenario, we can also write test cases to list message
 */
class MessageRestApiTest extends Specification {
    def mapper = new ObjectMapper()
    MockMvc mockMvc
    def messageService = Mock(MessageService)
    def msgPostUrl = "/api/v1/post-message"
    def userId = 2
    def message = "Hello All!"
    def largeMessage = "It is a long established fact that a reader will be distracted by the readable content " +
            "of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less " +
            "normal distribution of letters, as opposed to using 'Content here, content here', making it look like " +
            "readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their " +
            "default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. " +
            "Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour" +
            " and the like)."

    def setup() {
        def messageRestApi = new MessageRestApi(messageService)
        mockMvc = MockMvcBuilders.standaloneSetup(messageRestApi).build()
    }

    def "Rest API endpoint /post-message returns status ok"() {
        given:
        def messageRequest = new MessageRequest(userId: userId, message: message)

        when:
        mockMvc.
                perform(MockMvcRequestBuilders.post(msgPostUrl)
                        .content(mapper.writeValueAsString(messageRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())

        then:
        1 * messageService.addMessage(messageRequest)
    }

    def "Rest API endpoint /post-message returns 400 if message is more than 140"() {
        given:
        def messageRequest = new MessageRequest(userId: userId, message: largeMessage)

        when:
        mockMvc.
                perform(MockMvcRequestBuilders.post(msgPostUrl)
                        .content(mapper.writeValueAsString(messageRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())

        then:
        0 * messageService.addMessage(messageRequest)
    }

    def "Rest API endpoint /post-message returns 400 if message is missing"() {
        given:
        def messageRequest = new MessageRequest(userId: userId)

        when:
        mockMvc.
                perform(MockMvcRequestBuilders.post(msgPostUrl)
                        .content(mapper.writeValueAsString(messageRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())

        then:
        0 * messageService.addMessage(messageRequest)
    }

    def "Rest API endpoint /post-message returns 400 if userId is missing"() {
        given:
        def messageRequest = new MessageRequest(message: message)

        when:
        mockMvc.
                perform(MockMvcRequestBuilders.post(msgPostUrl)
                        .content(mapper.writeValueAsString(messageRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())

        then:
        0 * messageService.addMessage(messageRequest)
    }
    // We can write more test to cover message list etc
}