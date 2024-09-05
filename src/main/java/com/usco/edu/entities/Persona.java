package com.usco.edu.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Persona {

    
    private Long codigo;
    private String nombre;
    private String apellido;
    private String identificacion;
    private String emailInterno;
    
}
