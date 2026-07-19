package org.iplacex.proyectos.discografia.artistas;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.List;

@Document(collection = "artistas")
public class Artista {
    
    @Id
    public String _id;

    @Field
    public String nombre;

    @Field
    public List<String> estilos;

    @Field
    public int anioFundacion;

    @Field
    public boolean estaActivo;
}
