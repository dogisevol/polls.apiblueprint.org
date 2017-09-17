package org.apiblueprint.polls.web.model;

import org.apiblueprint.polls.domain.Choice;
import org.apiblueprint.polls.domain.Poll;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PollModel {
    private String question;
    private Collection<String> choices;

    public PollModel() {

    }

    public PollModel(String question, Collection<String> choices) {
        this.question = question;
        this.choices = choices;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Iterable<String> getChoices() {
        return choices;
    }

    public void setChoices(Collection<String> choices) {
        this.choices = choices;
    }

    public Poll toEntity() {
        Poll poll = new Poll();
        poll.setQuestion(this.question);
        List<Choice> choiceList = new ArrayList();
        for (String choiceText : choices) {
            Choice choice = new Choice();
            choice.setChoice(choiceText);
            choice.setPoll(poll);
            choiceList.add(choice);
        }
        poll.setChoices(choiceList);
        return poll;
    }
}
