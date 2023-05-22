package com.example.booking_airline_tickets;

import static com.example.booking_airline_tickets.login.loginflag;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    private ImageSlider imageSlider;
    public static  Button btn_login;
    Menu  item;
    Button btn_show,btn_book,btn_cancel,btn_contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
         btn_show=findViewById(R.id.btn_show);
         btn_book=findViewById(R.id.btn_book);
         btn_login=findViewById(R.id.btn_login);
         btn_cancel=findViewById(R.id.btn_cancel);
         btn_contact=findViewById(R.id.btn_contact);

        if (loginflag){
            btn_login.setText("خروج");
        }else {
            btn_login.setText("دخول");


        }
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mor=new Intent(getApplicationContext(),show.class);
                startActivity(mor);
            }
        });
        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mor=new Intent(getApplicationContext(), Book.class);
                startActivity(mor);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginflag){
                    Toast.makeText(Home.this, "تم الخروج بنجاح", Toast.LENGTH_SHORT).show();
                    loginflag=false;
                    btn_login.setText("دخول");
//                    item.findItem(R.id.login).setTitle("دخول");



                }else {
                Intent mor=new Intent(getApplicationContext(), login.class);
                startActivity(mor);
                }
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mor=new Intent(getApplicationContext(),cancelReservation.class);
                startActivity(mor);
            }
        });
        btn_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mor=new Intent(getApplicationContext(),Contact.class);
                startActivity(mor);
            }
        });



        imageSlider =findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slidemodels=new ArrayList<>();
        slidemodels.add(new SlideModel("https://media.istockphoto.com/id/1401027384/photo/businesswoman-reading-documents-and-working-on-digital-tablet-during-flight.jpg?s=612x612&w=0&k=20&c=uTN2pdw5qS2qqKoLxf7TQL85SV5l8Py-ewdmaMPW-3w=", ScaleTypes.FIT));
        slidemodels.add(new SlideModel("https://media.istockphoto.com/id/1345893732/photo/empty-airport-at-sunset.jpg?b=1&s=170667a&w=0&k=20&c=P05rF0fMYNavNSYVgXjka6DPAz16UnA7_L-9Sim9yNE=", ScaleTypes.FIT));
        slidemodels.add(new SlideModel("https://media.istockphoto.com/id/1464801136/photo/happy-young-woman-travelling.jpg?b=1&s=170667a&w=0&k=20&c=PgbPheURyQovYpXsLqCRA-jKKUKZBNod_HafCEC2w1o=", ScaleTypes.FIT));
        slidemodels.add(new SlideModel("https://media.istockphoto.com/id/1394456695/photo/a-woman-at-the-airport-holding-a-passport-with-a-boarding-pass.jpg?s=612x612&w=0&k=20&c=zsO5hJv5OaTLJHFj31OB7CtjjbcIfo5AsINQw3W89jg=", ScaleTypes.FIT));
        slidemodels.add(new SlideModel("https://media.istockphoto.com/id/1363169212/photo/girl-holds-tickets-boarding-passes-for-a-flight.jpg?s=612x612&w=0&k=20&c=sLpjtW-G0r8jQX9HNZf2ldnlS57xJgxAIIJYCtI_AiY=", ScaleTypes.FIT));
        slidemodels.add(new SlideModel("https://media.istockphoto.com/id/1357530144/photo/young-family-having-fun-traveling-together.jpg?s=612x612&w=0&k=20&c=9n0yR3q7w4ReuTiNA1k8D4BOl7__ZJ9K4ixT4kNhYZ8=", ScaleTypes.FIT));
        slidemodels.add(new SlideModel("https://media.istockphoto.com/id/1192648836/photo/young-happy-woman-in-an-airplane-cabin.jpg?s=612x612&w=0&k=20&c=KZ_iDYPrsN_Mr3cEMPIxqN1TN0EzedOEwWZIstsWhXw=", ScaleTypes.FIT));
        imageSlider.setImageList(slidemodels,ScaleTypes.FIT);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id=item.getItemId();
        if(id==R.id.booking){
            Intent home=new Intent(getApplicationContext(), Book.class);
            startActivity(home);
        }
        if(id==R.id.cancel){
            Intent contact=new Intent(getApplicationContext(),cancelReservation.class);
            startActivity(contact);
        }
        if(id==R.id.login){
            Intent about=new Intent(getApplicationContext(), show.class);
            startActivity(about);
        }
        if(id==R.id.show){
            Intent login=new Intent(getApplicationContext(),show.class);
            startActivity(login);
        }
        if(id==R.id.contact){
            Intent about=new Intent(getApplicationContext(),Contact.class);
            startActivity(about);
        }
        return super.onOptionsItemSelected(item);

    }

    public void Facebook_Url(View view){
        Intent Facebook =new Intent(Intent.ACTION_VIEW);
        Facebook.setData(Uri.parse("https://www.facebook.com/profile.php?id=100055853014720"));
        if (Facebook.resolveActivity(getPackageManager())!=null){
            startActivity(Facebook);
        }
    }
    public void Twitter_Url(View view){
        Intent Twitter =new Intent(Intent.ACTION_VIEW);
        Twitter.setData(Uri.parse("https://www.facebook.com/profile.php?id=100055853014720"));
        if (Twitter.resolveActivity(getPackageManager())!=null){
            startActivity(Twitter);
        }}
    public void Instg_Url(View view){
        Intent Instgram =new Intent(Intent.ACTION_VIEW);
        Instgram.setData(Uri.parse("https://instagram.com/ataesraa168?igshid=MzNlNGNkZWQ4Mg=="));
        if (Instgram.resolveActivity(getPackageManager())!=null){
            startActivity(Instgram);
        }
    }





}