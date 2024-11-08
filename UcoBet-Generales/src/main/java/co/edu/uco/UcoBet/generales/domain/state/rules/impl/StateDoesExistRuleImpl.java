package co.edu.uco.UcoBet.generales.domain.state.rules.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import co.edu.uco.UcoBet.generales.application.secondaryports.repository.StateRepository;
import co.edu.uco.UcoBet.generales.domain.state.exception.StateDoesNotExistException;
import co.edu.uco.UcoBet.generales.domain.state.rules.StateDoesExistRule;
import co.edu.uco.UcoBet.generales.infraestructure.secondaryadapters.redis.MessageCatalogService;

@Service
public class StateDoesExistRuleImpl implements StateDoesExistRule {
	private StateRepository stateRepository;

	private MessageCatalogService messageCatalogService;
	
	public StateDoesExistRuleImpl(StateRepository stateRepository,MessageCatalogService messageCatalogService) {
		this.stateRepository = stateRepository;
		this.messageCatalogService=messageCatalogService;
	}

	@Override
	public void execute(UUID data) {

		if (!stateRepository.existsById(data)) {
			throw StateDoesNotExistException.create(messageCatalogService);
		}

	}

}