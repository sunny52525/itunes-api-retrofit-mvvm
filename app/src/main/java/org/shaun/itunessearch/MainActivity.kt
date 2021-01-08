package org.shaun.itunessearch


import android.content.Context
import android.content.res.Configuration
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.iammert.library.ui.multisearchviewlib.MultiSearchView
import org.shaun.itunessearch.adapter.GridAdapter
import org.shaun.itunessearch.databinding.ActivityMainBinding
import org.shaun.itunessearch.viemodel.ITunesViewModel


private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ITunesViewModel
    private lateinit var binding: ActivityMainBinding
    private val adapter = GridAdapter(emptyList())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.itemsRv.layoutManager = GridLayoutManager(
            this,
            getColumns()
        )
        binding.itemsRv.adapter = adapter

        viewModel = ViewModelProviders.of(this).get(ITunesViewModel::class.java)

        viewModel.getItemList()?.observe(this, Observer {
            if (it != null) {
                Log.d(TAG, "getData: ${it.results}")
                adapter.updateData(it.results!!)
                adapter.notifyDataSetChanged()
            } else {
                Log.d(TAG, "getData: NULL")
            }

            binding.progressBar.visibility = View.GONE
        })


        binding.multiSearchView.setSearchViewListener(object :
            MultiSearchView.MultiSearchViewListener {
            override fun onItemSelected(index: Int, s: CharSequence) {
                Log.v("TEST", "onItemSelected: index: $index, query: $s")
                getNewData(s.toString())
            }

            override fun onTextChanged(index: Int, s: CharSequence) {
                Log.v("TEST", "changed: index: $index, query: $s")
            }

            override fun onSearchComplete(index: Int, s: CharSequence) {
                Log.v("TEST", "complete: index: $index, query: $s")
                getNewData(s.toString())


            }

            override fun onSearchItemRemoved(index: Int) {
                Log.v("TEST", "remove: index: $index")

            }
        })

        binding.appCompatTextView.setOnClickListener {
            test()
        }
    }

    private fun getColumns(): Int {
        return if (getOrientation() == Configuration.ORIENTATION_LANDSCAPE) 4 else 3
    }


    fun getNewData(query: String) {
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getNewData(query)
    }

    private fun getOrientation() = resources.configuration.orientation

    fun test() {
        val query = "SELECT itunes_data from itunes_table where artist IN ('lorde') ;"
        val db: SQLiteDatabase =
            baseContext.openOrCreateDatabase("itunes_database", Context.MODE_PRIVATE, null)
        val querys = db.rawQuery(query, null)
        querys.use {
            if (it.moveToLast()) {
                with(querys) {
                    val string = getString(0)
                    val s2=""
                    val s3 ="skd"
                    Log.d(TAG, "test: $string \n $s2 \n $s3")
                }
            }else{
                Log.d(TAG, "test: kk")
            }
        }

    }


}
