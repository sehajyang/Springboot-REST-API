package me.sehajyang.io.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerTests {
    
    @Autowired
    MockMvc mockMvc;
    
    //mockMvc.content에게 데이터를 json형태로 넘겨주기 위함
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void createEvent() throws Exception {
        //요청 생성
        Event event = Event.builder()
                .name("Spring")
                .description("Rest Api Dev")
                .beginEnrollmentDateTime(LocalDateTime.of(2019, 01, 04, 14, 20))
                .closeEnrollmentDateTime(LocalDateTime.of(2019, 01, 05, 14, 20))
                .beginEventDateTime(LocalDateTime.of(2019, 01, 06, 14, 20))
                .endEventDateTime(LocalDateTime.of(2019, 01, 07, 14, 20))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("Seoul, Korea")
                .build();

        mockMvc.perform(post("/api/events/")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaTypes.HAL_JSON)
                    .content(objectMapper.writeValueAsString(event)))
                .andDo(print()) // 어떠한 응답과 요청이 갔는지 볼 수 있음
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(jsonPath("id").value(Matchers.not(100)))
                .andExpect(jsonPath("free").value(Matchers.not(true)))
                .andExpect(jsonPath("eventStatus").value(EventStatus.DRAFT.name()))
                ;
    
    }
    @Test
    public void createEvent_Bad_Request() throws Exception {
        //요청 생성
        Event event = Event.builder()
                .id(100)
                .name("Spring")
                .description("Rest Api Dev")
                .beginEnrollmentDateTime(LocalDateTime.of(2019, 01, 04, 14, 20))
                .closeEnrollmentDateTime(LocalDateTime.of(2019, 01, 05, 14, 20))
                .beginEventDateTime(LocalDateTime.of(2019, 01, 06, 14, 20))
                .endEventDateTime(LocalDateTime.of(2019, 01, 07, 14, 20))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("Seoul, Korea")
                .free(true)
                .offline(false)
                .eventStatus(EventStatus.PUBLISHED)
                .build();

        mockMvc.perform(post("/api/events/")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaTypes.HAL_JSON)
                    .content(objectMapper.writeValueAsString(event)))
                .andDo(print()) // 어떠한 응답과 요청이 갔는지 볼 수 있음
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(jsonPath("id").value(Matchers.not(100)))
                .andExpect(jsonPath("free").value(Matchers.not(true)))
                .andExpect(jsonPath("eventStatus").value(EventStatus.DRAFT.name()))
                ;

    }

}
