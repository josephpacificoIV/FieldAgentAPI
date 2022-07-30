package learn.field_agent.domain;

import learn.field_agent.data.SecurityClearanceJdbcTemplateRepository;
import learn.field_agent.data.SecurityClearanceRepository;
import learn.field_agent.models.Agent;
import learn.field_agent.models.SecurityClearance;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    public Result<SecurityClearance> add(SecurityClearance securityClearance) {
        Result<SecurityClearance> result = validate(securityClearance);
        if (result.getType() != ResultType.SUCCESS) {
            return result;
        }

        SecurityClearance p = securityClearanceRepository.add(securityClearance);
        result.setPayload(p);
        return result;
    }

    private Result<SecurityClearance> validate(SecurityClearance securityClearance) {
        Result<SecurityClearance> result = new Result<>();

        if (securityClearance == null) {
            result.addMessage("Security Clearance cannot be null", ResultType.INVALID);
            return result;
        }

        if (securityClearance.getName() == null || securityClearance.getName().trim().length() == 0) {
            result.addMessage("Security Clearance name is required", ResultType.INVALID);
        }

        if (result.isSuccess()) {
            List<SecurityClearance> existingSecurityClearances = securityClearanceRepository.findAll();

            for (SecurityClearance existingSecurityClearance : existingSecurityClearances) {
                // If an existing security clearance was found for the provided **name** values
                // add an error message if the id values don't match (i.e. they're not the same record).
                if (existingSecurityClearance.getSecurityClearanceId() != securityClearance.getSecurityClearanceId() &&
                        Objects.equals(existingSecurityClearance.getName(), securityClearance.getName())) {
                    result.addMessage("Security Clearance name must be unique.", ResultType.INVALID);
                }
            }
        }



        return result;
    }
}
