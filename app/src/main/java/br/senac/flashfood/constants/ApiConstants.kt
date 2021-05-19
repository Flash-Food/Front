package br.senac.flashfood.constants


enum class ApiConstants(value: String) {
    BASE_URL("https://flash-foods.herokuapp.com/flash-food/"),
    HEADER_NAME_AUTH("authorization"),
    PREFIX("Bearer");

    val value = value
}