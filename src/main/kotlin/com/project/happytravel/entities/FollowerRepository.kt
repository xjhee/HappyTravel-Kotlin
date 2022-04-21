package com.project.happytravel.entities

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface FollowerRepository : JpaRepository<Follower, Long> {

    @Query(
        "select count(*) as count from followers join users on users.id = followers.user_id where users.username = ?1",
        nativeQuery = true
    )
    fun findFollowerCountByName(@Param("username") username: String): Int

    @Query(
        "select count(f.id) as count from followers f join users u on u.id = f.follower_id where u.username = ?1",
        nativeQuery = true
    )
    fun findFollowingCountByName(@Param("username") username: String): Int
}
