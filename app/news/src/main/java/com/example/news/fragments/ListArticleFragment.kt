package com.example.news.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.adapters.ArticleAdapter
import com.example.news.models.Article
import com.example.news.repositories.Articlepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListArticleFragment : Fragment() {

    private lateinit var adapter: ArticleAdapter
    private lateinit var recyclerView: RecyclerView

    private val repository = Articlepository()

    private val query: String by lazy {
        arguments?.getString(ARGS_QUERY) ?: ""
    }

    companion object {
        const val ARGS_QUERY = "ARGS_QUERY"
        fun newInstance(query: String):ListArticleFragment {
            return ListArticleFragment().apply {

                arguments = bundleOf(ARGS_QUERY to query)

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_article_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        lifecycleScope.launch {
            getData()
        }
    }

    private suspend fun getData() {
        withContext(Dispatchers.IO) {
            val result = repository.list(query)
            bindData(result)
        }
    }

    private suspend fun bindData(result: List<Article>) {
        withContext(Dispatchers.Main) {
            //afficher les données dans le recycler

            Log.d("Articles $query", result.toString())

            recyclerView = activity!!.findViewById<RecyclerView>(R.id.article_recycler_view)

            adapter = ArticleAdapter(result)

            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
        }
    }
}