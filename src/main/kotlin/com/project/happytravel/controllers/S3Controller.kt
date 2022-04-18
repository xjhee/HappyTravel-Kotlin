package com.project.happytravel.controllers

import com.amazonaws.services.s3.model.Region
import com.project.happytravel.services.S3Service
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RequestMapping("/api/v1")
@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
class S3Controller(
    private val s3Service: S3Service
) {

    @PostMapping("/upload")
    fun fileUpload(@RequestParam("file") multipartFile: MultipartFile, @RequestParam("region") region: String): MutableMap<String, String> {
        val s3Url = s3Service.upload(multipartFile, region)
        val response = mutableMapOf<String, String>()
        response["url"] = s3Url
        return response
    }
}