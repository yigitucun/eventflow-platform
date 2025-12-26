package io.yigitucun.eventflow.event.service.mapper;

import io.yigitucun.eventflow.event.service.dto.requests.CreateEventRequest;
import io.yigitucun.eventflow.event.service.dto.requests.UpdateEventRequest;
import io.yigitucun.eventflow.event.service.dto.responses.EventResponse;
import io.yigitucun.eventflow.event.service.model.EventRole;
import io.yigitucun.eventflow.event.service.model.EventStatus;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Mapper
public interface EventMapper {
    @Insert("""
        INSERT INTO events(title,description,event_date,location,price,capacity,duration,status)
        VALUES (#{title},#{description},#{eventDate},#{location},#{price},#{capacity},#{duration},'ACTIVE')
    """)
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void insertEvent(CreateEventRequest request);

    @Select("""
        SELECT id, title, description, event_date as eventDate, location, price, capacity, sold_tickets as soldTickets, duration, status, created_at as createdAt
        FROM events
        WHERE id = #{id}
    """)
    Optional<EventResponse> findById(@Param("id") int id);

    @Select("""
        SELECT id, title, description, event_date as eventDate, location, price, capacity, sold_tickets as soldTickets, duration, status, created_at as createdAt
        FROM events
        ORDER BY event_date DESC
    """)
    List<EventResponse> findAll();

    @Select("""
        SELECT id, title, description, event_date as eventDate, location, price, capacity, sold_tickets as soldTickets, duration, status, created_at as createdAt
        FROM events
        WHERE status = #{status}
        ORDER BY event_date DESC
    """)
    List<EventResponse> findByStatus(@Param("status") EventStatus status);

    @Update("""
        UPDATE events
        SET title = #{request.title},
            description = #{request.description},
            event_date = #{request.eventDate},
            location = #{request.location},
            price = #{request.price},
            capacity = #{request.capacity},
            duration = #{request.duration},
            status = #{request.status}
        WHERE id = #{id}
    """)
    int updateEvent(@Param("id") int id, @Param("request") UpdateEventRequest request);

    @Update("""
        UPDATE events SET status = #{status} WHERE id = #{id}
    """)
    int updateStatus(@Param("id") int id, @Param("status") EventStatus status);

    @Delete("""
        DELETE FROM events WHERE id = #{id}
    """)
    int deleteById(@Param("id") int id);

    @Select("""
        SELECT EXISTS(
            SELECT 1 FROM events
            WHERE event_date < #{newEnd}
            AND (event_date + (duration * INTERVAL '1 minute')) > #{newStart}
            AND status != 'CANCELLED'
        )
    """)
    boolean existsOverlappingEvents(@Param("newStart") LocalDateTime newStart, @Param("newEnd") LocalDateTime newEnd);

    @Select("""
        SELECT EXISTS(
            SELECT 1 FROM event_staff
            WHERE event_id = #{eventId} AND user_id = #{userId} AND role IN ('OWNER', 'MODERATOR')
        )
    """)
    boolean isEventOwnerOrModerator(@Param("eventId") int eventId, @Param("userId") int userId);

    @Insert("""
        INSERT INTO event_staff(user_id,event_id,role) VALUES (#{userId},#{eventId},#{role})
    """)
    void addStaff(@Param("eventId") int eventId, @Param("userId") int userId, @Param("role") EventRole role);

    @Update("""
        UPDATE events SET sold_tickets = sold_tickets + #{quantity} WHERE id = #{eventId}
    """)
    int updateSoldTickets(@Param("eventId") int eventId, @Param("quantity") int quantity);

    @Select("""
        SELECT capacity - sold_tickets FROM events WHERE id = #{eventId}
    """)
    int getAvailableTickets(@Param("eventId") int eventId);
}
