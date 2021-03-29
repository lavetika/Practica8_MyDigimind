package castro.diana.mydigimind.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import castro.diana.mydigimind.R
import castro.diana.mydigimind.Task
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.task_view.view.*

class HomeFragment : Fragment() {

    private var adapter: AdapterTasks? = null
    private lateinit var homeViewModel: HomeViewModel

    companion object{
        var tasks = ArrayList<Task>()
        var first = true
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        if(first){
            fillTasks()
            first = false
        }

        adapter = AdapterTasks(root.context, tasks)
        root.gridView.adapter = adapter

        return root
    }

    fun fillTasks(){
        tasks.add(Task("Practice 1", arrayListOf("Monday", "Sunday"), "17:30"))
        tasks.add(Task("Practice 2", arrayListOf("Friday", "Saturday"), "12:30"))
        tasks.add(Task("Practice 3", arrayListOf("Tuesday"), "20:40"))
        tasks.add(Task("Practice 4", arrayListOf("Thursday"), "8:00"))
        tasks.add(Task("Practice 5", arrayListOf("Wednesday"), "13:30"))
        tasks.add(Task("Practice 6", arrayListOf("Monday"), "7:30"))
        tasks.add(Task("Practice 7", arrayListOf("Sunday"), "11:00"))
        tasks.add(Task("Practice 8", arrayListOf("Saturday"), "14:50"))
    }


    private class AdapterTasks: BaseAdapter{
        var tasks = ArrayList<Task>()
        var context: Context? = null

        constructor(context: Context, tasks: ArrayList<Task>){
            this.context = context
            this.tasks = tasks
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var task = tasks[position]
            var inflator = LayoutInflater.from(context)
            var view =  inflator.inflate(R.layout.task_view, null)

            view.tv_title.setText(task.title)
            view.tv_time.setText(task.time)
            view.tv_days.setText(task.days.toString())

            return view
        }

        override fun getItem(position: Int): Any {
            return tasks[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return tasks.size
        }

    }

}