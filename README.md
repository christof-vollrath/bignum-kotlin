# Kotlin BigInteger, BigDecimal (Java-Compatible)

A minimal drop-in replacement for `java.math.BigInteger` and `java.math.BigDecimal` implemented in pure Kotlin. Not designed for full compatibility with Java's API, but all existing method should be compatible, making it easy to integrate into Kotlin.

---

## ✨ Features

* **Drop-in Replacement**: Mimics `java.math.BigInteger` and `java.math.BigDecimal` —switch by changing only the import.
* **Full Java Compatibility**: Method signatures, exceptions, and behavior match Java’s `BigInteger` and `BigDecimal.
* **Cryptography-Ready**: Includes `modInverse`, `modPow`, and bitwise operations.
* **Accurate Internal Representation**: Sign-magnitude format aligns with Java’s internal structure.
* **Cross-Platform Kotlin**: Written purely in Kotlin with KMP in mind.

---

## 📦 Usage

```kotlin
import your.package.BigInteger // Replace with your actual package

val a = BigInteger("12345678901234567890")
val b = BigInteger("98765432109876543210")
val sum = a.add(b)

println("Sum: $sum")
```

---

## 📀 Internal Representation

* **Sign**: An `Int` (`-1`, `0`, `1`)
* **Magnitude**: An `IntArray` storing unsigned digits in little/big-endian form

This mirrors Java's `BigInteger` to ensure identical behavior, especially for conversions like `toByteArray()` and cryptographic operations.

---

## 🔧 Implemented API

### ✅ Arithmetic

* `add`, `subtract`, `multiply`, `divide`, `remainder`, `divideAndRemainder`
* `pow`, `gcd`, `mod`, `modPow`, `modInverse`

### ✅ Bitwise

* `and`, `or`, `xor`, `not`
* `shiftLeft`, `shiftRight`
* `testBit`, `setBit`, `clearBit`

### ✅ Conversion & Comparison

* `compareTo`, `equals`, `hashCode`
* `toString(radix)`, `toByteArray()`
* `intValueExact()`, `longValueExact()`

### ✅ Constants & Factory

* `BigInteger.ZERO`, `ONE`, `TEN`
* `BigInteger.valueOf(Long)`

### 🚧 Planned

* Karatsuba multiplication
* Montgomery reduction
* Performance profiling
* Multiplatform support

---

## 🧪 Testing

Includes test coverage for:

* Edge cases: `-1`, `0`, overflow conditions
* Cross-validation against Java’s `BigInteger`
* Cryptographic cases: RSA-friendly values

---

## 💡 Why Another BigInteger?

Java’s `BigInteger` is powerful but isn't idiomatic Kotlin. This library aims to:

* Provide better Kotlin interoperability
* Enable full control and extensibility
* Serve cryptographic and educational use cases

---

## 🚀 Getting Started

### Gradle

```kotlin
dependencies {
    implementation("net.taobits:bigmath-kotlin:<version>")
}
```

## 📦 Usage

### Basic Example

```kotlin
import your.package.BigInteger // Replace with your actual package

val a = BigInteger("12345678901234567890")
val b = BigInteger("98765432109876543210")
val sum = a.add(b)

println("Sum: $sum")
```

### Java Compatibility

Replace your Java imports:

```diff
- import java.math.BigInteger
+ import net.taobits.bignum.BigInteger
```

---

## Building from Source

1. **Clone the repository**:

   ```bash
   git clone https://github.com/taobits/bignum-kotlin.git
   cd bignum-kotlin
   ```

2. **Build with Gradle**:

   ```bash
   ./gradlew build
   ```

   This generates a JAR file in `build/libs/`.

3. **Include the JAR in your project**:

   * Add the generated JAR as a dependency in your IDE or build tool.

4. **Alternatively, publish it to your local Maven repository**:

   ```bash
   ./gradlew publishToMavenLocal
   ```

5. **Then reference it in your project**:

   ```kotlin
   // Gradle
   implementation("net.taobits:bignum-kotlin:1.0.0")
   ```


## 🛠️ Contribution

Pull requests are welcome! Please:

1. Match Java’s behavior for all changes.
2. Include tests for new functionality.
3. Keep performance optimizations modular and optional.

---

## 📄 License

[MIT License](LICENSE)

---

## 🧭 Goals

* 📦 **API parity** with Java
* 🔒 **Secure** for cryptographic use
* ⚙️ **Extensible** for future optimizations
