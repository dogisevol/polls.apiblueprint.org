package org.apiblueprint.polls.service;

import org.apiblueprint.polls.domain.Choice;
import org.springframework.data.repository.CrudRepository;

interface ChoiceRepository extends CrudRepository<Choice, Long> {
}
