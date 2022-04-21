package com.project.happytravel.controllers

import com.project.happytravel.entities.*
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/followers")
@CrossOrigin(origins = ["http://localhost:3000"])
class FollowersController(val followerRepository: FollowerRepository) {

    @GetMapping("/count/follower={username}")
    fun countFollowers(@PathVariable("username") username: String): Int {
        return followerRepository.findFollowerCountByName(username)
    }

    @GetMapping("/count/following={username}")
    fun countFollowings(@PathVariable("username") username: String): Int {
        return followerRepository.findFollowingCountByName(username)
    }
}