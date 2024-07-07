@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

actual class MyText actual constructor(text: String)

// Common Code (Shared)
actual class MyButton actual constructor(text: String, onClick: () -> Unit)