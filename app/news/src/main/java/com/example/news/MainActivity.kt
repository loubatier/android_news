package com.example.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.news.fragments.ListCategoryFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = ListCategoryFragment()

        change(fragment)
    }
}

fun FragmentActivity.change(fragment: Fragment){
    supportFragmentManager.beginTransaction().apply {
        //replacer le précédent fragment, s'il existe
        replace(R.id.list_container, fragment)
        //ajouter la transaction dans la stack
        addToBackStack(null)
    }.commit()
}
