package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03FrancitorraDiana.model.services.impl;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03FrancitorraDiana.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03FrancitorraDiana.model.services.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ClientFlowerServiceImpl implements FlowerService {
    @Autowired
    private WebClient webClient;
    public void create(FlowerDTO flowerDTO){

    }
    public void  update(FlowerDTO flowerDTO){

    }
    public void delete(Integer flowerID){

    }
    public FlowerDTO getOne(Integer flowerID){
        FlowerDTO flowerDTO = webClient
                .get()
                .uri("/getOne/{id}")
                .retrieve()
                .bodyToMono(FlowerDTO.class)
                .block();
        return flowerDTO;
    }
    public List<FlowerDTO> getAll(){
        Mono<List<FlowerDTO>> flowerListMono = webClient
                .get()
                .uri("/getAll")
                .retrieve()
                .bodyToFlux(FlowerDTO.class)
                .collectList();

        List<FlowerDTO> flowerList = flowerListMono.block();

        return  flowerList;
    }
}
