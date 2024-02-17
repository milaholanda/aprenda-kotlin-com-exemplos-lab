import kotlin.system.exitProcess

// Niveis de curso
enum class Levels {
	BEGINNER, INTERMEDIATE, ADVANCED    
}

// Classe genérica para os cursos disponíveis.
class Course (
    val title:String, 
    val duration:Int,
    val level:Levels,
    val content:MutableList<String>,
    var enrolledStudents:Int = 0
) {
    // adiciona novos conteúdos à ementa da formação
    fun addContent() {
        println("Type the new content to be added to the course:")
        val newContent = readLine()!!.toString()
        content.add(newContent)
        println("A nova ementa do curso é $content")
    }

    val listOfStudents:MutableList<String> = mutableListOf()

    // Matricula de aluno
    fun addStudent() {
        println("What's the name of the new student?")
        val newStudent = readLine()!!.toString()
        listOfStudents.add(newStudent)
        enrolledStudents++
        println("Now there is/are $enrolledStudents students enrolled: $listOfStudents")
    }
}

// Conteúdo dos cursos disponíveis
val androidDeveloperContent = mutableListOf("Java","Kotlin","Android")
val UXDesignContent = mutableListOf("Figma", "Psychology","Design")
val MobileDeveloperContent = mutableListOf("React","Flutter","Swift")

// Cursos disponíveis
val androidDeveloper = Course("Android Developer", 40, Levels.BEGINNER, androidDeveloperContent)
val UXDesign = Course("UX Designer", 30, Levels.INTERMEDIATE, UXDesignContent)
val MobileDeveloper = Course("Mobile Developer", 50, Levels.ADVANCED, MobileDeveloperContent)
val courseList:List<Course> = listOf(androidDeveloper, UXDesign, MobileDeveloper)

// User
class User(val name:String, val email:String, val password:String) 
val userlist:MutableList<User> = mutableListOf()

//Adds a new user to the system
fun addUser() {
    println("Type your username:")
    val username = readLine()!!.toString()
    println("Now type your e-mail:")
    val useremail = readLine()!!.toString()
    println("Now type your password:")
    val userpassword = readLine()!!.toString()
    val newUser:User = User(username, useremail, userpassword) 
    userlist.add(newUser)
    println("New user added: ${newUser.name} \n")
}

// Verifies if the user has been created already
fun verifyuser() {
    println("What's your username?")
    var input:String? = readLine()
    var userfound:Boolean = false
    for (user in userlist) {
        if (input == user.name) {
            println("Welcome back $input \n")
            userfound = true
        }
    }

    if (userfound == false) {
        println("Your username wasn't found, please create a new user: \n")
        addUser()
    }
}

// verify Input, automatizes the operation of verifying answers
fun verifyInput(yesAction: () -> Unit, noAction: () -> Unit) {
    var input = readLine()?.uppercase()
    while (input !in setOf("Y","N","BREAK")) {
        println("Type in a valid input")
        input = readLine()?.uppercase()
    }
    when {
        input.equals("Y", true) -> yesAction()
        input.equals("N", true) -> noAction()
        input.equals("BREAK", true) -> exitProcess(0)
    }
}

// Main
fun main() {
    println("Welcome to DIO! Would you like to create a new user? Y for yes, N for no:")

    verifyInput(yesAction = {addUser()}, noAction =  {verifyuser()})

    println ("Ok! Let's check the courses available! \n")
    for (course in courseList) {
        println("-- ${course.title} --")
        println("- Duration: ${course.duration}h")
        println("- Level: ${course.level}")
        println("- Content: ${course.content.joinToString()}")
        println("- Students enrolled: ${course.enrolledStudents} \n")
        println("")
    }
}
