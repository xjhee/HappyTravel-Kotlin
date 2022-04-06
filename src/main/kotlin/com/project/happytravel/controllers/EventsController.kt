package com.project.happytravel.controllers
import com.project.happytravel.entities.Event
import com.project.happytravel.entities.EventRepository
import org.springframework.web.bind.annotation.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

@RestController
@RequestMapping("/events")
@CrossOrigin(origins = ["http://localhost:3000"])
class EventsController(val eventRepository: EventRepository) {

    @GetMapping
    fun getEvents() = eventRepository.findAll()

    @GetMapping("/{region}")
    fun getEventsByRegion(@PathVariable("region") region: String): ArrayList<HashMap<String, Any?>>? {
        var rawData = eventRepository.findEventsByRegion(region)
        var parsedData: ArrayList<HashMap<String, Any?>> = ArrayList()

        for (each in rawData) {
            var parsedDataEach = HashMap<String, Any?>()
            parsedDataEach.put("id", each.id)
            parsedDataEach.put("region", each.region)
            parsedDataEach.put("text", each.text)
            parsedDataEach.put("label", each.label)
            parsedDataEach.put("image", each.image?.decodeToString())
            parsedData.add(parsedDataEach)
        }
        return parsedData
    }

    @PostMapping("/post")
    fun postEvents(@RequestBody event: Event): Event {
        return eventRepository.save(event)
    }
}
