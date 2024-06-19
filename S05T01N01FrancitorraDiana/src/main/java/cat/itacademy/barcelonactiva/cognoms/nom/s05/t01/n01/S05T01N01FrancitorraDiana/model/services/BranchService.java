package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n01.S05T01N01FrancitorraDiana.model.services;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n01.S05T01N01FrancitorraDiana.model.dto.BranchDTO;

import java.util.List;

public interface BranchService {
    void create(BranchDTO sucursalDTO);
    void update(BranchDTO sucursalDTO);
    void delete(Integer sucursalID);
    BranchDTO getOne(Integer sucursalID);
    List<BranchDTO> getAll();
}
