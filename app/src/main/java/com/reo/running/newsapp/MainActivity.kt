package com.reo.running.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.reo.running.newsapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        // Retrofitクライアントのセットの取得
        val retrofit = Retrofit.Builder().baseUrl("https://newapi.org/").addConverterFactory(GsonConverterFactory.create()).build()
        // APIエンドポイントの生成
        val api = retrofit.create(ApiService::class.java)
        // 引数によってapiエンドポイントを指定し、リクエスト
        api.getNews("475a6716e0b1401ea0191041db0e6fff","jp").enqueue(object: Callback<ResponseData> {

            // 通信が失敗した時の処理
            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                Log.d("debug","${t}")
            }

            // 通信が成功した時の処理
            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>?) {
                // 紐づけたTextViewに取得したデータをそのまま表示
                binding.textview.text = response?.body().toString()
            }
        })
    }
}