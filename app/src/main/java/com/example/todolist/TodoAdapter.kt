package com.example.todolist

import android.icu.text.CaseMap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter (
        private val todos : MutableList<Todo>
        ): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class TodoViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    fun addTodo(todo: Todo){
        todos.add(todo)
        notifyItemInserted(todos.size -1)
    }

    fun deleteDoneTodos(){
        todos.removeAll{ todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean){
        if (isChecked){
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.itemView.apply {
            tvTodoTitle = curTodo.title
            curTodo.isChecked.also { it.also { cbDone.isChecked = it } }
            toggleStrikeThrough(tvTodoTitle, curTodo.isChecked)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvTodoTitle, isChecked)
                curTodo.isChecked =!curTodo.isChecked
            }
        }

    }

    override fun getItemCount(): Int {
        return todos.size

    }
}