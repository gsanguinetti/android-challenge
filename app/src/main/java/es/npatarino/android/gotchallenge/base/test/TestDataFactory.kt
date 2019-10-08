package es.npatarino.android.gotchallenge.base.test

import kotlin.random.Random

/**
 * Factory functions for dataModule instances
 */

fun randomString(): String = java.util.UUID.randomUUID().toString()

fun randomInt(start: Int = 1, end: Int = 1000): Int =
    Random.nextInt(start, end + 1)

fun randomDouble(): Double = Random.nextDouble()

fun randomLong(): Long = Random.nextLong()

fun randomBoolean(): Boolean = Random.nextBoolean()

fun randomStringList(count: Int): List<String> {
    val items: MutableList<String> = mutableListOf()
    repeat(count) {
        items.add(randomString())
    }
    return items
}