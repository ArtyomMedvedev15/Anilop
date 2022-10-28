package com.inventoryhobbyservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryInfoRequest {
    private Long userInventoryId;

    private Long hobbyInventoryId;
}
