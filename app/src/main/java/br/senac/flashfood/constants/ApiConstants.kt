package br.senac.flashfood.constants


enum class ApiConstants(value: String) {
    BASE_URL("https://flash-foods.herokuapp.com/flash-food/"),
    LOCAL_URL("http://10.0.2.2:8080/flash-food/"),
    HEADER_NAME_AUTH("authorization"),
    PREFIX("Bearer");

    val value = value
}