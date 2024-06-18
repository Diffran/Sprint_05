package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n01.S05T01N01FrancitorraDiana.model.services;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n01.S05T01N01FrancitorraDiana.model.dto.SucursalDTO;

import java.util.List;

public interface ICRUDSucursal {
    void create(SucursalDTO sucursalDTO);
    void update(SucursalDTO sucursalDTO);
    void delete(Integer sucursalID);
    SucursalDTO getOne(Integer sucursalID);
    List<SucursalDTO> getAll();
}
