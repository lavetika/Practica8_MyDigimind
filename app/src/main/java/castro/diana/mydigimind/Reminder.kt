package castro.diana.mydigimind

import java.io.Serializable

data class Reminder (var days: String, var time: String, var name: String): Serializable {
}