package edu.eci.cvds.Labtools.service;

import edu.eci.cvds.Labtools.model.Lab;
import edu.eci.cvds.Labtools.repository.MongoLabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase BasicLabService que implementa la interfaz LabService.
 * Esta clase se encarga de gestionar las operaciones relacionadas con los laboratorios.
 * @author Miguel Angel Vanegas Cardenas, Yojhan Toro Rivera e Ivan Cubillos Vela.
 */
@Service
public class BasicLabService implements LabService{

    @Autowired
    private MongoLabRepository labRepository;

    /**
     * Crea un nuevo laboratorio con el nombre especificado.
     *
     * @param labName El nombre del laboratorio a crear.
     * @return El laboratorio creado.
     * @throws IllegalArgumentException Si el laboratorio ya existe.
     */
    public Lab createLab(String labName) {
        Lab lab = new Lab();
        lab.setName(labName);
        if(labRepository.findByName(labName) != null){
            throw new IllegalArgumentException("Lab already exists");
        }
        labRepository.save(lab);
        return lab;
    }

    /**
     * Elimina un laboratorio con el nombre especificado.
     *
     * @param labName El nombre del laboratorio a eliminar.
     * @throws IllegalArgumentException Si el laboratorio no existe.
     */
    public void deleteLab(String labName) {
        if(labRepository.findByName(labName) == null){
            throw new IllegalArgumentException("Lab no exists");
        }
        labRepository.deleteByName(labName);
    }
}
