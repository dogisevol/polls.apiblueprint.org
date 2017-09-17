package org.apiblueprint.polls.domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Choice implements Serializable {


    private static final long serialVersionUID = 1292824767348769583L;
    @Id
    @SequenceGenerator(name = "choice_generator", sequenceName = "choice_generator", initialValue = 100)
    @GeneratedValue(generator = "choice_generator")
    private Long id;

    @Column(nullable = false)
    private String choice;

    @Column(nullable = false)
    private int votes = 0;

    @ManyToOne
    @Cascade(value = {org.hibernate.annotations.CascadeType.ALL})
    @JoinColumn(name = "poll_id")
    private Poll poll;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }
}
