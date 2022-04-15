package com.project.happytravel.entities
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    @Query("select * from users where username = ?1", nativeQuery = true)
    fun findByUsername(@Param("username") username: String): List<User>

    @Query("select id from users where username = ?1", nativeQuery = true)
    fun findIdByUsername(@Param("username") username: String?): Long
}
