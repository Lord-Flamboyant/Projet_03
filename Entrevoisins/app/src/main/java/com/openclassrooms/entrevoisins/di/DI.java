package com.openclassrooms.entrevoisins.di;

import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

/**
 * Dependency injector to get instance of services
 */
public class DI {

    private static final NeighbourApiService service = new DummyNeighbourApiService();

    public static NeighbourApiService getNeighbourApiService() {
        return service;
    }

    public static NeighbourApiService getNewInstanceApiService() {
        return new DummyNeighbourApiService();
    }
}
