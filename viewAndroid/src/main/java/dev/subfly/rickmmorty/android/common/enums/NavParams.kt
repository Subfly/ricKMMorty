package dev.subfly.rickmmorty.android.common.enums

enum class NavParams(val argName: String) {

    ID("content_id"),
    TITLE("title");

    val routeParameter: String = "${this.argName}={${this.argName}}"

}