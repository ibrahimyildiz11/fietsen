package be.vdab.fietsen.repositories;

import be.vdab.fietsen.domain.GroepsCursus;
import be.vdab.fietsen.domain.IndividueleCursus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
@Import(JpaCursusRepository.class)
@Sql("/insertCursus.sql")
class JpaCursusRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String CURSUSSEN = "cursussen";
    private static final LocalDate EEN_DATUM = LocalDate.of(2019,1,1);
    private final JpaCursusRepository repository;

    JpaCursusRepositoryTest(JpaCursusRepository repository) {
        this.repository = repository;
    }
    private long idVanTestGroepsCursus() {
        return jdbcTemplate.queryForObject(
                "select id from cursussen where naam = 'testGroep'",Long.class);
    }
    private long idVanTestIndividueleCursus() {
        return jdbcTemplate.queryForObject(
                "select id from cursussen where naam = 'testIndividueel'", Long.class);
    }

    @Test
    void findGroepsCursusById() {
        assertThat(repository.findById(idVanTestGroepsCursus()))
                .containsInstanceOf(GroepsCursus.class)
                .hasValueSatisfying(
                        cursus -> assertThat(cursus.getNaam()).isEqualTo("testGroep"));
    }
    @Test
    void findIndividueleCursusById() {
        assertThat(repository.findById(idVanTestIndividueleCursus()))
                .containsInstanceOf(IndividueleCursus.class)
                .hasValueSatisfying(
                        cursus -> assertThat(cursus.getNaam()).isEqualTo("testIndividueel"));
    }
    @Test
    void findByOnbestaandeId() {
        assertThat(repository.findById(-1)).isEmpty();
    }
    @Test
    void createGroepsCursus() {
        var cursus = new GroepsCursus("testGroep2", EEN_DATUM, EEN_DATUM);
        repository.create(cursus);
        assertThat(countRowsInTableWhere(CURSUSSEN,
                "id = '" + cursus.getId() + "'")).isOne();
    }
    @Test
    void createIndividueleCursus() {
        var cursus = new IndividueleCursus("testIndividueel2", 7);
        repository.create(cursus);
        assertThat(countRowsInTableWhere(CURSUSSEN,
                "id = '" + cursus.getId() + "'")).isOne();
    }
}
