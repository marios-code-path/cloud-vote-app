package com.sportsnet.sportsnetgateway

import com.sportsnet.Fan
import com.sportsnet.Interest
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
class SportsnetDomainTests {

    @Test
    fun `test interests are constructible`() {
        val interest = Interest(0L, "Turkey")

        val interestStateMatcher = Matchers.allOf(
                Matchers.hasProperty("name",
                        Matchers.allOf(
                                Matchers.notNullValue(),
                                Matchers.not(Matchers.empty<String>())
                        ))
        )

        MatcherAssert.assertThat("interest holds data",
                interest,
                interestStateMatcher)
    }

    @Test
    fun `test our fans can hold data`() {
        val fan = Fan(null,
                "THE RZA",
                listOf(Interest(null, "Gravy"), Interest(null, "Turkey")))


        val fanStateMatcher = Matchers.allOf(
                Matchers.hasProperty("name", Matchers.notNullValue()),
                Matchers.hasProperty("interests",
                        Matchers.allOf(
                                Matchers.notNullValue(),
                                Matchers.not(Matchers.emptyIterable<List<Interest>>())
                        )
                )
        )

        MatcherAssert.assertThat("Fans hold proper state",
                fan,
                fanStateMatcher)
    }
}