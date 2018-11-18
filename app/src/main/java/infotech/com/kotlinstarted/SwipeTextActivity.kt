package infotech.com.kotlinstarted

fun main(){

    val firstPerson = Person("John",20,"Programmer")
    val secondPerson = Person("Dave",30,"Bus Driver")

    val olderPerson = if (firstPerson.age>secondPerson.age) firstPerson else secondPerson

    run{
        if (firstPerson.age>secondPerson.age) firstPerson else secondPerson
    }.printPerson()

    val r1 = with(firstPerson){
        age += 1
        "Age is now $age"
    }
    println(r1)

    val res = firstPerson.run {
        age += 1
        "Age is now $age"
    }
    println(res)

}

data class Person(var name:String,var age:Int, var job:String){

    fun printPerson() = println(this.toString())

    fun String.println() = println(this)
}

