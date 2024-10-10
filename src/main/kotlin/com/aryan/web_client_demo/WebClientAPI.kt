package com.aryan.web_client_demo

import org.springframework.web.reactive.function.BodyInserter
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
/*
* exchange will return clientResponse and from that you need to retrieve data
* whereas retrieve will return data directly*/
class WebClientAPI {

    // Create web client instance
    // private val webClient = WerClient.create()
    private val webClient = WebClient.create("http://localhost:8080/v1")

    // prepare request
    // read request

    fun getAllEmployees(): Flux<Employee> {
        return webClient
            .get()
            .uri("/employees")
            .retrieve()
            .bodyToFlux(Employee::class.java)
            .doOnNext { println("Inside getAllEmployees $it") }
    }

    fun saveEmployees(): Mono<Employee> {
        return webClient
            .post()
            .uri("/employees")
            .body(BodyInserters.fromValue(Employee("123", "Jonny", "Me")))
            .retrieve()
            .bodyToMono(Employee::class.java)
            .doOnNext { println("Inside save employee $it") }
    }

    fun updateEmployees(): Mono<Employee> {
        return webClient
            .put()
            .uri("/employees/update")
            .body(BodyInserters.fromValue(Employee("123", "Jonny", "CSE")))
            .retrieve()
            .bodyToMono(Employee::class.java)
            .doOnNext { println("Inside update employee $it") }
    }

    fun deleteEmployees(id: String): Mono<Employee> {
        return webClient
            .delete()
            .uri("/employees/{id}", id)
            .retrieve()
            .bodyToMono(Employee::class.java)
            .doOnSuccess {
                println("Inside delete employee $it")
            }
    }
}

    data class Employee(
        val id: String?,
        val name: String,
        val dept: String
    )
