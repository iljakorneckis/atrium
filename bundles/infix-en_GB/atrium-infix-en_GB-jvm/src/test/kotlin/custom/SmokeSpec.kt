package custom

import ch.tutteli.atrium.api.infix.en_GB.toBe
import ch.tutteli.atrium.api.verbs.assertThat
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.createDescriptiveAssertion
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import ch.tutteli.atrium.translations.DescriptionBasic
import org.spekframework.spek2.Spek

object SmokeSpec : Spek({
    test("see if `toBe` can be used") {
        assertThat(1) toBe 1
    }

    test("see if own assertion function without i18n can be used") {
        assertThat(2) tobe even
        assertThat(1) tobe odd
    }

    test("see if own assertion function with i18n can be used") {
        assertThat(4) isMultipleOf 2
    }
})

@Suppress("ClassName")
object even
@Suppress("ClassName")
object odd

infix fun Expect<Int>.tobe(@Suppress("UNUSED_PARAMETER") even: even) =
    _logic.appendAssertion(_logic.createDescriptiveAssertion(DescriptionBasic.IS, Text("an even number")) { it % 2 == 0 })

infix fun Expect<Int>.tobe(@Suppress("UNUSED_PARAMETER") odd: odd) =
    _logic.appendAssertion(_logic.createDescriptiveAssertion(DescriptionBasic.IS, Text("an odd number")) { it % 2 == 1 })

infix fun Expect<Int>.isMultipleOf(base: Int): Expect<Int> = _logicAppend { isMultipleOf(base) }

private fun AssertionContainer<Int>.isMultipleOf(base: Int): Assertion =
    createDescriptiveAssertion(DescriptionIntAssertions.IS_MULTIPLE_OF, base) { it % base == 0 }

enum class DescriptionIntAssertions(override val value: String) : StringBasedTranslatable {
    IS_MULTIPLE_OF("is multiple of")
}

