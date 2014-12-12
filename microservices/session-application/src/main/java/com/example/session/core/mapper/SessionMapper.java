package com.example.session.core.mapper;

import com.example.session.core.Session;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionMapper implements ResultSetMapper<Session>
{
    public Session map(int index, ResultSet resultSet, StatementContext statementContext) throws SQLException
    {
        Session session = new Session();
        session.setUuid(resultSet.getString("UUID"));
        session.setUser(resultSet.getString("USER"));
        session.setCreated(resultSet.getDate("CREATED"));

        return session;
    }
}
