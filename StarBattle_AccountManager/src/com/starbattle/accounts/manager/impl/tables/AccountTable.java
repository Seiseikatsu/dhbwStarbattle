package com.starbattle.accounts.manager.impl.tables;

public enum AccountTable {

    NAME("NAME"), EMAIL("EMAIL"), PASSWORD("PASSWORD"), ACCOUNT_ID("ACCOUNT_ID");

    private String fieldName;

    private AccountTable(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public static String getTableName() {
        return "ACCOUNT";
    }
}
