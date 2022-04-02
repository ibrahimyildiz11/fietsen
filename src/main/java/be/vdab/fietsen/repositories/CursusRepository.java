package be.vdab.fietsen.repositories;

import be.vdab.fietsen.domain.Cursus;

import java.util.Optional;

public interface CursusRepository {
    Optional<Cursus> findById(long id);
    void create(Cursus cursus);
}
