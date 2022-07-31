package learn.field_agent.domain;

import learn.field_agent.data.AliasRepository;
import learn.field_agent.data.LocationRepository;
import learn.field_agent.models.Agent;
import learn.field_agent.models.Alias;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AliasServiceTest {

    @Autowired
    AliasService service;

    @MockBean
    AliasRepository repository;

    @Test
    void shouldFindById() {
        Alias expected = new Alias();
        expected.setAliasId(1);
        expected.setName("Hazel C Sauven");
        expected.setPersona("Mr. Potato");
        expected.setAgent_id(1);

        when(repository.findById(1)).thenReturn(expected);
        Alias actual = service.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddAliasWithoutPersonaIfAgentAlreadyHasAlias() {

        List<Alias> aliases = List.of(
                new Alias("Agent Name", "Mrs. Puff", 1),
                new Alias("Agent 2", "Mr. Krabs", 2)
        );

        Alias alias = new Alias("Agent Name", "", 1);
        when(repository.findById(alias.getAgent_id())).thenReturn(aliases.get(1));

        Result<Alias> result = service.add(alias);

        // 6. Configure the per-test behavior for mock PetRepository.

        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());

    }

    @Test
    void shouldAddAliasWithPersonaIfAgentAlreadyHasAlias() {

        List<Alias> aliases = List.of(
                new Alias("Agent Name", "Mrs. Puff", 1),
                new Alias("Agent 2", "Mr. Krabs", 2)
        );

        Alias alias = new Alias("Agent Name", "new persona", 1);
        when(repository.findById(alias.getAgent_id())).thenReturn(aliases.get(1));

        Result<Alias> result = service.add(alias);
        
        // 6. Configure the per-test behavior for mock PetRepository.

        assertEquals(ResultType.SUCCESS, result.getType());
        assertNull(result.getPayload());

    }




}