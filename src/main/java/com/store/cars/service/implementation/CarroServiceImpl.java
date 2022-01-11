package com.store.cars.service.implementation;

import com.store.cars.entity.Carro;
import com.store.cars.repository.CarroRepository;
import com.store.cars.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroServiceImpl implements CarroService {

    @Autowired
    private CarroRepository carroRepository;

    @Override
    public Carro cadastrarCarro(Carro carro) {

        System.out.println("Novo Carro:\n" + carro.toString());
        return carroRepository.save(carro);

    }

    @Override
    public Optional<Carro> buscarCarroPorId(Integer id) {
        return carroRepository.findById(id);
    }

    @Override
    public List<Carro> buscarCarros(Integer start, Integer limit) {
        return carroRepository.buscaCarros(start, limit);
    }

}
