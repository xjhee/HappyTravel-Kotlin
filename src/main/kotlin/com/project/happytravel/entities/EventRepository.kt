package com.project.happytravel.entities
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.math.BigInteger

@Repository
interface EventRepository : JpaRepository<Event, Long> {
    @Query("select * from events where region = ?1", nativeQuery = true)
    fun findEventsByRegion(@Param("region") region: String): List<Event>

    @Query(
        "select e.id as id, region, title, text, label, image, user_id" +
            " from events e join users u\n" +
            "on e.user_id = u.id\n" +
            "where u.username = ?1",
        nativeQuery = true
    )
    fun findEventsByUserName(@Param("username") username: String): List<Event>

    @Query(
        "select id, region, title, text, label, image, user_id" +
            " from events where id = ?1",
        nativeQuery = true
    )
    fun findEventById(@Param("id") id: BigInteger): Event
}
