package com.inventoryhobbyservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HobbyResponse {
    private Long id;
    private String name;
    private String describe;
    private Date duration;
    private String logoPath;
    private String type;
    private Long author_id;
    private Integer rating;
    private Date created;
    private Date updated;

}
