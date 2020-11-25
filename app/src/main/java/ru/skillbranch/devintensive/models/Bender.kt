package ru.skillbranch.devintensive.models

class Bender(var status:Status = Status.NORMAL, var question: Question = Question.NAME) {

    fun askQuestion(): String = when (question) {
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {
        var isValid = question.isValidate(answer)
        return when {
            !isValid.first -> "${isValid.second}\n${question.question}" to status.color
            question.answers.contains(answer.toLowerCase().trim()) -> {
                question = question.nextQuestion()
                "Отлично - это правильный ответ!\n${question.question}" to status.color
            }
            else -> {
                status = status.nextStatus()
                "Это не правильный ответ!\n${question.question}" to status.color
            }
        }
    }

    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus(): Status {
            return if(this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]
            } else {
                values()[0]
            }
        }
    }

    enum class Question(val question:String, val answers:List<String>) {
        NAME("Как меня зовут?", listOf("бендер","bender")) {
            override fun nextQuestion(): Question = PROFESSION
            override fun isValidate(answer: String): Pair<Boolean, String> {
                return (answer.trim().firstOrNull()?.isUpperCase()  ?: false) to answer.trim().firstOrNull().toString() //"Имя должно начинаться с заглавной буквы"
            }
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик","bender")) {
            override fun nextQuestion(): Question = MATERIAL
            override fun isValidate(answer: String): Pair<Boolean, String> {
                return (answer.trim().firstOrNull()?.isLowerCase()  ?: false) to "Профессия должна начинаться со строчной буквы"
            }
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")) {
            override fun nextQuestion(): Question = BDAY
            override fun isValidate(answer: String): Pair<Boolean, String> {
                return answer.trim().contains(Regex("\\d")).not() to "Материал не должен содержать цифр"
            }
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun nextQuestion(): Question = SERIAL
            override fun isValidate(answer: String): Pair<Boolean, String> {
                return answer.trim().contains(Regex("^[0-9]*$")) to "Год моего рождения должен содержать только цифры"
            }
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun nextQuestion(): Question = IDLE
            override fun isValidate(answer: String): Pair<Boolean, String> {
                return answer.trim().contains(Regex("^[0-9]{7}$")) to "Серийный номер содержит только цифры, и их 7"
            }
        },
        IDLE("На этом все, вопросов больше нет!", listOf()) {
            override fun nextQuestion(): Question = IDLE
            override fun isValidate(answer: String): Pair<Boolean, String> {
                return true to ""
            }
        };

        abstract fun nextQuestion():Question
        abstract fun isValidate(answer: String): Pair<Boolean,String>
    }
}