package net.taobits.bignum

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class BigIntegerLongConversionTest : DescribeSpec({

    describe("Long to BigInteger conversion should preserve value") {
        data class TestCase(val name: String, val value: Long)
        withData(
            // Explicit test cases
            TestCase(name = "Zero", value = 0L),
            TestCase(name = "Positive One", value = 1L),
            TestCase(name = "Negative One", value = -1L),
            TestCase(name = "Max Long", value = Long.MAX_VALUE),
            TestCase(name = "Min Long", value = Long.MIN_VALUE),
            TestCase(name = "Positive 42", value = 42L),
            TestCase(name = "Negative 42", value = -42L),
            TestCase(name = "Large Positive", value = 123456789L),
            TestCase(name = "Large Negative", value = -987654321L)
        ) { (_, value) ->
            // Convert Long → BigInteger → Long
            val bigInt = BigInteger.valueOf(value)
            val convertedLong = bigInt.toLong()

            // Assert equivalence
            convertedLong shouldBe value
        }
    }

    describe("BigInteger.toLong() overflow") {
        it("Throws ArithmeticException for values exceeding Long.MAX_VALUE") {
            val big = BigInteger.valueOf(Long.MAX_VALUE) + BigInteger.ONE
            shouldThrow<ArithmeticException> { big.toLong() }
        }

        it("Throws ArithmeticException for values below Long.MIN_VALUE") {
            val big = BigInteger.valueOf(Long.MIN_VALUE) - BigInteger.ONE
            shouldThrow<ArithmeticException> { big.toLong() }
        }
    }
})