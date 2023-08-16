package com.moko.monlaboratoireapi.models;

import jakarta.persistence.*;

@Entity
@Table
public class Language {

    @Id
    @SequenceGenerator(
            name = "language_sequence",
            sequenceName = "language_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "language_sequence"
    )
    private Long id;
}
