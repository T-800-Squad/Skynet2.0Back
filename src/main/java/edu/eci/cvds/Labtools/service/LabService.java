package edu.eci.cvds.Labtools.service;

import edu.eci.cvds.Labtools.model.Lab;

public interface LabService{
    Lab createLab(String labName);
    void deleteLab(String labName);
}
