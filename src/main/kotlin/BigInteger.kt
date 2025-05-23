package net.taobits.bignum

class BigInteger internal constructor(
    private val sign: Int,  // -1, 0, or 1
    private val magnitude: IntArray  // Big-endian, unsigned
) : Comparable<BigInteger> {

    fun toLong(): Long {
        if (sign == 0) return 0L
        require(magnitude.size <= 2) { "Magnitude exceeds 64 bits" }

        var result = 0L
        for (num in magnitude) {
            result = (result shl 32) or (num.toLong() and 0xFFFFFFFFL) // Merge 32-bit chunks as unsigned
        }
        return if (sign == -1) -result else result
    }

    fun add(value: BigInteger): BigInteger = plus(value)

    operator fun plus(value: BigInteger): BigInteger = TODO()

    fun subtract(value: BigInteger): BigInteger = minus(value)

    operator fun minus(value: BigInteger): BigInteger = TODO()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BigInteger) return false
        return sign == other.sign && magnitude.contentEquals(other.magnitude)
    }

    override fun hashCode(): Int {
        return 31 * sign + magnitude.contentHashCode()
    }

    override fun compareTo(other: BigInteger): Int {
        TODO("Not implemented yet")
    }

    companion object {
        val ZERO = BigInteger(0, intArrayOf())
        val ONE = BigInteger(1, intArrayOf(1))  // Not used yet
        val TEN = BigInteger(1, intArrayOf(10)) // Not used yet

        fun valueOf(value: Long): BigInteger {
            if (value == 0L) return ZERO

            val sign = if (value < 0) -1 else 1
            if (value == Long.MIN_VALUE) {
                // Split Long.MIN_VALUE into high/low Ints directly
                val high = (value ushr 32).toInt() // -2147483648 (0x80000000)
                val low = value.toInt()            // 0
                return BigInteger(sign, intArrayOf(high, low))
            }

            val absValue = kotlin.math.abs(value)
            val high = (absValue ushr 32).toInt()
            val low = absValue.toInt()

            return if (high != 0) {
                BigInteger(sign, intArrayOf(high, low))
            } else {
                BigInteger(sign, intArrayOf(low))
            }        }
    }
}