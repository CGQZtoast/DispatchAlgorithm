package com.zard.backend.model;

import com.zard.backend.pojo.Driver;

/**
 * @Author 乌贼
 * @Date 2023-08-29 8:37
 * @Description
 */

public class DispatchModel {
    Driver driver;
    NavigateModel navigateModel;

    public DispatchModel() {
    }

    public DispatchModel(Driver driver, NavigateModel navigateModel) {
        this.driver = driver;
        this.navigateModel = navigateModel;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public NavigateModel getNavigateModel() {
        return navigateModel;
    }

    public void setNavigateModel(NavigateModel navigateModel) {
        this.navigateModel = navigateModel;
    }
}
