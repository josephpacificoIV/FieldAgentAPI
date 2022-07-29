package learn.field_agent.domain;

import learn.field_agent.data.SecurityClearanceJdbcTemplateRepository;
import learn.field_agent.models.Agent;
import learn.field_agent.models.SecurityClearance;

import java.util.List;

public class SecurityClearanceService {

    private final SecurityClearanceJdbcTemplateRepository repository;


    public SecurityClearanceService(SecurityClearanceJdbcTemplateRepository repository) {
        this.repository = repository;
    }

    public List<SecurityClearance> findAll() {
        return repository.findAll();
    }

    public SecurityClearance findById(int agentId) {
        return repository.findById(agentId);
    }
}
