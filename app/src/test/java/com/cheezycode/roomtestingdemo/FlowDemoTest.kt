package com.cheezycode.roomtestingdemo

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.*

import org.junit.Test

class FlowDemoTest {

    @Test
    fun getFlowTest() = runTest {
        val sut = FlowDemo()
        val result = sut.getFlow().toList()
        Assert.assertEquals(listOf(1,2), result)
    }
}