package com.example.recyclerview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

const val TAG = "MyTag"
class PersonListFragment: Fragment() {
    private lateinit var personRecyclerView: RecyclerView
    private var adapter: PersonAdapter? = null

    private val myViewModel: MyViewModel by lazy {
        ViewModelProviders.of(this).get(MyViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_person_list, container, false)
        this.personRecyclerView = view.findViewById(R.id.person_recycler_view)
        this.personRecyclerView.layoutManager = LinearLayoutManager(this.context)

        this.updateUI()

        return view
    }

    private fun updateUI() {
        val people = this.myViewModel.people
        this.adapter = PersonAdapter(people)
        this.personRecyclerView.adapter = adapter
    }

    companion object {
        fun newInstance(): PersonListFragment {
            return PersonListFragment()
        }
    }
    private inner class PersonHolder(view: View): RecyclerView.ViewHolder(view)
    {
        val nameTextView: TextView = this.itemView.findViewById(R.id.name_view)
        val ageTextView: TextView = this.itemView.findViewById(R.id.age_view)
        val coolCheck: CheckBox = this.itemView.findViewById(R.id.super_view)

    }

    private inner class PersonAdapter(var people: List<Person>)
        : RecyclerView.Adapter<PersonHolder>()
    {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonHolder {
            Log.v(TAG, "onCreateViewHolder()")
            val view = layoutInflater.inflate(
                R.layout.list_item_person,
                parent,
                false
            )
            return PersonHolder(view)
        }

        override fun onBindViewHolder(holder: PersonHolder, position: Int) {
            val person = this.people[position]

            holder.nameTextView.text = person.name
            holder.ageTextView.text = person.age.toString()
            holder.coolCheck.isChecked = person.isSuperCool
        }

        override fun getItemCount(): Int {
            return this.people.size
        }


    }
}