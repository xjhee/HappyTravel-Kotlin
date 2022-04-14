package com.project.happytravel.controllers
import com.project.happytravel.entities.Event
import com.project.happytravel.entities.EventRepository
import com.project.happytravel.entities.UserRepository
import org.springframework.web.bind.annotation.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

@RestController
@RequestMapping("/events")
@CrossOrigin(origins = ["http://localhost:3000"])
class EventsController(val eventRepository: EventRepository, val userRepository: UserRepository) {

    @GetMapping
    fun getEvents() = eventRepository.findAll()

    @GetMapping("/region={region}")
    fun getEventsByRegion(@PathVariable("region") region: String): ArrayList<HashMap<String, Any?>>? {
        val rawData = eventRepository.findEventsByRegion(region)
        val parsedData: ArrayList<HashMap<String, Any?>> = ArrayList()

        for (each in rawData) {
            val parsedDataEach = HashMap<String, Any?>()
            parsedDataEach.put("id", each.id)
            parsedDataEach.put("region", each.region)
            parsedDataEach.put("text", each.text)
            parsedDataEach.put("label", each.label)
            parsedDataEach.put("image", each.image?.decodeToString())
            parsedData.add(parsedDataEach)
        }
        return parsedData
    }

    @GetMapping("/username={username}")
    fun getEventsByUserName(@PathVariable("username") username: String): ArrayList<HashMap<String, Any?>>? {
        val rawData = eventRepository.findEventsByUserName(username)
        val parsedUserEventData: ArrayList<HashMap<String, Any?>> = ArrayList()

        for (each in rawData) {
            val parsedUserEventDataEach = HashMap<String, Any?>()
            parsedUserEventDataEach.put("id", each.id)
            parsedUserEventDataEach.put("region", each.region)
            parsedUserEventDataEach.put("text", each.text)
            parsedUserEventDataEach.put("label", each.label)
            parsedUserEventDataEach.put("image", each.image?.decodeToString())
            parsedUserEventData.add(parsedUserEventDataEach)
        }
        return parsedUserEventData
    }

    @PostMapping("/post")
    fun postEvents(@RequestBody eventRaw: HashMap<String, String?>): Event {
        val postEvent = Event()
        postEvent.region = eventRaw.get("region")
        postEvent.text = eventRaw.get("text")
        postEvent.label = eventRaw.get("label")
        postEvent.image = eventRaw.get("image")?.toByteArray()
        postEvent.user_id = eventRaw.get("user_id")?.toLong()
        return eventRepository.save(postEvent)
    }
}
