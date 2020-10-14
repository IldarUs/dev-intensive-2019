package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?) : Pair<String?,String?> {
        val parts : List<String>? = fullName?.split(" ")
        var firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)
        if(firstName != null && firstName.isEmpty()) firstName = null
        if(lastName != null && lastName.isEmpty()) lastName = null
        return Pair(firstName, lastName)
    }

    fun transliteration(payload: String, devider: String = ""): String {
        var result: String = ""
        for (c in payload) {
            result += when (c.toString()) {
                "а" -> "a"
                "б" -> "b"
                "в" -> "v"
                "г" -> "g"
                "д" -> "d"
                "е" -> "e"
                "ё" -> "e"
                "ж" -> "zh"
                "з" -> "z"
                "и" -> "i"
                "й" -> "i"
                "к" -> "k"
                "л" -> "l"
                "м" -> "m"
                "н" -> "n"
                "о" -> "o"
                "п" -> "p"
                "р" -> "r"
                "с" -> "s"
                "т" -> "t"
                "у" -> "u"
                "ф" -> "f"
                "х" -> "h"
                "ц" -> "c"
                "ч" -> "ch"
                "ш" -> "sh"
                "щ" -> "sh'"
                "ъ" -> ""
                "ы" -> "i"
                "ь" -> ""
                "э" -> "e"
                "ю" -> "yu"
                "я" -> "ya"
                "А" -> "A"
                "Б" -> "B"
                "В" -> "V"
                "Г" -> "G"
                "Д" -> "D"
                "Е" -> "E"
                "Ё" -> "E"
                "Ж" -> "ZH"
                "З" -> "Z"
                "И" -> "I"
                "Й" -> "I"
                "К" -> "K"
                "Л" -> "L"
                "М" -> "M"
                "Н" -> "N"
                "О" -> "O"
                "П" -> "P"
                "Р" -> "R"
                "С" -> "S"
                "Т" -> "T"
                "У" -> "U"
                "Ф" -> "F"
                "Х" -> "H"
                "Ц" -> "C"
                "Ч" -> "CH"
                "Ш" -> "SH"
                "Щ" -> "SH'"
                "Ъ" -> ""
                "Ы" -> "I"
                "Ь" -> ""
                "Э" -> "E"
                "Ю" -> "YU"
                "Я" -> "YA"
                else -> if(c.toString()==" " && !devider.isNullOrEmpty()) devider else c.toString()
            }
        }
        return result
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        var result: String? = null
        if(!firstName?.trim().isNullOrEmpty()) result = firstName?.substring(0, 1)
        if(!lastName?.trim().isNullOrEmpty()) result = (result ?: "") + lastName?.substring(0, 1)

        return result
    }
}