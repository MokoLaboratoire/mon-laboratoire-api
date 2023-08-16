package com.moko.monlaboratoireapi.config;

import com.moko.monlaboratoireapi.models.LabUser;
import com.moko.monlaboratoireapi.repositories.LabUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class LabUserConfig {

    @Bean
    CommandLineRunner commentLineRuner(LabUserRepository labUserRepository) {
        return args -> {
            LabUser federico = new LabUser(
                "Federico",
                "desmoulin.federico@gmail.com",
                LocalDate.of(2000, Month.JANUARY, 5).toString(),
                29
            );

            labUserRepository.saveAll(List.of(federico));
        };
    }
}
