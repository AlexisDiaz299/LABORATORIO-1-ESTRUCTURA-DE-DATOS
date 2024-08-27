interface IFizzBuzz {
    fun printFizzBuzz(start: Int, end: Int)
}

// Clase que implementa la logica
class FizzBuzz : IFizzBuzz {
    // Se aplica el metodo para imprimir FizzBuzz.
    override fun printFizzBuzz(start: Int, end: Int) {
        if (start > end || start < 1 || end > 100) {
            throw IllegalArgumentException("El rango debe ser válido y estar entre 1 y 100.")
        }


        for (i in start..end) {
            print(evaluateNumber(i)) // Imprime el resultado evaluado.
            if (i % 10 == 0 || i == end) println() else print("\t")
        }
    }

    // Metodo que evalua un numero y retorna "Fizz", "Buzz", "FizzBuzz", o el numero.
    private fun evaluateNumber(number: Int): String {
        return when {
            number % 15 == 0 -> "FizzBuzz" // Si es Divisible por 3 y 5.
            number % 3 == 0 -> "Fizz" // Si es Divisible por 3.
            number % 5 == 0 -> "Buzz" //Si es Divisible por 5.
            else -> number.toString() //Si No es divisible por 3 o 5.
        }
    }
}

// Funcion principal para ejecutar el programa.
fun main() {
    try {
        val fizzBuzz = FizzBuzz()
        fizzBuzz.printFizzBuzz(1, 100) // Ejecuta FizzBuzz en el rango de 1 a 100.
    } catch (e: IllegalArgumentException) {
        println("Error: ${e.message}")
    }
}


//NOMBRE DEL ESTUDIANTE: JASON ALEXIS GARCIA DIAZ
//CARRERA: INGENIERIA EN DESARROLLO DE SOFTWARE