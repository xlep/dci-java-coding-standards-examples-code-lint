package com.company.project.vehicle;

import com.company.project.vehicle.configuration.Brand;
import com.company.project.vehicle.configuration.Engine;
import com.company.project.vehicle.configuration.EquipmentConfiguration;
import com.company.project.vehicle.configuration.Tyre;
import com.company.project.vehicle.configuration.VehicleModel;
import com.company.project.vehicle.workshop.Defect;
import com.company.project.vehicle.workshop.ErrorReader;
import java.util.ArrayList;
import java.util.List;

public class Car extends Vehicle{

  private final Brand brand;
  private final VehicleModel vehicleModel;
  private final Engine engine;
  private final EquipmentConfiguration configuration;

  /**
   * Generic constructor setting the brand, motorType and vehicle configuration.
   */
  public Car(Brand brand, VehicleModel vehicleModel, Engine engine,
      EquipmentConfiguration equipmentConfiguration) {
    this.brand = brand;
    this.engine = engine;
    this.vehicleModel = vehicleModel;
    this.configuration = equipmentConfiguration;
  }

  /**
   * Set's a new set of tires for the car if they're compatible.
   */
  public boolean setTyre(Tyre tyre) {
    if (vehicleModel.checkTyreSpecs(tyre)) {
      this.configuration.setTyre(tyre);
    }
    return false;
  }

  /**
   * Checks various parts of the vehicle and returns a list of defects that should be addressed.
   */
  public List<Defect> performMaintenance() {
    ArrayList<Defect> defects = new ArrayList<>();

    readErrors(defects);
    checkOilPressure(defects);
    checkWearout(defects);

    return defects;
  }

  private boolean readErrors(ArrayList<Defect> defects) {
    ErrorReader errorReader = new ErrorReader();
    return errorReader.analyzeCarComputer(this, defects);
  }

  private boolean checkOilPressure(ArrayList<Defect> defects) {
    return this.engine.readOilPressure(defects);
  }

  private void checkWearout(ArrayList<Defect> defects) {
    if (this.engine.checkVBelt()) {
      Defect defectVBelt = new Defect("v belt needs to be changed");
      defects.add(defectVBelt);
    }
    if (this.configuration.checkBreaks()) {
      Defect defectBreaks = new Defect("breaks need to be changed");
      defects.add(defectBreaks);
    }
  }

}
