package cg.codegym.minitest.springboot2.Repository;

import cg.codegym.minitest.springboot2.Model.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepository extends JpaRepository<Notes, Long> {
}
