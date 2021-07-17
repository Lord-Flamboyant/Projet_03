package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private final List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }
    /**
     * {@inheritDoc}
     *
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    /***add favorite */
    @Override
    public void addFavNeighbour(Neighbour neighbour) {
        if (!neighbour.getFav()) {
            neighbour.setFav(true);
        }
    }

    /***delete favorite */
    @Override
    public void deleteFavNeighbour(Neighbour neighbour) {
        if (neighbour.getFav()) {
            neighbour.setFav(false);
        }
    }
}

