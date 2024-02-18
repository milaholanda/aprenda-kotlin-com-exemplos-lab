import kotlin.system.exitProcess

// Niveis de curso
enum class Levels {
	BEGINNER, INTERMEDIATE, ADVANCED    
}

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

// Classe genérica para os cursos disponíveis.
class Course (
    val title:String, 
    val duration:Int,
    val level:Levels,
    val content:List<String>,
    var enrolledStudents:Int = 0
) {
    val listOfStudents:MutableList<String> = mutableListOf()

    // Matricula de aluno
    fun addStudent() {
        val newStudent = userlist.last().name
        listOfStudents.add(newStudent)
        enrolledStudents++
        println("Now there is/are $enrolledStudents students enrolled: $listOfStudents")
    }
}

// Conteúdo dos cursos disponíveis
val androidDeveloperContent = listOf("Java","Kotlin","Android")
val UXDesignContent = listOf("Figma", "Psychology","Design")
val MobileDeveloperContent = listOf("React","Flutter","Swift")

// Cursos disponíveis
val androidDeveloper = Course("Android Developer", 40, Levels.BEGINNER, androidDeveloperContent)
val UXDesign = Course("UX Designer", 30, Levels.INTERMEDIATE, UXDesignContent)
val MobileDeveloper = Course("Mobile Developer", 50, Levels.ADVANCED, MobileDeveloperContent)
val courseList:List<Course> = listOf(androidDeveloper, UXDesign, MobileDeveloper)

// User
class User(val name:String, val email:String, val password:String) {
    val enrolledCourses:MutableList<Course> = mutableListOf()
    
    fun verifyCourses() {
        if (enrolledCourses.isEmpty()) {
            println("You aren't enrolled in any course, would you like to enroll in a course?")
            verifyInput(yesAction = {
                println("""Ok, what course would you like to enroll in? Type the correspondent number!
                1 - Android Developer (Kotlin and Java)
                2 - UXDesign
                3 - Mobile Developer (Flutter, React and Swift)""")

                var input = readLine()!!.toInt()
                while (input !in setOf(1,2,3)) {
                    println("Type in a valid input")
                    input = readLine()!!.toInt()
                }
                when {
                    input.equals(1) -> androidDeveloper.addStudent()
                    input.equals(2) -> UXDesign.addStudent()
                    input.equals(3) -> MobileDeveloper.addStudent()
                    input.equals("BREAK") -> exitProcess(0)
                }
            }, noAction = {println("ok, see you later o/")})
        } else {
            var i = 0
            for (course in enrolledCourses) {
                i++
            }
            println("You are enrolled in $i courses:")
            for (course in enrolledCourses) {
                println("- ${enrolledCourses.last().title}")
                println("Let's start studying!")
            }
        }
    }
}

val userlist:MutableList<User> = mutableListOf()

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

    userlist.last().verifyCourses()
}
