package com.project.happytravel.entities

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface FollowerRepository : JpaRepository<User, Long> {

    @Query(
        "select count(*) as count from users u join followers f on u.id = f.user_id where u.username = ?1",
        nativeQuery = true
    )
    fun findFollowerCountByName(@Param("username") username: String): Int

    @Query(
        "select count(*) as count from users u join followers f on f.follower_id = u.id where u.username = ?1",
        nativeQuery = true
    )
    fun findFollowingCountByName(@Param("username") username: String): Int

    @Query(
        "select * from users us where us.id in \n" +
            "(select f.follower_id from followers f " +
            "join users u on u.id = f.user_id where u.username = ?1)",
        nativeQuery = true
    )
    fun findFollowersByName(@Param("username") username: String): List<User>

    @Query(
        "select * from users where users.id in\n" +
            "(select f.user_id as user_id from followers f " +
            "join users u on f.follower_id = u.id where u.username = ?1)",
        nativeQuery = true
    )
    fun findFollowingsByName(@Param("username") username: String): List<User>
}
