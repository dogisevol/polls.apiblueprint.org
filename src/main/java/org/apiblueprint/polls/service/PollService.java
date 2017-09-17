package org.apiblueprint.polls.service;

import org.apiblueprint.polls.domain.Poll;
import org.apiblueprint.polls.web.model.PollDTO;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface PollService {
    Collection<PollDTO> fetchAll();

    @Transactional
    Poll savePoll(Poll poll);

    @Transactional
    PollDTO getById(Long id);
}
