package ru.itis.Makhsotova.Enclave.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.Makhsotova.Enclave.models.Hotel;
import ru.itis.Makhsotova.Enclave.models.Order;
import ru.itis.Makhsotova.Enclave.models.User;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ReservationRoomHotelRepositoryJdbcImpl implements ReservationRoomHotelRepository {

    private final JdbcTemplate jdbcTemplate;

    //language=SQL
    private final String SQL_INSERT_NEW_RESERVATION = "insert into reservation (user_id, hotel_id, check_in_date, check_out_date, reservation_status) values (?, ?, ?, ?, ?)";

    //language=SQL
    private final String SQL_SELECT_RESERVATION_BY_USER_ID = "select * from reservation left join hotel h on h.id = reservation.hotel_id where user_id = ?";

    //language=SQL
    private final String SQL_UPDATE_RESERVATION_STATUS_BY_ID = "update reservation set reservation_status = ? where id = ?";

    //language=SQL
    private final String SQL_UPDATE_HOTEL_NUMBER_FREE_ROOMS = "update hotel set number_free_rooms = ? where id = ?";

    public ReservationRoomHotelRepositoryJdbcImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final ResultSetExtractor<List<Order>> orderResultSetExtractor = resultSet -> {
        List<Order> orderList = new ArrayList<>();

        while (resultSet.next()) {
            orderList.add(Order.builder()
                            .id(resultSet.getInt("id"))
                    .hotel(Hotel.builder()
                    .id(resultSet.getInt("hotel_id"))
                    .name(resultSet.getString("name"))
                    .locality(resultSet.getString("locality"))
                    .street(resultSet.getString("street"))
                    .buildingNumber(resultSet.getString("building_number"))
                    .numberFreeRooms(resultSet.getInt("number_free_rooms"))
                    .imagePath(resultSet.getString("image"))
                    .build())
                            .checkInDate(resultSet.getDate("check_in_date"))
                            .checkOutDate(resultSet.getDate("check_out_date"))
                            .reservationStatus(resultSet.getString("reservation_status"))
                    .build());
        }
        return orderList;
    };

    @Override
    public Integer reservationRoom(User user, Hotel hotel, Date checkInDate, Date checkOutDate) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update( connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_NEW_RESERVATION, new String[]{"id"});

            statement.setInt(1, user.getId());
            statement.setInt(2, hotel.getId());
            statement.setDate(3, checkInDate);
            statement.setDate(4, checkOutDate);
            statement.setString(5, STATUS_ACTIVE);

            return statement;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public void updateNumberFreeRooms(Hotel hotel) {
        jdbcTemplate.update(SQL_UPDATE_HOTEL_NUMBER_FREE_ROOMS, hotel.getNumberFreeRooms(), hotel.getId());
    }
    @Override
    public List<Order> findAllUserReservation(User user) {
        return jdbcTemplate.query(SQL_SELECT_RESERVATION_BY_USER_ID, orderResultSetExtractor, user.getId());
    }

    @Override
    public void changeStatusReservation(Integer orderId, String status) {
           jdbcTemplate.update(SQL_UPDATE_RESERVATION_STATUS_BY_ID, status, orderId);
    }
}
