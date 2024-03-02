package org.psa.RealEstate.service.impl;
import org.psa.RealEstate.entities.Agent;
import org.psa.RealEstate.exception.AgentNotFoundException;
import org.psa.RealEstate.payload.AgentDto;
import org.psa.RealEstate.repository.AgentRepository;
import org.psa.RealEstate.service.AgentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgentServiceImpl  implements AgentService {
    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private ModelMapper mapper;

    public Agent mapToEntity(AgentDto agentDto){
        /*Agent agent = new Agent();
        agent.setId(agentDto.getId());
        agent.setName(agentDto.getName());
        agent.setEmail(agentDto.getEmail());
        agent.setPhoneNumber(agentDto.getPhoneNumber());
        agent.setListedProperties();
        return agent;*/
        Agent agent=mapper.map(agentDto,Agent.class);
        return agent;
    }
    public AgentDto mapToDto(Agent agent){
        /*AgentDto agentDto = new AgentDto();
        agentDto.setId(agent.getId());
        agentDto.setName(agent.getName());
        agentDto.setEmail(agent.getEmail());
        agentDto.setPhoneNumber(agent.getPhoneNumber());
        return agentDto;*/
        AgentDto dto=mapper.map(agent,AgentDto.class);
        return dto;
    }
    @Override
    public AgentDto saveAgent(AgentDto agentDto) {
        Agent agent=mapToEntity(agentDto);
        Agent agentEntity=agentRepository.save(agent);
        AgentDto dto=mapToDto(agentEntity);
        return dto;
    }

    @Override
    public AgentDto updateAgent(AgentDto agentDto, long id) {
        Agent agent=agentRepository.findById(id).orElseThrow(
                ()-> new AgentNotFoundException("Agent Not Found With This Id: ")
        );
          agent.setName(agentDto.getName());
          agent.setEmail(agentDto.getEmail());
          agent.setPhoneNumber(agentDto.getPhoneNumber());
          Agent newAgent=agentRepository.save(agent);
        return mapToDto(newAgent);
    }

    @Override
    public AgentDto findAgentById(long id) {
        Agent agent=agentRepository.findById(id).orElseThrow(
                ()-> new AgentNotFoundException("Invalid Agent Id: ")
        );
        AgentDto agentDto=mapToDto(agent);
        return agentDto;
    }

    @Override
    public List<AgentDto> getAllAgent() {
        List<Agent> agents=agentRepository.findAll();
        List<AgentDto> dto=agents.stream().map(agent -> mapToDto(agent))
                .collect(Collectors.toList());
        return dto;
    }

    @Override
    public void deleteAgent(long id) {
        Agent agent=agentRepository.findById(id).orElseThrow(
                ()-> new AgentNotFoundException("Agent Does Not Exists with this id: ")
        );
        agentRepository.delete(agent);

    }
}
