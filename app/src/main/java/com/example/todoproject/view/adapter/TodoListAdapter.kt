package com.example.todoproject.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todoproject.databinding.ItemTodoBinding
import com.example.todoproject.model.TodoModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TodoListAdapter() : RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {
    private var todoItems = listOf<TodoModel>()
    inner class TodoViewHolder(private val binding: ItemTodoBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(todoModel: TodoModel) {
            binding.tvTodoTitle.text = todoModel.title
            binding.tvTodoDescription.text = todoModel.description
            binding.tvTodoCreatedDate.text = todoModel.createdDate.toString()

        }

    }

    fun setTodoItems(todoItems: List<TodoModel>) {
        this.todoItems = todoItems
        notifyDataSetChanged()
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<TodoModel>() {
            override fun areItemsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = ItemTodoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TodoViewHolder(view)
    }
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todoItems[position])
    }
    override fun getItemCount() = todoItems.size
}