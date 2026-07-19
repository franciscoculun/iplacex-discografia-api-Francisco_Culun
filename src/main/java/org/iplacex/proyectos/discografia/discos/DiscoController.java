package org.iplacex.proyectos.discografia.discos;

import java.util.List;
import java.util.Optional;
import org.iplacex.proyectos.discografia.artistas.IArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para el subdominio de Discos.
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class DiscoController {

    @Autowired
    private IDiscoRepository discoRepo;

    // Inyección del repositorio del dominio padre.
    @Autowired
    private IArtistaRepository artistaRepo;

    /**
     * Registra un nuevo Disco validando su dependencia estructural.
     */
    @PostMapping(
        value = "/disco",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> HandlePostDiscoRequest(@RequestBody Disco disco) {
        if (!artistaRepo.existsById(disco.idArtista)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        Disco creado = discoRepo.insert(disco);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    /**
     * Recupera la coleccion de discos.
     */
    @GetMapping(
        value = "/discos",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Disco>> HandleGetDiscosRequest() {
        List<Disco> discos = discoRepo.findAll();
        return new ResponseEntity<>(discos, HttpStatus.OK);
    }

    /**
     * Búsqueda específica por Id.
     */
    @GetMapping(
        value = "/disco/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> HandleGetDiscoRequest(@PathVariable String id) {
        Optional<Disco> discoOpt = discoRepo.findById(id);
        
        return discoOpt.<ResponseEntity<Object>>map(disco -> 
            new ResponseEntity<>(disco, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Recupera la discografía completa de un artista específico.
     */
    @GetMapping(
        value = "/artista/{id}/discos",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Disco>> HandleGetDiscosByArtistaRequest(@PathVariable String id) {
        List<Disco> discosPorArtista = discoRepo.findDiscosByIdArtista(id);
        
        return new ResponseEntity<>(discosPorArtista, HttpStatus.OK);
    }
}