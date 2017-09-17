package org.apiblueprint.polls.web.model;

import org.apiblueprint.polls.domain.Choice;

public class ChoiceDTO {
    private String choice;
    private Integer votes;

    public ChoiceDTO(Choice choice) {
        this(choice.getChoice(), choice.getVotes());
    }

    public ChoiceDTO(String choice, Integer votes) {
        this.choice = choice;
        this.votes = votes;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }
}

