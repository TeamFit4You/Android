package com.example.fit4you_android.ui.view.basicstatuscheck.questions

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fit4you_android.R

class BasicQuestions : Fragment() {

    companion object {
        fun newInstance() = BasicQuestions()
    }

    private lateinit var viewModel: BasicQuestionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_basic_questions, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BasicQuestionsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}