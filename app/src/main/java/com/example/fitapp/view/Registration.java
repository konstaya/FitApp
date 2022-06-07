package com.example.fitapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.fitapp.R;
import com.example.fitapp.model.FirebaseDatabaseHelper;
import com.example.fitapp.model.User;
import com.example.fitapp.model.UserPlanCreator;
import com.example.fitapp.view.registrationFragments.AgeFragment;
import com.example.fitapp.view.registrationFragments.ArmFragment;
import com.example.fitapp.view.registrationFragments.CheastFragment;
import com.example.fitapp.view.registrationFragments.HeightFragment;
import com.example.fitapp.view.registrationFragments.HipsFragment;
import com.example.fitapp.view.registrationFragments.OneHipFragment;
import com.example.fitapp.view.registrationFragments.RegisterFragment;
import com.example.fitapp.view.registrationFragments.ShinFragment;
import com.example.fitapp.view.registrationFragments.TapeFragment;
import com.example.fitapp.view.registrationFragments.WaistFragment;
import com.example.fitapp.view.registrationFragments.WeightFragment;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {
    // dataBase auth
    private FirebaseAuth mAuth;

    // buttons
    private Button btn_Next;
    private Button btn_Registr;
    private ImageButton btn_Return;

    //step of registration (connected with fragments transactions)
    private Integer step;

    //user info variables
    private int age,height;
    private float weight,arm,chest,waist, hips,oneHip,shin;
    private String name, email, password;

    //registration fragments

    RegisterFragment registerFragment = new RegisterFragment();
    AgeFragment ageFragment = new AgeFragment();
    HeightFragment heightFragment = new HeightFragment();
    WeightFragment weightFragment = new WeightFragment();
    ArmFragment armFragment = new ArmFragment();
    CheastFragment cheastFragment = new CheastFragment();
    WaistFragment waistFragment = new WaistFragment();
    HipsFragment hipsFragment = new HipsFragment();
    OneHipFragment oneHipFragment = new OneHipFragment();
    ShinFragment shinFragment = new ShinFragment();
    TapeFragment tapeFragment = new TapeFragment();
    FragmentTransaction ft;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //initialize the FirebaseAuth instance.
        mAuth = FirebaseAuth.getInstance();

        step = 1;
        btn_Next = findViewById(R.id.btn_Next);
        btn_Return = findViewById(R.id.btnExit);
        btn_Registr = findViewById(R.id.btn_registr);


        openNewFrag(ageFragment);

        btn_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(step);
                if (step == 1) {
                    if (checkAge()) openNewFrag(heightFragment);
                    else step--;
                }
                else if (step == 2) {
                    if (checkHeight()) openNewFrag(weightFragment);
                    else step--;
                }
                else if (step == 3) {
                    if (checkWeight()) openNewFrag(tapeFragment);
                    else step--;
                }
                else if (step == 4) openNewFrag(armFragment);
                else if (step == 5) {
                    if (checkArm())openNewFrag(cheastFragment);
                    else step--;
                }
                else if (step == 6) {
                    if (checkChest())openNewFrag(waistFragment);
                    else step--;
                }
                else if (step == 7) {
                    if (checkWaist())openNewFrag(hipsFragment);
                    else step--;
                }
                else if (step == 8){
                    if (checkHips()) openNewFrag(oneHipFragment);
                    else step--;
                }
                else if (step == 9){
                    if (checkOneHip()) openNewFrag(shinFragment);
                    else step--;
                }
                else if (step == 10){
                    if (checkShin()) {
                        openNewFrag(registerFragment);
                        btn_Next.setVisibility(View.INVISIBLE);
                        btn_Registr.setVisibility(View.VISIBLE);
                    }
                    else step--;
                }
                step++;

            }
        });

        btn_Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (step == 1) Registration.super.onBackPressed();
                else if (step == 2) goToPreviousFrag(heightFragment,ageFragment);
                else if (step == 3) goToPreviousFrag(weightFragment,heightFragment);
                else if (step == 4) goToPreviousFrag(tapeFragment,weightFragment);
                else if (step == 5) goToPreviousFrag(armFragment,tapeFragment);
                else if (step == 6) goToPreviousFrag(cheastFragment,armFragment);
                else if (step == 7) goToPreviousFrag(waistFragment,cheastFragment);
                else if (step == 8) goToPreviousFrag(hipsFragment,waistFragment);
                else if (step == 9) goToPreviousFrag(oneHipFragment,hipsFragment);
                else if (step == 10) goToPreviousFrag(shinFragment,oneHipFragment);
                else if (step == 11) {
                    goToPreviousFrag(registerFragment,shinFragment);
                    btn_Next.setVisibility(View.VISIBLE);
                    btn_Registr.setVisibility(View.GONE);
                }
                step --;
            }
        });
        btn_Registr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });


    }

    private void registerUser() {
        if (registerFragment.checkFields()){
            name = registerFragment.getUserName();
            email = registerFragment.getEmail();
            password = registerFragment.getPassword();

        }
        else return;
        User newUser = new User(name, email,age,height,weight,
                arm, chest, waist,hips,oneHip,shin);
        new UserPlanCreator(Registration.this).userRegistration(newUser, password);

    }


    private void openNewFrag(Fragment openFrag) {
        // Open the fragment on the frame view
        ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.move_to_right, R.anim.move_to_left);
        ft.replace(R.id.frame_container,openFrag);
        ft.commit();
    }

    private void goToPreviousFrag(Fragment currentFrag, Fragment previousFrag){
        // close remove current fragment from stack in the frame view
        // and open previous registration step
        ft = getSupportFragmentManager().beginTransaction();
        if (currentFrag != null) ft.remove(currentFrag);
        ft.setCustomAnimations(R.anim.return_from_left, R.anim.return_to_right);
        ft.replace(R.id.frame_container,previousFrag);
        ft.commit();
    }

    private boolean checkAge(){
        // Checking age that user specified in the field
        // Return true  if user's age is OK

        age=ageFragment.getAge();
        if (age<0) Toast.makeText(getBaseContext(),"Введите свой возраст",Toast.LENGTH_LONG).show();
        else if (age<14)Toast.makeText(getBaseContext(),"Для регистрации вам должны быть старше 13",Toast.LENGTH_LONG).show();
        else if (age>100) Toast.makeText(getBaseContext(),"Некорретный возраст",Toast.LENGTH_LONG).show();
        else return true;
        return false;
    }
    private boolean checkHeight(){
        // Checking height that user specified in the field
        // Return true  if user's height is OK

        height = heightFragment.getHeight();
        if (height<0) Toast.makeText(getBaseContext(),"Введите свой рост",Toast.LENGTH_LONG).show();
        else if (height<130 | height>230) Toast.makeText(getBaseContext(),"Некорректный рост",Toast.LENGTH_LONG).show();
        else return true;
        return false;
    }
    private boolean checkWeight(){
        // Checking weight that user specified in the field
        // Return true  if user's weight is OK

        weight = weightFragment.getWeight();
        System.out.println(weight);
        if (weight<0) Toast.makeText(getBaseContext(),"Введите свой вес",Toast.LENGTH_LONG).show();
        else if (weight<35 | weight>200) Toast.makeText(getBaseContext(),"Некорректный вес",Toast.LENGTH_LONG).show();
        else return true;
        return false;
    }
    private boolean checkArm(){
        // Checking wrist girth that user specified in the field
        // Return true  if user's wrist girth is OK

        arm = armFragment.getWrist();
        if (arm<0) Toast.makeText(getBaseContext(),"Введите обхват запятья",Toast.LENGTH_LONG).show();
        else if (arm<5 | arm>30) Toast.makeText(getBaseContext(),"Некорректный размер запястья",Toast.LENGTH_LONG).show();
        else return true;
        return false;
    }
    private boolean checkChest(){
        // Checking cheast that user specified in the field
        // Return true  if user's cheast is OK

        chest = cheastFragment.getCheast();
        if (chest<0) Toast.makeText(getBaseContext(),"Введите обхват груди",Toast.LENGTH_LONG).show();
        else if (chest<30 | chest>200) Toast.makeText(getBaseContext(),"Некорректный обхват груди",Toast.LENGTH_LONG).show();
        else return true;
        return false;
    }
    private boolean checkWaist(){
        // Checking waist circumference that user specified in the field
        // Return true  if user's waist is OK

        waist = waistFragment.getWaist();
        if (waist<0) Toast.makeText(getBaseContext(),"Введите обхват талии",Toast.LENGTH_LONG).show();
        else if (waist<30 | waist>150) Toast.makeText(getBaseContext(),"Некорректный обхват талии",Toast.LENGTH_LONG).show();
        else return true;
        return false;
    }
    private boolean checkHips(){
        // Checking hips circumference that user specified in the field
        // Return true  if user's hips circumference is OK

        hips = hipsFragment.getHips();
        if (hips<0) Toast.makeText(getBaseContext(),"Введите обхват бедер",Toast.LENGTH_LONG).show();
        else if (hips<30 | hips>200) Toast.makeText(getBaseContext(),"Некорректный обхват бедер",Toast.LENGTH_LONG).show();
        else return true;
        return false;
    }
    private boolean checkOneHip(){
        // Checking one hip circumference that user specified in the field
        // Return true  if user's one hip circumference is OK

        oneHip = oneHipFragment.getOneHip();
        if (oneHip<0) Toast.makeText(getBaseContext(),"Введите обхват одного бедра",Toast.LENGTH_LONG).show();
        else if (oneHip<30 | oneHip>200) Toast.makeText(getBaseContext(),"Некорректный обхват",Toast.LENGTH_LONG).show();
        else return true;
        return false;
    }
    private boolean checkShin(){
        // Checking shin circumference that user specified in the field
        // Return true  if user's shin circumference is OK

        shin = shinFragment.getShin();
        if (shin<0) Toast.makeText(getBaseContext(),"Введите обхват голени",Toast.LENGTH_LONG).show();
        else if (shin<30 | shin>200) Toast.makeText(getBaseContext(),"Некорректный обхват",Toast.LENGTH_LONG).show();
        else return true;
        return false;
    }


}