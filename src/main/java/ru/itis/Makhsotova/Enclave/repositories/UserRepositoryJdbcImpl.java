package ru.itis.Makhsotova.Enclave.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.Makhsotova.Enclave.models.User;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserRepositoryJdbcImpl implements UserRepository {

    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private final static String SQL_INSERT = "insert into account (first_name, last_name, email, hash_password) values (?, ?,  ?, ?)";

    //language=SQL
    private final static String SQL_SELECT_BY_EMAIL = "select * from account where email = ? ";

    //language=SQL
    private final static String SQL_ADD_UUID = "update account set uuid = ?  where id = ? ";

    //language=SQL
    private final String SQL_SELECT_BY_UUID = "select * from account where account.uuid = ?";

    public UserRepositoryJdbcImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final RowMapper<User> userRowMapper = (row, rowNumber) -> {
        return User.builder()
                .id(row.getInt("id"))
                .firstName(row.getString("first_name"))
                .secondName(row.getString("last_name"))
                .email(row.getString("email"))
                .password(row.getString("hash_password"))
                .build();
    };

    @Override
    public void save(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update( connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"id"});

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getSecondName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            return statement;
        }, keyHolder);

        user.setId (keyHolder.getKey().intValue());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, userRowMapper, email ));
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public User getProfileByUUID(UUID uuid) {
        List<User> list = jdbcTemplate.query(SQL_SELECT_BY_UUID, userRowMapper, uuid);
        if(list.size() == 0){
            return null;
        }
        return list.get(0);
    }


    @Override
    public int addUUID(User user, UUID uuid) {
        return jdbcTemplate.update(SQL_ADD_UUID,  uuid, user.getId());
    }
}
