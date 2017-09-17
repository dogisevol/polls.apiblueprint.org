package org.apiblueprint.polls.web;

import org.apiblueprint.polls.domain.Poll;
import org.apiblueprint.polls.service.PollService;
import org.apiblueprint.polls.web.model.PollDTO;
import org.apiblueprint.polls.web.model.PollModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;

@RestController
public class PollController {
    @Autowired
    private PollService pollService;

    @RequestMapping(value = "/questions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Transactional(readOnly = true)
    public Collection<PollDTO> questions() {
        return pollService.fetchAll();
    }

    @RequestMapping(value = "/questions", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PollDTO> question(@RequestBody PollModel pollModel, UriComponentsBuilder b) {
        Poll poll = pollService.savePoll(pollModel.toEntity());
        PollDTO pollDTO = pollService.getById(poll.getId());
        UriComponents uriComponents =
                b.path("/questions/{id}").buildAndExpand(pollDTO.getId());
        return ResponseEntity.created(uriComponents.toUri()).body(pollDTO);
    }
}
