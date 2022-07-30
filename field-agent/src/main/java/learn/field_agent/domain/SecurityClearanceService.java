package learn.field_agent.domain;

import learn.field_agent.data.SecurityClearanceJdbcTemplateRepository;
import learn.field_agent.data.SecurityClearanceRepository;
import learn.field_agent.models.Agent;
import learn.field_agent.models.SecurityClearance;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityClearanceService {

    private final SecurityClearanceRepository securityClearanceRepository;


    public SecurityClearanceService(SecurityClearanceRepository securityClearanceRepository) {
        this.securityClearanceRepository = securityClearanceRepository;
    }

    public List<SecurityClearance> findAll() {
        return securityClearanceRepository.findAll();
    }

    public SecurityClearance findById(int agentId) {
        return securityClearanceRepository.findById(agentId);
    }
}
