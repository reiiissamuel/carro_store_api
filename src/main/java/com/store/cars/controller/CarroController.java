package com.store.cars.controller;

import com.store.cars.dto.Response;
import com.store.cars.entity.Carro;
import com.store.cars.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @GetMapping()
    public ResponseEntity<Response<List>> getCarros(
            @RequestParam(name = "limit", defaultValue = "5") Integer limit,
            @RequestParam(name = "start", defaultValue = "0") Integer start
    ){
        List<Carro> carros =
                this.carroService.buscarCarros(start, limit);

        return Response.status(HttpStatus.OK.value(), List.class)
                .message(HttpStatus.OK.getReasonPhrase())
                .data(carros).build();

    }

    @GetMapping("/{carroId}")
    public ResponseEntity<Response<Carro>> getCarroById(
            @PathVariable("carroId") Integer carroId
    ){
        Optional<Carro> carro =
                this.carroService.buscarCarroPorId(carroId);

        if(!carro.isPresent()) {
            return Response.status(HttpStatus.NOT_FOUND.value(), Carro.class)
                    .message(HttpStatus.NOT_FOUND.getReasonPhrase()).build();
        }

        return Response.status(HttpStatus.OK.value(), Carro.class)
                .message(HttpStatus.OK.getReasonPhrase())
                .data(carro.get()).build();

    }

    @PostMapping()
    public ResponseEntity<Response<Carro>> cadastraCarro(@Validated @RequestBody Carro carro)  {

        Carro result = carroService.cadastrarCarro(carro);

        return Response.status(HttpStatus.CREATED.value(), Carro.class)
                .message(HttpStatus.CREATED.getReasonPhrase())
                .data(result).build();
    }

}
