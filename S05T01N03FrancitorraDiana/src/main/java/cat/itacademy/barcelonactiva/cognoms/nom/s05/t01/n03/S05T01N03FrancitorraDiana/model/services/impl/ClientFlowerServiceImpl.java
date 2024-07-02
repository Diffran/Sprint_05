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
        webClient
                .post()
                .uri("/add")
                .bodyValue(flowerDTO)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
    public void  update(FlowerDTO flowerDTO){
        webClient
                .put()
                .uri("/update")
                .bodyValue(flowerDTO)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
    public void delete(Integer flowerID){
        webClient
                .delete()
                .uri("/delete/{id}", flowerID)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
    public FlowerDTO getOne(Integer flowerID){
        return  webClient
                .get()
                .uri("/getOne/{id}", flowerID)
                .retrieve()
                .bodyToMono(FlowerDTO.class)
                .block();
    }
    public List<FlowerDTO> getAll(){
        Mono<List<FlowerDTO>> flowerListMono = webClient
                .get()
                .uri("/getAll")
                .retrieve()
                .bodyToFlux(FlowerDTO.class)
                .collectList();

        return  flowerListMono.block();
    }
}
