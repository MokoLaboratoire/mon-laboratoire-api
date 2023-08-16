package com.moko.monlaboratoireapi.services;

import com.moko.monlaboratoireapi.models.LabUser;
import com.moko.monlaboratoireapi.repositories.LabUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class LabUserService {

    private final LabUserRepository labUserRepository;

    @Autowired
    public LabUserService(LabUserRepository labUserRepository) {
        this.labUserRepository = labUserRepository;
    }

    public List<LabUser> getUsers() {
        return labUserRepository.findAll();
    }

    public void addNewUser(LabUser lab_user) {
        Optional<LabUser> labUserOptional = labUserRepository.findLabUserByEmail(lab_user.getEmail());
        if(labUserOptional.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        labUserRepository.save(lab_user);
     }

     public void deleteLabUser(UUID lab_user_id) {
        boolean labUserExists = labUserRepository.existsById(lab_user_id);
         if(!labUserExists) {
             throw new IllegalStateException("User doesn't exist");
         }
         labUserRepository.deleteById(lab_user_id);
     }

     @Transactional
     public void updateLabUser(UUID lab_user_id, String name, String email) {
         LabUser labUser = labUserRepository.findById(lab_user_id).orElseThrow(() -> new IllegalStateException("User doesn't exist"));
         if(name != null && name.length() > 0 && !Objects.equals(labUser.getName(), name)) {
            labUser.setName(name);
         }
         if(email != null && email.length() > 0 && !Objects.equals(labUser.getEmail(), email)) {
             labUser.setEmail(email);
         }
     }
}
