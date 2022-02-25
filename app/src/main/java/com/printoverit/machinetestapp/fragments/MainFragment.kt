package com.printoverit.machinetestapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.printoverit.machinetestapp.adapters.TestAdapters
import com.printoverit.machinetestapp.api.TestAPI
import com.printoverit.machinetestapp.databinding.FragmentMainBinding
import com.printoverit.machinetestapp.dataclasses.Data
import com.printoverit.machinetestapp.dataclasses.Meme
import com.printoverit.machinetestapp.util.MyApplication
import com.printoverit.machinetestapp.viewmodels.TestViewModel
import com.printoverit.machinetestapp.viewmodels.TestViewModelFactory

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? =null
    private val binding get()= _binding!!
    lateinit var testViewModel: TestViewModel
    private var userList:List<Meme>? =null
    private val mAdapter by lazy { TestAdapters() }
    private val mAdapter2 by lazy { TestAdapters() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     val memeRepository= (activity?.application as MyApplication).testRepository
     testViewModel=ViewModelProvider(this,TestViewModelFactory(memeRepository)).get(TestViewModel::class.java)
        }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentMainBinding.inflate(inflater, container, false)
        binding.lifecycleOwner=this
        binding.viewModel = testViewModel
        loadOldData()
        binding.memeRecycylerview.layoutManager= LinearLayoutManager(requireContext())
        binding.memeSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
               return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                        if(newText == ""){
                            loadOldData()
                        }else{
                            testViewModel.loadSearchedMemes(newText)
                            testViewModel.getOfflineMemes().observe(viewLifecycleOwner,{item->
                                item.data?.let { mAdapter2.setData(item.data) }
                                userList=item.data.memes
                                binding.memeRecycylerview.adapter=mAdapter2
                                mAdapter2.notifyDataSetChanged()
                            })
                        }
                }
                return true
            }
        })
        return  binding.root
    }
    private fun loadOldData() {
        testViewModel.loadMemes()
        testViewModel.getMemes().observe(requireActivity(),{item->
            item.data?.let { mAdapter.setData(item.data) }
            userList=item.data.memes
            mAdapter.notifyDataSetChanged()
        })
        binding.memeRecycylerview.adapter=mAdapter
    }

}