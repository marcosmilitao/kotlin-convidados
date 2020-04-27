package com.militao.convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.militao.convidados.R
import com.militao.convidados.service.constants.GuestConstants
import com.militao.convidados.view.adapter.GuestAdapter
import com.militao.convidados.view.listener.GuestListener
import com.militao.convidados.viewmodel.GuestViewModel

class AllGuestFragment : Fragment() {

    private lateinit var mViewModel: GuestViewModel
    private val mAdapter: GuestAdapter = GuestAdapter()
    private lateinit var mListener: GuestListener


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        mViewModel = ViewModelProvider(this).get(GuestViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_all, container, false)


        // 1 - Obter a recycler
        var recycler = root.findViewById<RecyclerView>( R.id.recycle_all_guests)

        // 2 - Definir um layout
        recycler.layoutManager = LinearLayoutManager(context)

        // 3 - Definir um adapter
        recycler.adapter = mAdapter

        mListener = object : GuestListener{
            override fun onClick(id: Int) {
                val intent = Intent(context,GuestFormActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(GuestConstants.GUESTID,id)

                intent.putExtras(bundle)

                startActivity(intent)

            }

            override fun onDelete(id: Int) {
                mViewModel.delete(id)
                mViewModel.load(GuestConstants.FILTER.EMPTY)
            }
        }

        mAdapter.attachListener(mListener)

        observer()



        return root
    }

    override fun onResume() {
        super.onResume()
        mViewModel.load(GuestConstants.FILTER.EMPTY)
    }
    private fun observer() {
        mViewModel.guestList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateGuests(it)

        })
    }
}
