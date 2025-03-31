package edu.eci.cvds.Labtools.service;

import edu.eci.cvds.Labtools.model.Lab;
import edu.eci.cvds.Labtools.repository.MongoLabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicLabService implements LabService{

    @Autowired
    private MongoLabRepository labRepository;
    @Override
    public Lab createLab(String labName) {
        Lab lab = new Lab();
        lab.setName(labName);
        if(labRepository.findByName(labName) != null){
            throw new IllegalArgumentException("Lab already exists");
        }
        labRepository.save(lab);
        return lab;
    }

    @Override
    public void deleteLab(String labName) {
        if(labRepository.findByName(labName) == null){
            throw new IllegalArgumentException("Lab no exists");
        }
        labRepository.deleteByName(labName);
    }
}
