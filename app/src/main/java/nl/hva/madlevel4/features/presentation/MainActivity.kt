package nl.hva.madlevel4.features.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import nl.hva.madlevel4.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()
        setToolbarTitles()
    }

    private fun setupNavigation() {
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(findNavController(R.id.navHostFragment))
    }

    private fun setToolbarTitles() {
        findNavController(R.id.navHostFragment)
                .addOnDestinationChangedListener { _, destination, _ ->
                    when (destination.id) {
                        R.id.bucketListFragment -> {
                            iconDelete.visibility = View.VISIBLE
                            supportActionBar?.title = "Bucket List"
                        }
                        R.id.addItemFragment -> {
                            iconDelete.visibility = View.INVISIBLE
                            supportActionBar?.title = "Create Bucket List Item"
                        }
                    }
                }
    }

    override fun onSupportNavigateUp() = findNavController(R.id.navHostFragment).navigateUp()

}
