package io.yigitucun.eventflow.ticket.service.mapper;

import io.yigitucun.eventflow.ticket.service.dto.requests.CreateTicketRequest;
import io.yigitucun.eventflow.ticket.service.dto.responses.TicketResponse;
import io.yigitucun.eventflow.ticket.service.model.TicketStatus;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TicketMapper {

    @Insert("""
        INSERT INTO tickets (event_id, user_id, ticket_number, price, status, purchased_at)
        VALUES (#{request.eventId}, #{userId}, #{request.ticketNumber}, #{request.price}, #{request.status}, NOW())
    """)
    @Options(useGeneratedKeys = true, keyProperty = "request.id", keyColumn = "id")
    void insertTicket(@Param("request") CreateTicketRequest request, @Param("userId") int userId);

    @Select("""
        SELECT id, event_id as eventId, user_id as userId, ticket_number as ticketNumber,
               price, status, purchased_at as purchasedAt, created_at as createdAt
        FROM tickets
        WHERE id = #{id}
    """)
    Optional<TicketResponse> findById(@Param("id") int id);

    @Select("""
        SELECT id, event_id as eventId, user_id as userId, ticket_number as ticketNumber,
               price, status, purchased_at as purchasedAt, created_at as createdAt
        FROM tickets
        WHERE ticket_number = #{ticketNumber}
    """)
    Optional<TicketResponse> findByTicketNumber(@Param("ticketNumber") String ticketNumber);

    @Select("""
        SELECT id, event_id as eventId, user_id as userId, ticket_number as ticketNumber,
               price, status, purchased_at as purchasedAt, created_at as createdAt
        FROM tickets
        WHERE user_id = #{userId}
        ORDER BY purchased_at DESC
    """)
    List<TicketResponse> findByUserId(@Param("userId") int userId);

    @Select("""
        SELECT id, event_id as eventId, user_id as userId, ticket_number as ticketNumber,
               price, status, purchased_at as purchasedAt, created_at as createdAt
        FROM tickets
        WHERE event_id = #{eventId}
        ORDER BY purchased_at DESC
    """)
    List<TicketResponse> findByEventId(@Param("eventId") int eventId);

    @Select("""
        SELECT id, event_id as eventId, user_id as userId, ticket_number as ticketNumber,
               price, status, purchased_at as purchasedAt, created_at as createdAt
        FROM tickets
        WHERE event_id = #{eventId} AND status = #{status}
        ORDER BY purchased_at DESC
    """)
    List<TicketResponse> findByEventIdAndStatus(@Param("eventId") int eventId, @Param("status") TicketStatus status);

    @Update("""
        UPDATE tickets SET status = #{status} WHERE id = #{id}
    """)
    int updateStatus(@Param("id") int id, @Param("status") TicketStatus status);

    @Update("""
        UPDATE tickets SET status = 'CANCELLED' WHERE event_id = #{eventId} AND status = 'ACTIVE'
    """)
    int cancelTicketsByEventId(@Param("eventId") int eventId);

    @Delete("""
        DELETE FROM tickets WHERE id = #{id}
    """)
    int deleteById(@Param("id") int id);

    @Select("""
        SELECT COUNT(*) FROM tickets WHERE event_id = #{eventId} AND status IN ('ACTIVE', 'SOLD')
    """)
    int countActiveTicketsByEventId(@Param("eventId") int eventId);

    @Select("""
        SELECT COUNT(*) FROM tickets WHERE event_id = #{eventId} AND user_id = #{userId} AND status = 'ACTIVE'
    """)
    int countUserActiveTicketsForEvent(@Param("eventId") int eventId, @Param("userId") int userId);

    @Select("""
        SELECT EXISTS(SELECT 1 FROM tickets WHERE id = #{ticketId} AND user_id = #{userId})
    """)
    boolean isTicketOwner(@Param("ticketId") int ticketId, @Param("userId") int userId);
}