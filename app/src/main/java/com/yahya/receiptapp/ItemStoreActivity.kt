package com.yahya.receiptapp

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yahya.receiptapp.databinding.ActivityItemStoreBinding
import com.yahya.receiptapp.interfaces.IRecyclerViewInterface
import com.yahya.receiptapp.models.Product
import com.yahya.receiptapp.utility.*
import java.util.Calendar


class ItemStoreActivity : AppCompatActivity(),IRecyclerViewInterface {

    var PI_REQUESTCODE = 45;
    var sec = 2;
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemListAdapter: ItemListAdapter
    private lateinit var viewBinding: ActivityItemStoreBinding
    private var itemStore = ArrayList<Product>()
    private var alarmManagers = ArrayList<AlarmManager?>()
    private var filename = "itemStore"


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityItemStoreBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        var productsReceived = intent.getStringArrayListExtra("listofproducts")  as ArrayList<Product> //intent.getSerializableExtra()
        createNotificationChannel()
        setupAlarms(productsReceived)

        itemStore = PersistenceService.getItemStoreFromInternalStorage(this, filename)
        itemStore.addAll(productsReceived)
        PersistenceService.saveItemStoreToInternalStorage(this,itemStore, filename)
        itemListAdapter = ItemListAdapter(itemStore,this)
        recyclerView = viewBinding.storeRecyclerView
        recyclerView.adapter = itemListAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewBinding.btnClearStore.setOnClickListener {
            itemStore.clear()
            itemListAdapter.notifyDataSetChanged()
            PersistenceService.saveItemStoreToInternalStorage(this,itemStore,filename)
        }

    }

    override fun onPause() {
        super.onPause()
        PersistenceService.saveItemStoreToInternalStorage(this,itemStore,filename)
    }

    private fun scheduleNotification(time:Long){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val intent = Intent(applicationContext, NotificationHelper::class.java)

            val pendingIntent = PendingIntent.getBroadcast(applicationContext, notificationID++,intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)

            val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            alarmManagers.add(alarmManager)
            //val time = getTime()
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                time,
                pendingIntent
            )
        }

    }


    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "my_notification_channel"
            val descriptionText = "this is my notification channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId,name,importance).apply {
                description = descriptionText
            }
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }
    }

    private fun setupAlarms(list: ArrayList<Product>){
        for(item in list){
            if(item.expiryDate!=null){
                var c = Calendar.getInstance()
                c.time = item.expiryDate
                //c.add(Calendar.SECOND, sec++)
                scheduleNotification(c.timeInMillis)
            }else{
                alarmManagers.add(null)
            }

        }
    }

    override fun onItemLongClick(position: Int) {
        itemStore.removeAt(position)
        itemListAdapter.notifyItemRemoved(position)
//        if(position == itemStore.size -1){
//
//       }else{
//           var shift: Int = 1;
//           while(true){
//               try{
//                   itemStore.removeAt(position-shift)
//                   itemListAdapter.notifyItemRemoved(position)
//                   break;
//               }catch (e:java.lang.IndexOutOfBoundsException){
//                   shift++;
//               }
//           }
//       }

    }
}