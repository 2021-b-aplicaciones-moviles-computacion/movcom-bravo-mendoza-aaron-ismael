package com.example.supermaxi

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    var totalLista = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var inicioFragment = InicioFragment()
        var promosFragment = PromosFragment()
        var tarjetaFragment = TarjetaFragment()
        var listasFragment = ListasFragment()
        var masFragment = MasFragment()

        val navegacion = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navegacion.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_inicio -> {
                    setCurrentFragment(inicioFragment)
                    true
                }
                R.id.nav_promos -> {
                    setCurrentFragment(promosFragment)
                    true
                }
                R.id.nav_tarjeta -> {
                    setCurrentFragment(tarjetaFragment)
                    true
                }
                R.id.nav_listas -> {
                    setCurrentFragment(listasFragment)
                    true
                }
                R.id.nav_mas -> {
                    setCurrentFragment(masFragment)
                    true
                }
                else -> {
                    false
                }
            }
        }

    }

    fun setCurrentFragment(framento: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.containerView, framento)
            commit()
        }
    }




}