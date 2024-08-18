@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package util

interface FileHasher {
    fun hash(data: ByteArray): String
}

expect object FileHasherFactory {
    /**
     * Creates a new instance of [FileHasher]
     */
    fun createMd5Hasher(): FileHasher
}
