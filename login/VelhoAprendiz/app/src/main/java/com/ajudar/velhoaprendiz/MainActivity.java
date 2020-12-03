package com.ajudar.velhoaprendiz;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements FragmentBoleto.OnFragmentInteractionListener,FragmentTransferencia.OnFragmentInteractionListener,FragmentAssistencia.OnFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Pagar Boleto"));
        tabLayout.addTab(tabLayout.newTab().setText("Transferência"));
        tabLayout.addTab(tabLayout.newTab().setText("Assistência"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pager);
        AdapterParaOsFragment adapter = new AdapterParaOsFragment(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuprincipal, menu);
        return true;
    }

    public void sair(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Entrar.class));
        Toast.makeText(MainActivity.this, "Você saiu",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuAjuda:
                new AlertDialog.Builder(this)
                        .setTitle("Ajuda")
                        .setMessage("\nAqui você encontra vídeos explicativos\n" +
                                "sobre algumas funcionalidades de aplicativos bancários.\n" +
                                "\nContém vídeos de bancos diversificados.\n" +
                                "\nSelecione o que você quer aprender" +
                                " e escolha o vídeo que corresponde ao seu banco.")
                        .setNegativeButton("Fechar", null).show();
                return true;

            case R.id.menuSair:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Entrar.class));
                Toast.makeText(MainActivity.this, "Você saiu",Toast.LENGTH_SHORT).show();
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
