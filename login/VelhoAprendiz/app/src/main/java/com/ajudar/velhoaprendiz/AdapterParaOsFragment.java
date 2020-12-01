package com.ajudar.velhoaprendiz;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class AdapterParaOsFragment extends FragmentStatePagerAdapter {

    int numeroDeTabelas;


    public AdapterParaOsFragment(FragmentManager fm, int numDeTabelas){
        super(fm);
        this.numeroDeTabelas = numDeTabelas;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
              FragmentBoleto videosDoBoleto = new FragmentBoleto();
              return videosDoBoleto;
            case 1:
                FragmentTransferencia videosDeTransferencia = new FragmentTransferencia();
                return videosDeTransferencia;
            case 2:
                FragmentAssistencia assistencia = new FragmentAssistencia();
                return assistencia;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numeroDeTabelas;
    }
}
