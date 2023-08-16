package com.moko.monlaboratoireapi.repositories;

import com.moko.monlaboratoireapi.models.LabUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LabUserRepository extends JpaRepository<LabUser, UUID> {

    Optional<LabUser> findLabUserByEmail(String email);
}
