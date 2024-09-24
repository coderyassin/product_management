package org.alten.business.http.request;

import lombok.*;
import org.alten.shared.enums.InventoryStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UpdateProductRequest {
    private String code;

    private String name;

    private String description;

    private String image;

    private String category;

    private Double price;

    private Integer quantity;

    private String internalReference;

    private Long shellId;

    private InventoryStatus inventoryStatus;

    private Double rating;
}
