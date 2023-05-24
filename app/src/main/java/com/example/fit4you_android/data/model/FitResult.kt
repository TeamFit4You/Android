package com.example.fit4you_android.data.model

data class FitResult(
    val setN: String,
    val totalAC: Double,
    val countOne: Int,
    val countTwo: Int,
    val countThr: Int,
    val accOne: Double,
    val accTwo: Double,
    val accThr: Double,
    val feedOne: String,
    val feedTwo: String,
    val feedThr: String,
) {
    fun getStringtotalAC() = totalAC.toString()
    fun getStringcountOne() = countOne.toString()
    fun getStringcountTwo() = countTwo.toString()
    fun getStringcountThr() = countThr.toString()
    fun getStringaccOne() = accOne.toString()
    fun getStringaccTwo() = accTwo.toString()
    fun getStringaccThr() = accThr.toString()
}
