package org.psa.RealEstate.controller;
import org.psa.RealEstate.payload.AgentDto;
import org.psa.RealEstate.payload.ApiResponse;
import org.psa.RealEstate.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/agents")
public class AgentController {
    @Autowired

    private AgentService agentService;
    @PostMapping
    public ResponseEntity<AgentDto> saveAgent(@RequestBody AgentDto agentDto){
        AgentDto dto=agentService.saveAgent(agentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @PutMapping("/{agentId}")
    public ResponseEntity<AgentDto> updateAgent(@RequestBody AgentDto agentDto, @PathVariable(value = "agentId") long id){
        AgentDto dto=agentService.updateAgent(agentDto,id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<AgentDto>> getAllAgent(){
        List<AgentDto> dto=agentService.getAllAgent();
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @GetMapping("/{agentId}")
    public ResponseEntity<AgentDto> findAgentById(@PathVariable(value = "agentId") long id){
        AgentDto dto=agentService.findAgentById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @DeleteMapping("/{agentId}")
    public ResponseEntity<ApiResponse> deleteAgent(@PathVariable(value = "agentId") long id){
        agentService.deleteAgent(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Agent Deleted Successfully: ",true),HttpStatus.OK);
    }
}
