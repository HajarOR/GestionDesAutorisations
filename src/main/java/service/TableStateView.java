/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Lenovo
 */
@SessionScoped
@Named("dtTableStateView")
public class TableStateView implements Serializable {

    private List<Car> cars;

    private List<Car> filteredCars;

    private Car selectedCar;

    @EJB
    private service.CarService service;

    public List<String> getBrands() {
        return service.getBrands();
    }

    public List<String> getColors() {
        return service.getColors();
    }

    public List<Car> getCars() {
        cars = service.createCars(50);
        return cars;
    }

    public List<Car> getFilteredCars() {
        return filteredCars;
    }

    public Car getSelectedCar() {
        return selectedCar;
    }

    public void setSelectedCar(Car selectedCar) {
        this.selectedCar = selectedCar;
    }

    public void setFilteredCars(List<Car> filteredCars) {
        this.filteredCars = filteredCars;
    }

    public void setService(CarService service) {
        this.service = service;
    }

    public CarService getService() {
        return service;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

}
