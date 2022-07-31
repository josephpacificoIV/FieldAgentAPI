package learn.field_agent.controllers;


import learn.field_agent.domain.AgentService;
import learn.field_agent.domain.AliasService;
import learn.field_agent.models.Agent;
import learn.field_agent.models.Alias;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api")
public class AliasController {

    private final AliasService service;
    private final AgentService agentService;

    public AliasController(AliasService service, AgentService agentService) {
        this.service = service;
        this.agentService = agentService;
    }

    @GetMapping("/agent/{agentId}")
    public ResponseEntity<Agent> findById(@PathVariable int agentId) {
        Agent agent = agentService.findById(agentId);
        if (agent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(agent);
    }


}
