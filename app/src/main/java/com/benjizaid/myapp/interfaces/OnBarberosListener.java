package com.benjizaid.myapp.interfaces;

import com.benjizaid.myapp.model.BarberiaEntity;
import com.benjizaid.myapp.model.BarberosEntity;

public interface OnBarberosListener {
    void selectedItemBarberos(BarberosEntity barberosEntity);
    void renderFirstBarberos(BarberosEntity barberosEntity);
    //void goToDetallesBarberos(BarberosEntity barberosEntity);

    void selectedItemBarberia(BarberiaEntity barberiaEntity);
    void renderFirstBarberia(BarberiaEntity barberiaEntity);
}
