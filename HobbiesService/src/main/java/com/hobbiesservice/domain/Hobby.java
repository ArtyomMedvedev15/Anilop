package com.hobbiesservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hobby extends BaseDomain {

    private String name;
    private String describe;
    private Date duration;
    private String logoPath;
    private Type type;
    private Long author_id;
    @ColumnDefault("0")
    private Integer rating;

}
