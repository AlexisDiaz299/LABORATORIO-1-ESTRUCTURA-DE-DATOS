interface IBaseNumber {
    val value: Int
    fun getType(): NumberType
}

enum class NumberType { // Enum para definir los tipos de números.
    PRIME, EVEN, ODD
}

data class PrimeNumber(override val value: Int) : IBaseNumber {
    override fun getType() = NumberType.PRIME
}

data class EvenNumber(override val value: Int, val divisors: List<Int>) : IBaseNumber {
    override fun getType() = NumberType.EVEN
}

data class OddNumber(override val value: Int, val divisors: List<Int>) : IBaseNumber {
    override fun getType() = NumberType.ODD
}

class NumberFactory {
    fun createNumber(value: Int): IBaseNumber {  //Metodo para crear un numero basado en su valor.
        return when {
            isPrime(value) -> PrimeNumber(value)  //Si el numero es primo entonces crea un PrimeNumber.
            value % 2 == 0 -> EvenNumber(value, findDivisors(value))  // Si el numero es par se crea un EvenNumber.
            else -> OddNumber(value, findDivisors(value))  //Si el numero es impar entonces se crea un OddNumber.
        }
    }

    private fun isPrime(number: Int): Boolean {      //Metodo para verificar si un numero es primo.
        if (number <= 1) return false   
        return (2 until number).none { number % it == 0 }
    }

    private fun findDivisors(number: Int): List<Int> {       // Metodo para encontrar todos los divisores de un numero.
        return (1..number).filter { number % it == 0 }
    }
}
 
class NumberEvaluator(private val numberFactory: NumberFactory) { // Clase para evaluar un rango de numeros y ordenarlos por tipo.
    fun evaluateRange(n: Int): Map<NumberType, List<IBaseNumber>> {
        require(n > 0) { "N debe ser un numero positivo mayor que cero." }

        return (1..n).map { numberFactory.createNumber(it) }
                     .groupBy { it.getType() }
    }
}

fun main() { // Funcion principal para ejecutar el programa.
    try {
        val evaluator = NumberEvaluator(NumberFactory())
        val result = evaluator.evaluateRange(100)       // Evalua el rango de 1 a 100.

        println("Prime numbers count: ${result[NumberType.PRIME]?.size ?: 0}")          // Imprime el conteo de numeros de cada tipo.
        println("Even numbers count: ${result[NumberType.EVEN]?.size ?: 0}")
        println("Odd numbers count: ${result[NumberType.ODD]?.size ?: 0}")
    } catch (e: IllegalArgumentException) {
        println("Error: ${e.message}")
    }
}



//NOMBRE DEL ESTUDIANTE: JASON ALEXIS GARCIA DIAZ
//CARRERA: INGENIERIA EN DESARROLLO DE SOFTWARE