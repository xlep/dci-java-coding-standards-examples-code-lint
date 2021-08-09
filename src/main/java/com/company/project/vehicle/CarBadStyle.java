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

public class CarBadStyle extends Vehicle{

private final Brand BRAND; private final
VehicleModel VehicleModel;
private final Engine e; private final EquipmentConfiguration c;

public CarBadStyle(Brand BRAND, VehicleModel vehicleModel, Engine engine, EquipmentConfiguration equipmentConfiguration) {this.BRAND = BRAND; this.e = engine; this.VehicleModel = vehicleModel; this.c = equipmentConfiguration;}

public boolean setTyre(Tyre tyre) {
  if (VehicleModel.checkTyreSpecs(tyre))
  this.c.setTyre(tyre);
  return false;
}


public List<Defect> performMaintenance() { ArrayList<Defect> defects = new ArrayList<>();
errors(defects);
pressure(defects);
wearout(defects);
    return defects;
}

  private boolean errors(ArrayList<Defect> defects) {
    ErrorReader errorReader = new ErrorReader(); return errorReader.analyzeCarComputer(this, defects);
  }

private boolean pressure(ArrayList<Defect> defects) {return this.e.readOilPressure(defects);
  }

  private void wearout(ArrayList<Defect> defects) {
    if (this.e.checkVBelt())
    { Defect defectVBelt = new Defect("v belt needs to be changed");
    defects.add(defectVBelt); }
    if (this.c.checkBreaks()) {
      Defect defectBreaks = new Defect("breaks need to be changed");
      defects.add(defectBreaks);
    }
  }

}
