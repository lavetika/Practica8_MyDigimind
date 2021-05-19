package castro.diana.mydigimind.ui.dashboard

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import castro.diana.mydigimind.R
import castro.diana.mydigimind.Task
import castro.diana.mydigimind.ui.home.HomeFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DashboardFragment : Fragment() {


    private lateinit var storage : FirebaseFirestore
    private lateinit var  usuario : FirebaseAuth
    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(

            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        //Instanciar firebase
        storage = FirebaseFirestore.getInstance()
        usuario = FirebaseAuth.getInstance()

        root.btn_time.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)

                btn_time.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(root.context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }

        root.btn_done.setOnClickListener {
            var title = et_title.text.toString()
            var time = btn_time.text.toString()
            var days = ArrayList<String>()

            /*if(chbx_monday.isChecked)
                days.add("Monday")
            if(chbx_tuesday.isChecked)
                days.add("Tuesday")
            if(chbx_wednesday.isChecked)
                days.add("Wednesday")
            if(chbx_thursday.isChecked)
                days.add("Thursday")
            if(chbx_friday.isChecked)
                days.add("Friday")
            if(chbx_saturday.isChecked)
                days.add("Saturday")
            if(chbx_sunday.isChecked)
                days.add("Sunday")

                var task = Task(title, days, time)
            HomeFragment.tasks.add(task)

            */

            val actividad = hashMapOf(
                "actividad" to et_title.text.toString(),
                "email" to usuario.currentUser.email.toString(),
                "do" to chbx_sunday.isChecked,
                "lu" to chbx_monday.isChecked,
                "ma" to chbx_tuesday.isChecked,
                "mi" to chbx_wednesday.isChecked,
                "ju" to chbx_thursday.isChecked,
                "vi" to chbx_friday.isChecked,
                "sa" to chbx_saturday.isChecked,
                 "tiempo" to btn_time.toString())

            storage.collection("actividades")
                    .add(actividad)
                    .addOnSuccessListener {
                        Toast.makeText(root.context, "New task added", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(root.context, "Error: try again", Toast.LENGTH_SHORT).show()
                    }
        }
        return root
    }
}