package com.project.happytravel.controllers

import com.project.happytravel.entities.SignUp
import com.project.happytravel.entities.SignUpRepository
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/signup")
@CrossOrigin(origins = ["http://localhost:3000"])
class SignUpController(val signUpRepository: SignUpRepository) {

    @PostMapping
    fun newAccount(@RequestBody signup: SignUp): SignUp {
        return signUpRepository.save(signup)
    }
}
