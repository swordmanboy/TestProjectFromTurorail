package thailand.transport.center.smbz.com.myapp4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import thailand.transport.center.smbz.com.myapp4.data.model.Query;
import thailand.transport.center.smbz.com.myapp4.data.model.Weather;
import thailand.transport.center.smbz.com.myapp4.remote.WeatherAPI;

public class MainActivity extends AppCompatActivity {
    private TextView txt_city;
    private TextView txt_update;
    private TextView txt_temp;
    private TextView txt_conditions;
    private Button btn_reponse;

//    https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22Bangkok%2C%20th%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_city= (TextView) findViewById(R.id.txt_city);
        txt_update= (TextView) findViewById(R.id.txt_update);
        txt_temp= (TextView) findViewById(R.id.txt_temp);
        txt_conditions= (TextView) findViewById(R.id.txt_conditions);
        btn_reponse = (Button) findViewById(R.id.btn_reponse);
        ButterKnife.bind(this);

        btn_reponse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeatherAPI.Factory.getInstance().getWeather().enqueue(new Callback<Weather>() {
                    @Override
                    public void onResponse(Call<Weather> call, Response<Weather> response) {
                        Query query =  response.body().getQuery();
                        txt_city.setText(query.getResults().getChannel().getLocation().getCity());
                        txt_temp.setText(query.getResults().getChannel().getItem().getCondition().getTemp());
                        txt_update.setText(query.getResults().getChannel().getLastBuildDate());
                        txt_conditions.setText(query.getResults().getChannel().getItem().getCondition().getText());
                        Log.e("", "" );
                    }

                    @Override
                    public void onFailure(Call<Weather> call, Throwable t) {
                        Log.e("", "");
                    }
                });
            }
        });
    }

}
