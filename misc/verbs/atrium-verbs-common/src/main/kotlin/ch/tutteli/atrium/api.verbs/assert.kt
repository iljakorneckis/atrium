package ch.tutteli.atrium.api.verbs

import ch.tutteli.atrium.api.verbs.AssertionVerb.ASSERT
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.FeatureExpect
import ch.tutteli.atrium.creating.RootExpect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.reporting.ExpectBuilder
import ch.tutteli.atrium.domain.builders.reporting.ExpectOptions

/**
 * Creates an [Expect] for the given [subject].
 *
 * @param subject The subject for which we are going to postulate assertions.
 *
 * @return The newly created assertion container.
 * @throws AssertionError in case an assertion does not hold.
 */
fun <T> assert(subject: T): RootExpect<T> =
    ExpectBuilder.forSubject(subject)
        .withVerb(ASSERT)
        .withoutOptions()
        .build()

/**
 * Creates an [Expect] for the given [subject] and [Expect.addAssertionsCreatedBy] the
 * given [assertionCreator]-lambda where the created [Assertion]s are added as a group and reported as a whole.
 *
 * @param subject The subject for which we are going to postulate assertions.
 * @param assertionCreator Assertion group block with a non-fail fast behaviour.
 * @return The newly created assertion container.
 * @throws AssertionError in case an assertion does not hold.
 */
fun <T> assert(subject: T, assertionCreator: Expect<T>.() -> Unit): Expect<T> =
    assert(subject).addAssertionsCreatedBy(assertionCreator)

@Deprecated(
    "`assert` should not be nested, use `feature` instead.",
    ReplaceWith(
        "feature(\"name of the feature\") { newSubject /* see also other overloads which do not require `name of the feature` and provide the subject as parameter, e.g. feature { f(it::yourFeature) } */}",
        "ch.tutteli.atrium.api.fluent.de_CH.feature",
        "ch.tutteli.atrium.api.fluent.en_GB.feature"
    )
)
fun <T, R> Expect<T>.assert(newSubject: R): FeatureExpect<T, R> =
    ExpectImpl.feature.manualFeature(this, ASSERT) { newSubject }.getExpectOfFeature()
