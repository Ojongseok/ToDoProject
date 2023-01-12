package com.example.todoproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoproject.R
import com.example.todoproject.databinding.ActivityMainBinding
import com.example.todoproject.model.TodoModel
import com.example.todoproject.view.adapter.TodoListAdapter
import com.example.todoproject.viewmodel.TodoViewModel
import kotlinx.android.synthetic.main.dialog_add_todo.view.*
import kotlinx.android.synthetic.main.item_todo.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var mTodoViewModel : TodoViewModel
    private lateinit var mTodoListAdapter: TodoListAdapter
    private val mTodoItems = ArrayList<TodoModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initRecyclerView()
        initAddButton()
        initViewModel()
    }

    private fun initViewModel() {
        mTodoViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(TodoViewModel::class.java)

        mTodoViewModel.getTodoList().observe(this) {
            mTodoListAdapter.setTodoItems(it)
        }
    }

    private fun initAddButton() {
        binding.btnAddTodo.setOnClickListener {
            openAddTodoDialog()
        }
    }

    private fun openAddTodoDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_todo, null)
        val dialog = AlertDialog.Builder(this)
            .setTitle("추가하기")
            .setView(dialogView)
            .setPositiveButton("확인") { dialogInterface, i ->
                val title = dialogView.et_todo_title.text.toString()
                val description = dialogView.et_todo_description.text.toString()
                val createdDate = SimpleDateFormat("yyyy.MM.dd HH:mm").format(Date(System.currentTimeMillis()))

                mTodoViewModel.insertTodo(TodoModel(0,title,description,createdDate))
            }
            .setNegativeButton("취소", null)
            .create()
        dialog.show()
    }

    private fun initRecyclerView() {
        mTodoListAdapter = TodoListAdapter()

        binding.rlTodoList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = mTodoListAdapter
        }
    }
}