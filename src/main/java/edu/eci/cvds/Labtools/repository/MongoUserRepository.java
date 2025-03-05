package edu.eci.cvds.Labtools.repository;

import edu.eci.cvds.Labtools.model.Booking;
import edu.eci.cvds.Labtools.model.BookingDTO;
import edu.eci.cvds.Labtools.model.User;
import edu.eci.cvds.Labtools.model.UserDTO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public interface MongoUserRepository extends UserRepository {

    default boolean userAuthentication(UserDTO userDTO) {
        return false;
    }


    default BookingDTO[] queryUserBooking(String userId){
        return null;
    }

    default User getUser(String userId){
        return null;
    }

    default void setUser(String userId, Booking booking){

    }

}
