package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView
import ru.skillbranch.devintensive.utils.Utils

fun User.toUserView() : UserView {
    val nickName:String = Utils.transliteration("$firstName $lastName")
    val initials:String? = Utils.toInitials("$firstName", "$lastName")
    val status:String = if(lastVisit==null) "Еще ни разу не был" else if(isOnline) "online" else "Последний раз был ${lastVisit!!.humanizeDiff()}"

    return UserView(
        id,
        "$firstName $lastName",
        nickName = nickName,
        avatar = avatar,
        initials = initials,
        status = status
    )
}