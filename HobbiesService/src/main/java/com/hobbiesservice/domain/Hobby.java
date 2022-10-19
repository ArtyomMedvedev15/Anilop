package com.hobbiesservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private Long typeId;
    private Long author_id;
    private Integer rating;

}
