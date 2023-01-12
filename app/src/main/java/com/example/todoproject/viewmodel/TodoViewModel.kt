package com.example.todoproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.todoproject.model.TodoModel
import com.example.todoproject.model.repository.TodoRepository

class TodoViewModel(application: Application): AndroidViewModel(application) {
    private val mTodoRepository = TodoRepository(application)
    private val mTodoItems = mTodoRepository.getTodoList()

    fun insertTodo(todoModel: TodoModel) {
        mTodoRepository.insertTodo(todoModel)
    }

    fun getTodoList(): LiveData<List<TodoModel>> {
        return mTodoItems
    }
}