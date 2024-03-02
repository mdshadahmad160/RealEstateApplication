package org.psa.RealEstate.service;
import org.psa.RealEstate.payload.AgentDto;

import java.util.List;

public interface AgentService {

    AgentDto saveAgent(AgentDto agentDto);

    AgentDto updateAgent(AgentDto agentDto, long id);

    AgentDto findAgentById(long id);

    List<AgentDto> getAllAgent();

    void deleteAgent(long id);
}
