package com.project.happytravel.entities
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface EventRepository : JpaRepository<Event, Long> {
    @Query("select * from events where region = ?1", nativeQuery = true)
    fun findEventsByRegion(@Param("region") region: String): List<Event>
}
