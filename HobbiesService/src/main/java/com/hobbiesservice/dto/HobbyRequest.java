package com.hobbiesservice.dto;

import com.hobbiesservice.domain.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HobbyRequest {
    private String name;
    private String describe;
    private Date duration;
    private String logoPath;
    private Type type;
    private Long author_id;
    private Integer rating;
}
