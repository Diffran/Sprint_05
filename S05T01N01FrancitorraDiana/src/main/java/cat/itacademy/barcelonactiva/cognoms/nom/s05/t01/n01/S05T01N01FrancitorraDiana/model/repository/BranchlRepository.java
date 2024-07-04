package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n01.S05T01N01FrancitorraDiana.model.repository;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n01.S05T01N01FrancitorraDiana.model.domain.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BranchlRepository extends JpaRepository<Branch, Integer> {
}
