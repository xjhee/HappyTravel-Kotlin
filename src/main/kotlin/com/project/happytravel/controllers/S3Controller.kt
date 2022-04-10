package com.project.happytravel.controllers

import com.project.happytravel.services.S3Service
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RequestMapping("/api/v1")
@RestController
class S3Controller(
    private val s3Service: S3Service
) {

    @PostMapping("/upload")
    fun fileUpload(@RequestParam("image") multipartFile: MultipartFile): String {
        return s3Service.upload(multipartFile)
    }
}
