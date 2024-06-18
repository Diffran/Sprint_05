package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n01.S05T01N01FrancitorraDiana.model.services;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n01.S05T01N01FrancitorraDiana.model.domain.Sucursal;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n01.S05T01N01FrancitorraDiana.model.dto.SucursalDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n01.S05T01N01FrancitorraDiana.model.repository.ISucursalRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class SucursalService implements ICRUDSucursal{
    private ISucursalRepository sucursalRepository;

    @Autowired
    public SucursalService(ISucursalRepository sucursalRepository){
        this.sucursalRepository=sucursalRepository;
    }

    private Sucursal toEntity(SucursalDTO sucursalDTO){
        if(sucursalDTO.getPk_BranchID()==null){
            return new Sucursal(sucursalDTO.getBranchName(), sucursalDTO.getBranchCountry());
        }
        return new Sucursal(sucursalDTO.getPk_BranchID(), sucursalDTO.getBranchName(), sucursalDTO.getBranchCountry());
    }
    private SucursalDTO toDTO(Sucursal sucursal){
        return  new SucursalDTO(sucursal.getPk_BranchID(), sucursal.getBranchName(), sucursal.getBranchCountry());
    }

    @Override
    public void create(SucursalDTO sucursalDTO) throws EntityExistsException{
        if(sucursalRepository.findById(sucursalDTO.getPk_BranchID()).isPresent()){
            throw new EntityExistsException("Create Sucursal failed: Invalid ID number: "+ sucursalDTO.getPk_BranchID()+
                    " ALREADY EXIST in database");
        }

        sucursalRepository.save(toEntity(sucursalDTO));
    }

    @Override
    public void update(SucursalDTO sucursalDTO) throws EntityNotFoundException{
        if(!sucursalRepository.findById(sucursalDTO.getPk_BranchID()).isPresent()){
            throw new EntityNotFoundException("Update Sucursal Failed: Invalid ID: "+ sucursalDTO.getPk_BranchID()+
                    " -> DOESN'T EXIST in DataBase");
        }

        sucursalRepository.save(toEntity(sucursalDTO));
    }

    @Override
    public void delete(Integer branchId) throws EntityNotFoundException{
        if(!sucursalRepository.findById(branchId).isPresent()){
            throw new EntityNotFoundException("Update Sucursal Failed: Invalid ID: "+ branchId+
                    " -> DOESN'T EXIST in DataBase");
        }

        sucursalRepository.deleteById(branchId);
    }

    @Override
    public SucursalDTO getOne(Integer branchId) throws  EntityNotFoundException{
        Sucursal branch =sucursalRepository.findById(branchId)
                .orElseThrow(() -> new NoResultException("Get One Sucursal Failed: Invalid ID: "+ branchId
                        +" -> DOESN'T EXIST in DataBase"));

        return toDTO(branch);
    }

    @Override
    public List<SucursalDTO> getAll(){
        List<Sucursal> branchList = sucursalRepository.findAll();

        return branchList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

}
