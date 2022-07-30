package learn.field_agent.controllers;


import learn.field_agent.domain.SecurityClearanceService;
import learn.field_agent.models.Agency;
import learn.field_agent.models.SecurityClearance;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/security/clearance")
public class SecurityClearanceController {

    private final SecurityClearanceService securityClearanceService;

    public SecurityClearanceController(SecurityClearanceService securityClearanceService) {
        this.securityClearanceService = securityClearanceService;
    }

    @GetMapping
    public List<SecurityClearance> findAll() {
        return securityClearanceService.findAll();
    }

    @GetMapping("/{securityClearanceId}")
    public ResponseEntity<SecurityClearance> findById(@PathVariable int securityClearanceId) {
        SecurityClearance securityClearance = securityClearanceService.findById(securityClearanceId);
        if (securityClearance == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(securityClearance);
    }
}
