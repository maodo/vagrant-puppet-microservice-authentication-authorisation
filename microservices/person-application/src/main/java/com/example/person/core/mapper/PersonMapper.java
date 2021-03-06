package com.example.person.core.mapper;

import com.example.person.core.Person;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements ResultSetMapper<Person>
{
    public Person map(int index, ResultSet resultSet, StatementContext statementContext) throws SQLException
    {
        return new Person()
                .setId(resultSet.getInt("ID"))
                .setName(resultSet.getString("NAME"));
    }
}
