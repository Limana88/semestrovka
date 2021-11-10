package ru.itis.Makhsotova.Enclave.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.Makhsotova.Enclave.models.Hotel;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HotelRepositoryJdbcImpl implements HotelRepository {

    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private final String SQL_SELECT_ALL_HOTELS = "select * from hotel";

    //language=SQL
    private final String SQL_SELECT_HOTEL_BY_ID = "select * from hotel where id = ?";

    public HotelRepositoryJdbcImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final RowMapper<Hotel> hotelRowMapper = (row, rowNumber) -> {
        return Hotel.builder()
                .id(row.getInt("id"))
                .name(row.getString("name"))
                .locality(row.getString("locality"))
                .street(row.getString("street"))
                .buildingNumber(row.getString("building_number"))
                .imagePath(row.getString("image"))
                .numberFreeRooms(row.getInt("number_free_rooms"))
                .build();
    };

    private final ResultSetExtractor<List<Hotel>> hotelResultSetExtractor = resultSet -> {
        List<Hotel> hotelList = new ArrayList<>();

        while (resultSet.next()) {
            hotelList.add(Hotel.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .locality(resultSet.getString("locality"))
                    .street(resultSet.getString("street"))
                    .buildingNumber(resultSet.getString("building_number"))
                    .imagePath(resultSet.getString("image"))
                    .numberFreeRooms(resultSet.getInt("number_free_rooms"))
                    .build());
        }

        return hotelList;
    };

    @Override
    public List<Hotel> findAllHotel() {
        return jdbcTemplate.query(SQL_SELECT_ALL_HOTELS, hotelResultSetExtractor);
    }

    @Override
    public Optional<Hotel> getHotelById(Integer id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_HOTEL_BY_ID, hotelRowMapper, id));
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }
}
