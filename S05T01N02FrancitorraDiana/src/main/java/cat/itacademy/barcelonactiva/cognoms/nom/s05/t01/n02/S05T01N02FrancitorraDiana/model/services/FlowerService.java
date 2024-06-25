package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.S05T01N02FrancitorraDiana.model.services;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.S05T01N02FrancitorraDiana.model.dto.FlowerDTO;

import java.util.List;

public interface FlowerService {
    void create(FlowerDTO flowerDTO);
    void update(FlowerDTO flowerDTO);
    void delete(Integer flowerID);
    FlowerDTO getOne(Integer flowerID);
    List<FlowerDTO> getAll();
}
