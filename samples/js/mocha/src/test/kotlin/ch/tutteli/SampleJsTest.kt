package ch.tutteli

import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.assertThat
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.translations.DescriptionBasic
import kotlin.test.Test

class SampleJsTest {
    @Test
    fun toBe() {
        expect(1).toBe(1)
    }

    @Test
    fun assertAnExceptionOccurred() {
        expect {
            throw IllegalArgumentException()
        }.toThrow<IllegalArgumentException>()
    }


    @Test
    fun assertAnExceptionWithAMessageOccurred() {
        expect {
            throw IllegalArgumentException("oho... hello btw")
        }.toThrow<IllegalArgumentException> {
            messageContains("hello")
        }
    }

    @Test
    fun useOwnFunction() {
        // isEven is defined in the common module
        assertThat(2).isEven()
    }
}

fun Expect<Int>.isEven() =
    createAndAddAssertion(DescriptionBasic.IS, RawString.create("an even number")) { it % 2 == 0 }
