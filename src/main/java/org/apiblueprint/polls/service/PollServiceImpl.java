package org.apiblueprint.polls.service;

import org.apiblueprint.polls.domain.Poll;
import org.apiblueprint.polls.web.model.PollDTO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Component("questionService")
@Transactional
public class PollServiceImpl implements PollService {

    private final PollRepository pollRepository;
    private final ChoiceRepository choiceRepository;

    public PollServiceImpl(PollRepository questionRepository, ChoiceRepository choiceRepository) {
        this.pollRepository = questionRepository;
        this.choiceRepository = choiceRepository;
    }

    @Override
    public Collection<PollDTO> fetchAll() {
        Collection<PollDTO> result = new ArrayList<>();
        for (Poll poll : pollRepository.fetchAll()) {
            result.add(new PollDTO(poll));
        }
        return result;
    }

    @Override
    public Poll savePoll(Poll poll) {
        Poll result = pollRepository.save(poll);
        return result;
    }

    @Override
    public PollDTO getById(Long id) {
        Optional<Poll> byId = pollRepository.findById(id);
        if(byId.isPresent())
        return new PollDTO(byId.get());
        else return null;
    }
}
