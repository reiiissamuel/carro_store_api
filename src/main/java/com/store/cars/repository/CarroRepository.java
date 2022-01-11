package com.store.cars.repository;

import com.store.cars.entity.Carro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarroRepository extends CrudRepository<Carro, Integer> {

    String BUSCA_CARROS_QUERY =
            "SELECT DISTINCT ON (id) * " +
                    "FROM {h-schema}carro " +
                    "ORDER BY data_cadastro desc, id desc " +
                    "LIMIT :limit OFFSET :start";

    @Query(value = BUSCA_CARROS_QUERY, nativeQuery = true)
    List<Carro> buscaCarros(
            @Param("start") Integer start,
            @Param("limit") Integer limit
    );

}
