package com.test.utilities

sealed class FilterItem(val filter: String) {
    object Stars : FilterItem("stars")
    object Updated : FilterItem("updated")
}