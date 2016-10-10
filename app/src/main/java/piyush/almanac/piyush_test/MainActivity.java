package piyush.almanac.piyush_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText msg;
    Button pushbtn;
    Button fetchbtn;
    TextView textdisplay;
    String masssege;
    RequestQueue requestQueue;
    String insertnoticeUrl="http://kgbvbundu.org/piyush/push.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pushbtn=(Button) findViewById(R.id.pushbutton);
        fetchbtn=(Button) findViewById(R.id.fetchbutton);

        pushbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg=(EditText) findViewById(R.id.msgtext);
                masssege=msg.getText().toString();



                push();


            }
        });

        fetchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textdisplay=(TextView) findViewById(R.id.textdisplay);

            }
        });



    }
    void push(){
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, insertnoticeUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
      if(response.equals("success")) {
          Toast.makeText(getApplication(),"Ho gya",Toast.LENGTH_LONG).show();
      }else
      {
          Toast.makeText(getApplication(),"nai hua",Toast.LENGTH_LONG).show();
      }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplication(),"Oops, Notice send failed, Slow Internet!!",Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("text", masssege);
                return parameters;

            }
        };


        requestQueue.add(request);



    }

}

