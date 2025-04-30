package com.example.lessons11.ui.ContentProvider

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.provider.ContactsContract
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatTextView
import com.example.lessons11.R
import com.example.lessons11.databinding.FragmentContentBinding

const val REQUEST_CODE = 42

class ContentProviderFragment : Fragment() {

    private var _binding:FragmentContentBinding? = null
    private val binding get() = _binding!!


    companion object {
        fun newInstance() = ContentProviderFragment()
    }

    private val viewModel: ContentViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContentBinding.inflate(inflater,container,false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
    }

    private fun checkPermission() {
        context?.let {
            when {
                ContextCompat.checkSelfPermission(it, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED -> {
                    getContacts()
                }

                shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS) -> {
                    AlertDialog.Builder(it)
                        .setTitle("Доступ к контактам")
                        .setMessage("Это необходимо для отображения ваших контактов.")
                        .setPositiveButton("Ок") { _, _ ->
                            requestPermission()
                        }
                        .setNegativeButton("Нет") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                }

                else -> {
                    // Если permission не предоставлен и объяснение не требуется (впервые запрашиваем)
                    requestPermission()
                }
            }
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                    getContacts()
                } else {
                    context?.let {
                        AlertDialog.Builder(it)
                            .setTitle("Доступ к контактам")
                            .setMessage("Объяснение")
                            .setPositiveButton("Ок"){_,_ ->
                                requestPermission()
                            }
                            .setNegativeButton("No"){dialog,_ ->
                                dialog.dismiss()
                            }
                            .create()
                            .show()
                    }
                }
                return
            }
        }
    }

    private  fun requestPermission(){
        requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), REQUEST_CODE)
    }


    private fun getContacts(){
        context?.let {
            val contentResolver:ContentResolver = it.contentResolver
            val cursorWithContacts:Cursor? = contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI
                ,null, null, null,ContactsContract.Contacts.DISPLAY_NAME + " ASC"
            )

            cursorWithContacts?.let { cursor ->
                for (i in 0 .. cursor.count){
                    if(cursor.moveToPosition(i)){
                        val name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                       addView(it,name)
                    }
                }
            }
            cursorWithContacts?.close()
        }
    }
    private fun addView(context: Context,textTx :String){
        binding.conteinerForContacts.addView(AppCompatTextView(context).apply {
            text = textTx
            textSize = resources.getDimension(R.dimen.main_conteiner_text_size)
        })
    }
}





