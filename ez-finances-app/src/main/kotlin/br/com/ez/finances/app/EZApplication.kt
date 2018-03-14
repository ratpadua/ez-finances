package br.com.ez.finances.app

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class EZApplication

fun main(args: Array<String>) {
    SpringApplication.run(EZApplication::class.java, *args)
}