package com.example.myapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.ItemRowRepoAdapter
import com.example.myapplication.model.ReposResponseData
import com.example.myapplication.retrofit.ApiClient
import kotlinx.android.synthetic.main.fragment_all_repo.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllRepoFragment : Fragment() {
    lateinit var itemRowRepoAdapter: ItemRowRepoAdapter
    val repoList = ArrayList<ReposResponseData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_repo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemRowRepoAdapter = ItemRowRepoAdapter(repoList)
        val layoutManager2 = LinearLayoutManager(context)
        rcyrepo.layoutManager = layoutManager2
        rcyrepo.itemAnimator = DefaultItemAnimator()
        rcyrepo.adapter = itemRowRepoAdapter

        reposApi()
    }

    private fun reposApi(){

        val call: Call<ArrayList<ReposResponseData>> = ApiClient.getClient.reposList()
        call.enqueue(object : Callback<ArrayList<ReposResponseData>> {
            override fun onResponse(
                call: Call<ArrayList<ReposResponseData>>,
                response: Response<ArrayList<ReposResponseData>>
            ) {
                if (response.isSuccessful){

                    val data = response.body()
                    if (data != null) {
                        itemRowRepoAdapter.addAll(data)
                    }
                } else{
                    Toast.makeText(context,"No Response", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ArrayList<ReposResponseData>>, t: Throwable) {
                Toast.makeText(context,t.message, Toast.LENGTH_SHORT).show()
            }

        })

    }

}