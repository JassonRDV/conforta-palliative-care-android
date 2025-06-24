package com.jasson.conforta.util

object Levenshtein {
    fun distance(s1: String, s2: String): Int {
        val s1Lower = s1.lowercase()
        val s2Lower = s2.lowercase()
        val dp = Array(s1Lower.length + 1) { IntArray(s2Lower.length + 1) }

        for (i in 0..s1Lower.length) {
            for (j in 0..s2Lower.length) {
                when {
                    i == 0 -> dp[i][j] = j
                    j == 0 -> dp[i][j] = i
                    else -> {
                        val cost = if (s1Lower[i - 1] == s2Lower[j - 1]) 0 else 1
                        dp[i][j] = minOf(
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1,
                            dp[i - 1][j - 1] + cost
                        )
                    }
                }
            }
        }
        return dp[s1Lower.length][s2Lower.length]
    }
}