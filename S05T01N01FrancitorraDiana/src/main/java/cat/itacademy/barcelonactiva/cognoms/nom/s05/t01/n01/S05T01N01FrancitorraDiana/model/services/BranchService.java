package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n01.S05T01N01FrancitorraDiana.model.services;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n01.S05T01N01FrancitorraDiana.model.domain.Branch;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n01.S05T01N01FrancitorraDiana.model.dto.BranchDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n01.S05T01N01FrancitorraDiana.model.repository.IBranchlRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class BranchService implements IBranchCRUD {
    private IBranchlRepository branchRepository;

    @Autowired
    public BranchService(IBranchlRepository branchRepository){
        this.branchRepository =branchRepository;
    }

    private Branch toEntity(BranchDTO branchDTO){
        if(branchDTO.getPk_BranchID()==null){
            return new Branch(branchDTO.getBranchName(), branchDTO.getBranchCountry());
        }
        return new Branch(branchDTO.getPk_BranchID(), branchDTO.getBranchName(), branchDTO.getBranchCountry());
    }
    private BranchDTO toDTO(Branch branch){
        return  new BranchDTO(branch.getPk_BranchID(), branch.getBranchName(), branch.getBranchCountry());
    }

    @Override
    public void create(BranchDTO branchDTO) throws EntityExistsException{
        if(branchRepository.findById(branchDTO.getPk_BranchID()).isPresent()){
            throw new EntityExistsException("Create Sucursal failed: Invalid ID number: "+ branchDTO.getPk_BranchID()+
                    " ALREADY EXIST in database");
        }

        branchRepository.save(toEntity(branchDTO));
    }

    @Override
    public void update(BranchDTO branchDTO) throws EntityNotFoundException{
        if(!branchRepository.findById(branchDTO.getPk_BranchID()).isPresent()){
            throw new EntityNotFoundException("Update Sucursal Failed: Invalid ID: "+ branchDTO.getPk_BranchID()+
                    " -> DOESN'T EXIST in DataBase");
        }

        branchRepository.save(toEntity(branchDTO));
    }

    @Override
    public void delete(Integer branchId) throws EntityNotFoundException{
        if(!branchRepository.findById(branchId).isPresent()){
            throw new EntityNotFoundException("Update Sucursal Failed: Invalid ID: "+ branchId+
                    " -> DOESN'T EXIST in DataBase");
        }

        branchRepository.deleteById(branchId);
    }

    @Override
    public BranchDTO getOne(Integer branchId) throws  EntityNotFoundException{
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new NoResultException("Get One Sucursal Failed: Invalid ID: "+ branchId
                        +" -> DOESN'T EXIST in DataBase"));

        return toDTO(branch);
    }

    @Override
    public List<BranchDTO> getAll(){
        List<Branch> branchList = branchRepository.findAll();

        return branchList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

}
