package learn.field_agent.data;

import learn.field_agent.models.Alias;
import learn.field_agent.models.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AliasJdbcTemplateRepositoryTest {

    final static int NEXT_ALIAS_ID = 3;

    @Autowired
    AliasJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAlias() {
        Alias actual = repository.findById(1);
        assertNotNull(actual);
        assertEquals(1, actual.getAliasId());
        assertEquals("Hazel C Sauven", actual.getName());
    }

    @Test
    void shouldAddNewAlias() {
        Alias alias = new Alias();
        alias.setName("Hazel C Sauven");
        alias.setPersona("Mr Mr Potato Head");
        alias.setAgent_id(1);
        alias.setAliasId(3);

        Alias actual = repository.add(alias);
        assertNotNull(actual);
        assertEquals(NEXT_ALIAS_ID, actual.getAliasId());
    }


}