package com.project.happytravel.controllers
import com.project.happytravel.entities.Event
import com.project.happytravel.entities.EventRepository
import com.project.happytravel.entities.UserRepository
import org.springframework.web.bind.annotation.*
import java.math.BigInteger
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
            parsedDataEach.put("title", each.title)
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
            parsedUserEventDataEach.put("title", each.title)
            parsedUserEventDataEach.put("text", each.text)
            parsedUserEventDataEach.put("label", each.label)
            parsedUserEventDataEach.put("image", each.image?.decodeToString())
            parsedUserEventData.add(parsedUserEventDataEach)
        }
        return parsedUserEventData
    }

    @GetMapping("/id={id}")
    fun getEventById(@PathVariable("id") id: BigInteger): HashMap<String, Any?> {
        val rawData = eventRepository.findEventById(id)
        val parsedUserEventData = HashMap<String, Any?>()

        parsedUserEventData.put("id", rawData.id)
        parsedUserEventData.put("region", rawData.region)
        parsedUserEventData.put("title", rawData.title)
        parsedUserEventData.put("text", rawData.text)
        parsedUserEventData.put("label", rawData.label)
        parsedUserEventData.put("image", rawData.image?.decodeToString())
        return parsedUserEventData
    }

    @PostMapping("/post")
    fun postEvents(@RequestBody eventRaw: HashMap<String, Any?>): Event {
        val postEvent = Event()
        val username = eventRaw.get("username") as String?
        val userId = userRepository.findIdByUsername(username)
        postEvent.region = eventRaw.get("region") as String?
        postEvent.title = eventRaw.get("title") as String?
        postEvent.text = eventRaw.get("text") as String?
        postEvent.label = eventRaw.get("label") as String?
        postEvent.image = (eventRaw.get("image") as String?)?.toByteArray()
        postEvent.user_id = userId
        return eventRepository.save(postEvent)
    }
}
