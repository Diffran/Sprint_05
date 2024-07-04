package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.S05T01N02FrancitorraDiana.model.repository;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.S05T01N02FrancitorraDiana.model.domain.Flower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowerIRepository extends JpaRepository<Flower, Integer> {
}
