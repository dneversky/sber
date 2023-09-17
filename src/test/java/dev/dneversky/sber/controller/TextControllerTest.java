package dev.dneversky.sber.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import dev.dneversky.sber.reqeust.TextRequest;
import dev.dneversky.sber.validator.TextValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TextController.class)
class TextControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TextValidator textValidator;

    @Test
    void checkBrackets() throws Exception {
        TextRequest textRequest = new TextRequest("Это мое любимое место для отдыха (вместе с друзьями).");
        mockMvc.perform(post("/api/checkBrackets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(buildBody(textRequest)))
                .andExpect(status().isOk());
    }

    private String buildBody(TextRequest textRequest) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(textRequest);
    }
}