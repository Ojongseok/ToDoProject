package com.example.todoproject.model.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.todoproject.model.TodoModel
import com.example.todoproject.model.db.TodoDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoRepository(application: Application) {
    private val mTodoDatabase = TodoDatabase.getInstance(application)
    private val mTodoDAO = mTodoDatabase.todoDao()
    private val mTodoItems = mTodoDAO.getTodoList()

    fun getTodoList(): LiveData<List<TodoModel>> {
        return mTodoItems
    }

    fun insertTodo(todoModel: TodoModel) {
        CoroutineScope(Dispatchers.IO).launch {
            mTodoDAO.insertTodo(todoModel)
        }
    }
}