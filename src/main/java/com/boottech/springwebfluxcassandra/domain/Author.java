package com.boottech.springwebfluxcassandra.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("author")
public class Author {

    @PrimaryKey
    private UUID id;

    private String lastname;

    private String middleName;

    private String firstname;
}
