package com.org.test.geminisample.utils.ktx

val List<String>.toStringList: String
    get() = this.joinToString(", ")