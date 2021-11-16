package com.example.androidwithsql

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.androidwithsql.MainActivity.Companion.DB_MEMBER
import com.example.androidwithsql.MainActivity.Companion.DB_VERSION
import com.example.androidwithsql.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignupBinding    //회원가입뷰와 연결
    private lateinit var loginIntent : Intent   //로그인Activity와 연결
    private lateinit var helper: SqliteHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginIntent = Intent(this,LoginActivity::class.java)
        helper = SqliteHelper(this,DB_MEMBER,DB_VERSION)

        //클릭 시 사용자가 입력한 정보를 Member테이블의 각 속성에 들어감.
        binding.EnrollmentButton.setOnClickListener {
            if(binding.signupIDText.text == null || binding.signtupPWText.text == null) {
                Toast.makeText(this,"아이디 또는 비밀번호가 입력되있지 않습니다.",Toast.LENGTH_SHORT).show()
            }else
            {
                val member = MemberData(
                    binding.signupIDText.toString(), binding.signtupPWText.toString(),
                    binding.signupNameText.toString(), binding.signupPhoneNoText.toString()
                )
                helper.insertMemberData(member)
                Toast.makeText(this, "회원등록하셨습니다.", Toast.LENGTH_SHORT).show()
                startActivity(loginIntent)
            }
        }
    }
}