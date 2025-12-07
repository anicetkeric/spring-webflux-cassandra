package com.boottech.springwebfluxcassandra.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("book")
public class Book {
    @PrimaryKey
    private UUID id;
    private String title;
    private int page;
    private String isbn;
    private String description;

    private String language;

    private LocalDate publication;
    private double price;
}
