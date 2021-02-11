package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.favori_neighbour.FavNeighbours;
import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> favNeighbougs = FavNeighbours.generateFavNeighbours();

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

    /***add favori */
    @Override
    public void addFavNeighbour(Neighbour neighbour) {
        getFavNeighbours().add(neighbour);
        neighbour.Fav = true;
    }

    /***delete favori */
    @Override
    public void deleteFavNeighbour(Neighbour neighbour) {
        getFavNeighbours().remove(neighbour);
        neighbour.Fav = false;
    }

    /*** list fav neighbours*/
    @Override
    public List<Neighbour> getFavNeighbours() {
        return favNeighbougs;
    }

}

