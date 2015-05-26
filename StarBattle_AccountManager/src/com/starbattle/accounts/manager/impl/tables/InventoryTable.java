package com.starbattle.accounts.manager.impl.tables;

public enum InventoryTable {
    PLAYER_ID("player_id"), ITEM_ID("item_id");

    private String fieldName;

    private InventoryTable(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public static String getTableName() {
        return "INVENTAR";
    }
}
