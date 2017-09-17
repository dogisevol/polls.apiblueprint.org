package org.apiblueprint.polls.service;

import org.apiblueprint.polls.domain.Poll;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

interface PollRepository extends CrudRepository<Poll, Long> {
    @Query(value = "SELECT DISTINCT p FROM Poll p LEFT JOIN FETCH p.choices")
    Iterable<Poll> fetchAll();
}
