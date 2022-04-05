package com.project.happytravel.controllers
import com.project.happytravel.entities.User
import com.project.happytravel.entities.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = ["http://localhost:3000"])
class UserController(val userRepository: UserRepository) {

    @GetMapping
    fun getUsers() = userRepository.findAll()

    @GetMapping("/{userId}")
    fun getUser(@PathVariable("userId") userId: Long): Optional<User> {
        return userRepository.findById(userId)
    }

    @PostMapping("/signup")
    fun SignUpUser(@RequestBody user: User): User {
        return userRepository.save(user)
    }

    @PutMapping("/{userId}")
    fun updateUser(@PathVariable("userId") userId: Long, @RequestBody updateUserInfo: User): User? {
        val oldUserInfo = userRepository.findByIdOrNull(userId)
        if (oldUserInfo == null) {
            return oldUserInfo
        }
        return userRepository.save(updateUserInfo)
    }

    @DeleteMapping("/{userId}")
    fun deleteUser(@PathVariable("userId") userId: Long) {
        userRepository.deleteById(userId)
    }

    @GetMapping("/login/{username}")
    fun validateUser(@PathVariable("username") username: String): User? {
        val getUserInfo = userRepository.findByUsername(username).firstOrNull()
        return getUserInfo
    }
}




