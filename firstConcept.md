# First Concept

-----
# Kotlin BigInteger: Java-Compatible Implementation Summary

## 1. Core Goals

* **Drop-in Replacement**: Mimic `java.math.BigInteger`’s API so existing Java/Kotlin code works by changing only the package import.
* **Minimal but Complete**: Implement critical functions first, with room for optimizations later.
* **Compatibility**: Match Java’s behavior for edge cases, exceptions, and internal representation.

## 2. Essential Components

### Internal Representation

* **Sign-Magnitude Format**:

    * `sign`: `Int` (`-1`, `0`, or `1`)
    * `magnitude`: `IntArray` (unsigned, little/big-endian array of digits)

### Constructors

* From `String` (with radix support: 2–36)
* From `ByteArray` (two’s complement)
* From `Long` (convenience)

### Arithmetic Operations

* `add`, `subtract`, `multiply` (start with schoolbook, then Karatsuba)
* `divide`, `remainder`, `divideAndRemainder`
* `pow`, `gcd`, `mod`, `modPow`, `modInverse` (Extended Euclidean Algorithm)

### Bitwise Operations

* `and`, `or`, `xor`, `not`, `shiftLeft`, `shiftRight`
* `testBit`, `setBit`, `clearBit`

### Comparison & Utilities

* `compareTo`, `equals`, `hashCode`
* `signum`, `abs`, `negate`, `min`, `max`

### Conversions

* `toString()` (with radix support)
* `toByteArray()` (two’s complement)
* `intValueExact()`, `longValueExact()` (throw on overflow)

## 3. Java Compatibility Criticals

* **Static Constants**: `ZERO`, `ONE`, `TEN` in a companion object
* **Factory Method**: `valueOf(Long)`

### Exceptions

* `NumberFormatException` (invalid strings)
* `ArithmeticException` (division by zero, no modular inverse)

### Algorithm Parity

* Same results as Java for edge cases (e.g., negative values in `mod` vs `remainder`)
* Byte-for-byte match in `toByteArray()`

## 4. Why `modInverse` Matters

* **Not a Replacement**: Works alongside `mod` for solving `a * x ≡ 1 (mod m)`
* **Cryptography**: Required for RSA, ECC, and key generation
* **Java API Contract**: Must exist for compatibility and throw errors when `gcd(a, m) ≠ 1`

## 5. Implementation Strategy

* **Start Simple**:

    * Basic algorithms (e.g., schoolbook multiplication)
    * Add optimizations (Karatsuba, Montgomery reduction) later

* **Test Rigorously**:

    * Cross-check results with Java’s `BigInteger`
    * Validate edge cases (e.g., `-1`, `0`, overflow)

* **Stub Unsupported Methods**:

    * Throw `UnsupportedOperationException` for non-critical methods (if any)

## Key Takeaways

* **Focus on API Parity**: Match Java’s method signatures, exceptions, and behavior
* **Prioritize Cryptography-Ready Features**: `modInverse`, `modPow`, and proper bitwise ops
* **Representation is Key**: Sign-magnitude ensures compatibility with Java’s results

This plan ensures your Kotlin BigInteger behaves identically to Java’s for all implemented methods, making it a seamless replacement!
