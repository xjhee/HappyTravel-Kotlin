package com.project.happytravel.entities

import org.springframework.data.jpa.repository.JpaRepository


interface SignUpRepository : JpaRepository<SignUp, Long>{
}