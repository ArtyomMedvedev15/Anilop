package com.authorsservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author extends BaseDomain {

    private String nameAuthor;
    private String describeAuthor;
    @ColumnDefault("0")
    private Integer rating;
    private String logoPath;

}
