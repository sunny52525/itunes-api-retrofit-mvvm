package org.shaun.itunessearch


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iammert.library.ui.multisearchviewlib.MultiSearchView
import org.shaun.itunessearch.adapter.GridAdapter
import org.shaun.itunessearch.modelclass.iTunesItem
import org.shaun.itunessearch.viemodel.ITunesViewModel


private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ITunesViewModel
    private val adapter = GridAdapter(emptyList<iTunesItem>())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val itemsRv=findViewById<RecyclerView>(R.id.items_rv)
        itemsRv.layoutManager = GridLayoutManager(this, 3)
        itemsRv.adapter = adapter

            viewModel=ViewModelProviders.of(this).get(ITunesViewModel::class.java)

            viewModel.getItemList()?.observe(this, Observer {
                if (it != null) {

                    Log.d(TAG, "getData: ${it.results}")
                    adapter.updateData(it.results!!)
                    adapter.notifyDataSetChanged()
                } else {
                    Log.d(TAG, "getData: NULL")
                }
            })


        val multiSearchView=findViewById<MultiSearchView>(R.id.multiSearchView)
        multiSearchView.setSearchViewListener(object :
            MultiSearchView.MultiSearchViewListener {
            override fun onItemSelected(index: Int, s: CharSequence) {
                Log.v("TEST", "onItemSelected: index: $index, query: $s")
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
                Log.d(TAG, "onSearchItemRemoved: ${viewModel.itunesLiveData?.value?.results}")
                adapter.updateData(viewModel.itunesLiveData?.value?.results!!   )
                adapter.notifyDataSetChanged()
            }
        })
    }


    fun getNewData(query: String) {
        viewModel.getNewData(query)
        Log.d(TAG, "getNewData: ${viewModel.itunesLiveData?.hasActiveObservers()}")

    }


}