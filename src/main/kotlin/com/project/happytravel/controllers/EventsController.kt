package com.project.happytravel.controllers
import com.project.happytravel.entities.Event
import com.project.happytravel.entities.EventRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/events")
@CrossOrigin(origins = ["http://localhost:3000"])
class EventsController(val eventRepository: EventRepository) {

    @GetMapping
    fun getEvents() = eventRepository.findAll()
}