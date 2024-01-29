package org.sid.repository;

import org.sid.entities.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ServerRepository extends JpaRepository<Server, Long> {
    @Query("SELECT s FROM Server s WHERE s.ipAddress = :x")
    Server findIpAddress(@Param("x") String ipAddress);
}