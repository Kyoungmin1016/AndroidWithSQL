package com.example.androidwithsql

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.androidwithsql.MainActivity.Companion.DB_MEMBER
import com.example.androidwithsql.MainActivity.Companion.DB_VERSION
import com.example.androidwithsql.SqliteHelper.Companion.U_id
import com.example.androidwithsql.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding //로그인뷰 연결
    private lateinit var signupIntent : Intent  //회원가입액티비티와 연결
    private lateinit var helper: SqliteHelper   //클래스 SqliteHelper 호출
    private lateinit var HomeIntent : Intent  //home 액티비티와 연결


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //모든 연결 함수 초기화
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        signupIntent = Intent(this,SignupActivity::class.java)
        helper = SqliteHelper(this,DB_MEMBER,DB_VERSION)
        HomeIntent = Intent(this, HomeActivity::class.java)

        //클릭시 회원가입화면으로 이동
        binding.signupButton.setOnClickListener {
            startActivity(signupIntent)
        }

        //클릭 시 Member테이블의 데이터 초기화
        binding.resetButton.setOnClickListener {
            helper.reset()
            Toast.makeText(this,"초기화되었습니다.",Toast.LENGTH_SHORT).show()
        }

        //클릭 시 로그인
        binding.loginButton.setOnClickListener {
            //아이디 및 비밀번호정보 미 입력시 토스트메시지 출력
            if(binding.longinId.text.toString().length != 0 && binding.loginPw.text.toString().length != 0){
                //아이디 및 비밀번호가 맞는 투플이 있으면 아이디및 기타정보 임시저장 후 Home화면으로 이동
                if(helper.checkMemberData(binding.longinId.text.toString(),binding.loginPw.text.toString())){
                    //helper.insertOrderItem(OrderData(U_id.toString(),"주문 시 해당리스트에 출력됩니다.",0,null))
                    startActivity(HomeIntent)
                }
                else{
                    Toast.makeText(this,"로그인 실패",Toast.LENGTH_SHORT).show()
                }
            }
            else
                Toast.makeText(this,"아이디 또는 비밀번호가 입력되지 않았습니다.",Toast.LENGTH_SHORT).show()
        }


    }
}