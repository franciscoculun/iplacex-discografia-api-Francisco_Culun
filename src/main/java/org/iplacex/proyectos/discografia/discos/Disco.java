package org.iplacex.proyectos.discografia.discos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.List;

@Document(collection = "discos")
public class Disco {

    @Id
    public String _id;

    @Field
    public String idArtista;

    @Field
    public String nombre;

    @Field
    public int anioLanzamiento;

    @Field
    public List<String> canciones;
}
