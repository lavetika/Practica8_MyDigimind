package castro.diana.mydigimind

import java.io.Serializable

class Carrito: Serializable {

    var reminders = ArrayList<Reminder>()

    fun add(p: Reminder):Boolean{
        return reminders.add(p)
    }

}