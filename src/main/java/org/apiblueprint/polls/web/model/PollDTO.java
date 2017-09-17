package org.apiblueprint.polls.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apiblueprint.polls.domain.Choice;
import org.apiblueprint.polls.domain.Poll;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;

public class PollDTO {
    private String question;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private ZonedDateTime publishedAt;
    private Collection<ChoiceDTO> choices = new ArrayList<>();
    @JsonIgnore
    private Long id;

    public PollDTO(Poll poll) {
        this.question = poll.getQuestion();
        this.publishedAt = poll.getPublishedAt();
        this.id = poll.getId();
        for (Choice choice : poll.getChoices()) {
            choices.add(new ChoiceDTO(choice));
        }
    }

    public PollDTO(Long id, String question, ZonedDateTime publishedAt, Collection<ChoiceDTO> choices) {
        this.question = question;
        this.publishedAt = publishedAt;
        this.choices = choices;
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Collection<ChoiceDTO> getChoices() {
        return choices;
    }

    public void setChoices(Collection<ChoiceDTO> choices) {
        this.choices = choices;
    }

    public ZonedDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(ZonedDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
