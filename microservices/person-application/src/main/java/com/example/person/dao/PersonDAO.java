package com.example.person.dao;

import com.example.person.core.Person;
import com.example.person.core.mapper.PersonMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(PersonMapper.class)
public interface PersonDAO {

    @SqlUpdate("create table PERSON (ID int not null, NAME varchar(100) not null)")
    void createTable();

    @SqlUpdate("insert into PERSON (ID, NAME) values (1, 'John Doe'); " +
               "insert into PERSON (ID, NAME) values (2, 'Jane Doe')")
    void insertTestData();

    @SqlQuery("select * from PERSON")
    List<Person> getAll();

    @SqlQuery("select * from PERSON where ID = :id")
    Person findById(@Bind("id") int id);

    @SqlUpdate("delete from PERSON where ID = :id")
    void deleteById(@Bind("id") int id);

    @SqlUpdate("update into PERSON set NAME = :name where ID = :id")
    int update(@BindBean Person person);

    @SqlUpdate("insert into PERSON (ID, NAME) values (:id, :name)")
    int insert(@BindBean Person person);
}
