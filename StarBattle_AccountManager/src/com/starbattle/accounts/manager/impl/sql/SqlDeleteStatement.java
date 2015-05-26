package com.starbattle.accounts.manager.impl.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.starbattle.accounts.manager.impl.DatabaseControl;

public class SqlDeleteStatement extends SqlStatement {

    public SqlDeleteStatement() {
        statement = "DELETE ";
    }

    public void from(Class<? extends Enum> table) throws SQLException {
        statement += getTableName(table);
    }

    public void where(Enum... fieldNames) throws SQLException {

        int count = fieldNames.length;
        statement += " WHERE ";
        for (int i = 0; i < count; i++) {
            String field = getFieldName(fieldNames[i]);
            statement += field + " = ?";
            separate(i, count, "AND ");
        }
    }

    public void values(Object... values) {
        this.values = values;
    }

    @Override
    public ResultSet execute(DatabaseControl databaseControl) throws SQLException {
        Connection connection = databaseControl.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        for (int i = 0; i < values.length; i++) {
            preparedStatement.setObject(1 + i, values[i]);
        }
        preparedStatement.executeUpdate();
        System.out.println(preparedStatement.toString());
        return preparedStatement.getResultSet();

    }

}
