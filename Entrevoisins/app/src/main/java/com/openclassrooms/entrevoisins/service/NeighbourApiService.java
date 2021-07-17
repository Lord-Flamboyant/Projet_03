package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {


    List<Neighbour> getNeighbours();

    /*** delete neighbour ***/
    void deleteNeighbour(Neighbour neighbour);

    /*** create neighbour */
    void createNeighbour(Neighbour neighbour);

    /***add fav neighbour */
    void addFavNeighbour(Neighbour neighbour);

    /*** delete fav neighbour*/
    void deleteFavNeighbour(Neighbour neighbour);
  

}
