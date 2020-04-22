package com.example.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragments.adapter.CategoryAdapter
import com.example.fragments.model.Category

class RecyclerFragment : Fragment() {

    private lateinit var adapter: CategoryAdapter
    private lateinit var recyclerView: RecyclerView

    private val categories = listOf(
        Category("Politique", "Description politique", "https://media.lesechos.com/api/v1/images/view/5d81c2023e45465c7641b968/1280x720/0601890358164-web-tete.jpg"),
        Category("Économie", "Description jeux vidéos", "https://www.ecossimo.com/wp-content/uploads/finance-et-economie.jpg"),
        Category("Éducation", "Description santé", "https://www.education.gouv.fr/sites/default/files/styles/banner_1340x730/public/2020-01/education-a-la-sexualite-boj-men-debussy190927-6164.jpg?h=26569e11&itok=w3bQ664r"),
        Category("Pandémie", "Description environnement", "https://file1.topsante.com/var/topsante/storage/images/1/3/2/2/1322378/pandemie-endemie-epidemie-quelle-est-difference.jpeg?alias=width400&size=x100&format=jpeg"),
        Category("Sciences", "Description sciences", "https://www.espace-sciences.org/sites/espace-sciences.org/files/styles/w375h226/public/images/conferences/conferences/avrieux_cfotolia.jpg?itok=Qr5E-oG1"),
        Category("Écologie", "Description écologie", "https://www.orientation-environnement.fr/wp-content/uploads/2019/09/emploi-formations-metiers-ecologie.jpg")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recycler_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)
        bindRecyclerView()

    }

    private fun bindRecyclerView() {

        adapter = CategoryAdapter(categories)
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        recyclerView.adapter = adapter

    }
}