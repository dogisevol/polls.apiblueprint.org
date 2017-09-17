package org.apiblueprint.polls;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apiblueprint.polls.domain.Poll;
import org.apiblueprint.polls.service.PollService;
import org.apiblueprint.polls.web.model.ChoiceDTO;
import org.apiblueprint.polls.web.model.PollDTO;
import org.apiblueprint.polls.web.model.PollModel;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = PollsApplication.class
)
@AutoConfigureMockMvc
public class PollsApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private PollService pollService;

    @MockBean
    private PollModel pollModel;


    private String publishedAt;

    @Before
    public void setUp() {
        List<PollDTO> polls = new ArrayList();
        List<ChoiceDTO> choices = new ArrayList();
        ZonedDateTime now = ZonedDateTime.now();
        publishedAt = dtf.format(now);
        choices.add(new ChoiceDTO("Choice", 10));
        PollDTO pollDTO = new PollDTO(1L, "Question", now, choices);
        polls.add(pollDTO);

        Mockito.when(pollService.fetchAll()).thenReturn(polls);
        Mockito.when(pollService.savePoll(Mockito.any(Poll.class)))
                .thenReturn(Mockito.mock(Poll.class));
        Mockito.when(pollService.getById(Mockito.any(Long.class)))
                .thenReturn(pollDTO);
    }

    @Test
    public void testGetQuestions() throws Exception {
        assertResult(this.mockMvc.perform(get("/questions")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk()));

    }

    @Test
    public void testPostQuestions() throws Exception {
        Mockito.mock(PollModel.class);
        assertResult(this.mockMvc.perform(post("/questions")
                .content(objectMapper.writeValueAsString(new PollModel("Question", Lists.newArrayList(""))))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).andExpect(status().isCreated()));
    }

    private void assertResult(ResultActions perform) throws Exception {
        String content = perform
                .andExpect(content().contentType(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andReturn().getResponse().getContentAsString();
        assert content.contains(publishedAt);
        assert content.contains("Question");
        assert content.contains("Choice");
    }

}
