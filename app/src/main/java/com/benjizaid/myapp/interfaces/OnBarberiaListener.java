package com.benjizaid.myapp.interfaces;

import com.benjizaid.myapp.model.BarberiaEntity;

public interface OnBarberiaListener {
    void selectedItemBarberia(BarberiaEntity barberiaEntity);
    void renderFirstBarberia(BarberiaEntity barberiaEntity);
}
