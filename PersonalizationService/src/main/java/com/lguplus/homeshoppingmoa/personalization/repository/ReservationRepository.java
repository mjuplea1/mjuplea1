package com.lguplus.homeshoppingmoa.personalization.repository;

import com.lguplus.homeshoppingmoa.personalization.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByMainProductIdIn(List<Long> mainProductIdList);
}
