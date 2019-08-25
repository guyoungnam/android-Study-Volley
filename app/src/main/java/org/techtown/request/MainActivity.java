package org.techtown.request;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;

    static RequestQueue requestQueue; //요청 큐는 한 번만 만들어 계속 사용할 수 있기 때문에 static 키워드로

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeRequest();
            }
        });

        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }
    public void makeRequest(){
        String url = editText.getText().toString();

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            //문자열을 주고받기 위해 사용하는 요청 객체
            @Override
            public void onResponse(String response) {
                println("에러->" + response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("에러->"+error.getMessage());
                    }
                }
                ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String,String> params = new HashMap<String, String>();
                return params;
            }

        };
        request.setShouldCache(false);
        requestQueue.add(request); //요청큐의 add() 메서드로 요청객체를 넣으면 요청 큐가 자동으로 요청과 응답 과정을 진행
        println("요청 보냄");
    }
    public void println(String data){
        textView.append(data+"\n");
    }
}
