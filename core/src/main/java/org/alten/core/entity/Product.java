package org.alten.core.entity;

import jakarta.persistence.*;
import lombok.*;
import org.alten.core.listener.ProductListener;
import org.alten.shared.enums.InventoryStatus;

@Entity
@EntityListeners(ProductListener.class)
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Product extends AbstractEntity{
    private String code;

    private String name;

    private String description;

    private String image;

    private String category;

    private Double price;

    private Integer quantity;

    @Column(name = "internal_reference")
    private String internalReference;

    @Column(name = "shell_id")
    private Long shellId;

    @Column(name = "inventory_status")
    @Enumerated(EnumType.STRING)
    private InventoryStatus inventoryStatus;

    private Double rating;
}
