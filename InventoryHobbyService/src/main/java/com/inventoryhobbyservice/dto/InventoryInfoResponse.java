package com.inventoryhobbyservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryInfoResponse {
    private Long id;
    private UUID serial_id;
    private Long userInventoryId;
    private Long hobbyInventoryId;
}
