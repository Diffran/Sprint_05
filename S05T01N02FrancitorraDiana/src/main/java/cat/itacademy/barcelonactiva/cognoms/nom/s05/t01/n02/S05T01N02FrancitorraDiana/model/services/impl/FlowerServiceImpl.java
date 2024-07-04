package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.S05T01N02FrancitorraDiana.model.services.impl;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.S05T01N02FrancitorraDiana.model.domain.Flower;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.S05T01N02FrancitorraDiana.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.S05T01N02FrancitorraDiana.model.exceptions.CountryNotFoundException;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.S05T01N02FrancitorraDiana.model.repository.FlowerIRepository;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.S05T01N02FrancitorraDiana.model.services.FlowerService;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.S05T01N02FrancitorraDiana.model.util.Countries;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlowerServiceImpl implements FlowerService {
    private FlowerIRepository flowerRepository;

    @Autowired
    public FlowerServiceImpl(FlowerIRepository flowerRepository) {
        this.flowerRepository = flowerRepository;
    }

    private Flower toEntity(FlowerDTO flowerDTO){
        if(flowerDTO.getPk_FlowerID()==null){

            return new Flower(flowerDTO.getFlowerName(), validCountry(flowerDTO.getFlowerCountry()));
        }
        return new Flower(flowerDTO.getPk_FlowerID(), flowerDTO.getFlowerName(), validCountry(flowerDTO.getFlowerCountry()));
    }

    private FlowerDTO toDTO(Flower flower){
        return  new FlowerDTO(flower.getPk_FlowerID(), flower.getFlowerName(), flower.getFlowerCountry());
    }


    @Override
    public void create(FlowerDTO flowerDTO){
        flowerRepository.save(toEntity(flowerDTO));
    }

    @Override
    public void update(FlowerDTO flowerDTO) {
        flowerRepository.findById(flowerDTO.getPk_FlowerID())
                .orElseThrow(() -> new EntityNotFoundException("Update Sucursal Failed: Invalid ID: "+ flowerDTO.getPk_FlowerID()
                        +" -> DOESN'T EXIST in DataBase"));

        flowerRepository.save(toEntity(flowerDTO));
    }

    @Override
    public void delete(Integer flowerId) {
        if(!flowerRepository.findById(flowerId).isPresent()){
            throw new EntityNotFoundException("Update Sucursal Failed: Invalid ID: "+ flowerId+
                    " -> DOESN'T EXIST in DataBase");
        }

        flowerRepository.deleteById(flowerId);
    }

    @Override
    public FlowerDTO getOne(Integer flowerId) {
        Flower flower = flowerRepository.findById(flowerId)
                .orElseThrow(() -> new NoResultException("Get One Sucursal Failed: Invalid ID: "+ flowerId
                        +" -> DOESN'T EXIST in DataBase"));

        return toDTO(flower);
    }

    @Override
    public List<FlowerDTO> getAll(){
        List<Flower> flowerList = flowerRepository.findAll();

        return flowerList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private static String validCountry(String country){
        return Countries.ALL_COUNTRIES.stream()
                .filter(c -> c.equalsIgnoreCase(country))
                .findFirst()
                .orElseThrow(() -> new CountryNotFoundException("Not Valid Country: " + country
                        + " -PLEASE ADD A VALID COUNTRYY"));
    }
}
