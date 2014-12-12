package com.example.session.dao;

import com.example.session.core.Session;
import com.example.session.core.mapper.SessionMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(SessionMapper.class)
public interface SessionDAO {

    @SqlUpdate("create table SESSION (UUID varchar(36) not null, USER varchar(100) not null, CREATED timestamp not null)")
    void createTable();

    @SqlQuery("select * from SESSION where UUID = :uuid")
    Session findByUUID(@Bind("uuid") String uuid);

    @SqlUpdate("delete from SESSION where UUID = :uuid")
    void deleteByUUID(@Bind("uuid") String uuid);

    @SqlUpdate("insert into SESSION (UUID, USER, CREATED) values (:uuid, :user, :created)")
    void insert(@BindBean Session session);
}
