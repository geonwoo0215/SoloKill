package com.geonwoo.solokill.domain.point.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geonwoo.solokill.domain.point.model.Point;

public interface PointRepository extends JpaRepository<Point, Long> {

}
