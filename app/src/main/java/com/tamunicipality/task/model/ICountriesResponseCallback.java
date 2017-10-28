package com.tamunicipality.task.model;

import java.util.List;

/**
 * Created by Dmitriy on 20.06.2017.
 */

public interface ICountriesResponseCallback {
    void successful(List<String> regions, Countries countries);
    void error(String message);
}
