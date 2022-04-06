package com.project.happytravel.entities
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "events")
open class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @Column(name = "region")
    var region: String? = null

    @Column(name = "text")
    var text: String? = null

    @Column(name = "label")
    var label: String? = null

    @Column(name = "image")
    var image: ByteArray? = null
}