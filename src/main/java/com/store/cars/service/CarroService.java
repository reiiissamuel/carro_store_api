package com.store.cars.service;


import com.store.cars.entity.Carro;

import java.util.List;
import java.util.Optional;

public interface CarroService {

    Carro cadastrarCarro(Carro carro);

    Optional<Carro> buscarCarroPorId(Integer id);

    List<Carro> buscarCarros(Integer start, Integer limit);
}
