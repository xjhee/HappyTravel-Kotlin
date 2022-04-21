package com.project.happytravel.entities

import javax.persistence.*

@Entity
@Table(name = "followers")
open class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @Column(name = "user_id")
    open var user_id: Long? = null

    @Column(name = "follower_id")
    open var follower_id: Long? = null
}