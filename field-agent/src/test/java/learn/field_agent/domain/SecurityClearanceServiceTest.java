package learn.field_agent.domain;

import learn.field_agent.data.AgencyRepository;
import learn.field_agent.data.SecurityClearanceRepository;
import learn.field_agent.models.Agency;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SecurityClearanceServiceTest {

    @Autowired
    SecurityClearanceService securityClearanceService;

    @MockBean
    SecurityClearanceRepository securityClearanceRepository;

    @Test
    void shouldFindAllSecurityClearances() {

    }

    @Test
    void findById() {
    }

    @Test
    void shouldAddNewSecurityClearance() {

        SecurityClearance clearance = new SecurityClearance(0, "Security Clearance Test");
        SecurityClearance mockOut = new SecurityClearance(5, "Security Clearance Test");

        when(securityClearanceRepository.add(clearance)).thenReturn(mockOut);

        Result<SecurityClearance> result = securityClearanceService.add(clearance);
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(mockOut, result.getPayload());
    }

    @Test
    void shouldNotAddSecurityClearanceWithoutName() {
        Result<SecurityClearance> result = securityClearanceService.add(
                new SecurityClearance(0, ""));

        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());
    }

    @Test
    void shouldNotAddDuplicateSecurityClearance() {
        Result<SecurityClearance> result = securityClearanceService.add(
                new SecurityClearance(0, ""));

        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());
    }
}