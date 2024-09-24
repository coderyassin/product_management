package org.alten.shared.enums;

public enum InventoryStatus {
    INSTOCK("Product is in stock and available"),
    LOWSTOCK("Product is in stock but the level is low"),
    OUTOFSTOCK("Product is out of stock");

    private final String statusDescription;

    InventoryStatus(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String getStatusDescription() {
        return statusDescription;
    }
}
