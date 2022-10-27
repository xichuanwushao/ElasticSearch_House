package com.lcz.repository;

import com.lcz.entity.SubwayStation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface SubwayStationRepository extends CrudRepository<SubwayStation, Long> {
    List<SubwayStation> findAllBySubwayId(Long subwayId);
}
