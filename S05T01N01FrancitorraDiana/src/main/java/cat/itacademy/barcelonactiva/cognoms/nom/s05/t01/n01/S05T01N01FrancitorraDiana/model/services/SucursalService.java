package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n01.S05T01N01FrancitorraDiana.model.services;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n01.S05T01N01FrancitorraDiana.model.domain.Sucursal;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n01.S05T01N01FrancitorraDiana.model.dto.SucursalDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n01.S05T01N01FrancitorraDiana.model.repository.ISucursalRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SucursalService {
    private ISucursalRepository sucursalRepository;

    @Autowired
    public SucursalService(ISucursalRepository sucursalRepository){
        this.sucursalRepository=sucursalRepository;
    }

    public SucursalDTO createSucursal(SucursalDTO sucursalDTO) throws EntityExistsException{

        if(sucursalRepository.findById(sucursalDTO.getPk_SucursalID()).isPresent()){
            throw new EntityExistsException("Create Sucursal failed: Invalid ID number: "+ sucursalDTO.getPk_SucursalID()+
                    " ALREADY EXIST in database");
        }

        //convertir aixo a metode toEntity i toDTO, crec que no cal que retorni DTO al ser tHymenoseque...
        //crear
        Sucursal sucursal = new Sucursal();
        sucursal.setPk_SucursalID(sucursalDTO.getPk_SucursalID());
        sucursal.setNomSucursal(sucursalDTO.getNomSucursal());
        sucursal.setPaisSucursal(sucursalDTO.getPaisSucursal());
        sucursalRepository.save(sucursal);

        return sucursalDTO;
    }

    public SucursalDTO updateSucursal(SucursalDTO sucursalDTO) throws EntityNotFoundException{
        if(!sucursalRepository.findById(sucursalDTO.getPk_SucursalID()).isPresent()){
            throw new EntityNotFoundException("Update Sucursal Failed: Invalid ID: "+ sucursalDTO.getPk_SucursalID()+
                    " -> DOESN'T EXIST in DataBase");
        }

        Sucursal sucursal = new Sucursal();
        sucursal.setPk_SucursalID(sucursalDTO.getPk_SucursalID());
        sucursal.setNomSucursal(sucursalDTO.getNomSucursal());
        sucursal.setPaisSucursal(sucursalDTO.getPaisSucursal());
        sucursalRepository.save(sucursal);

        return sucursalDTO;
    }

    public void deleteFruit(Integer sucursalID) throws EntityNotFoundException{
        if(!sucursalRepository.findById(sucursalID).isPresent()){
            throw new EntityNotFoundException("Update Sucursal Failed: Invalid ID: "+ sucursalID+
                    " -> DOESN'T EXIST in DataBase");
        }

        sucursalRepository.deleteById(sucursalID);
    }

    public SucursalDTO getOneSucursal(Integer sucursalID) throws  EntityNotFoundException{
        Sucursal sucursal=sucursalRepository.findById(sucursalID)
                .orElseThrow(() -> new EntityNotFoundException("Get One Sucursal Failed: Invalid ID: "+ sucursalID
                        +" -> DOESN'T EXIST in DataBase"));

        SucursalDTO sucursalDTO = new SucursalDTO();
        sucursalDTO.setPk_SucursalID(sucursal.getPk_SucursalID());
        sucursalDTO.setNomSucursal(sucursal.getNomSucursal());
        sucursalDTO.setPaisSucursal(sucursal.getPaisSucursal());

        return  sucursalDTO;
    }

    public List<SucursalDTO> getAllSucursals(){

    }

}
