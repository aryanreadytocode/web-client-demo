package com.aryan.web_client_demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebClientDemoApplication
/*thenMany will wait to complete the previous task*/
fun main(args: Array<String>) {
	runApplication<WebClientDemoApplication>(*args)
//	val api = WebClientAPI()
	val api = WebClientAPIUsingExchange()

	api.getAllEmployees()
		.thenMany(api.saveEmployees())
		.thenMany(api.getAllEmployees())
		.thenMany(api.updateEmployees())
		.thenMany(api.getAllEmployees())
		.thenMany(api.deleteEmployees("123"))
		.thenMany(api.getAllEmployees())
		.subscribe()
}
