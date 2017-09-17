package org.apiblueprint.polls.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Collection;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Poll implements Serializable {

    private static final long serialVersionUID = -3501514625217380338L;
    @Id
    @SequenceGenerator(name = "question_generator", sequenceName = "question_sequence", initialValue = 100)
    @GeneratedValue(generator = "question_generator")
    private Long id;

    @Column(nullable = false)
    private String question;

    @Column(name = "published_at", insertable = true, updatable = false, columnDefinition = "timestamp default current_timestamp")
    @CreationTimestamp
    private ZonedDateTime publishedAt;

    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL)
    private Collection<Choice> choices;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ZonedDateTime getPublishedAt() {
        return publishedAt;
    }

    public Collection<Choice> getChoices() {
        return choices;
    }

    public void setChoices(Collection<Choice> choices) {
        this.choices = choices;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPublishedAt(ZonedDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }
}
