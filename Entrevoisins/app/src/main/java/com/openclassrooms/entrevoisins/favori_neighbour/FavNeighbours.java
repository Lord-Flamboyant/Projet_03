package com.openclassrooms.entrevoisins.favori_neighbour;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FavNeighbours {

    public static List<Neighbour> FAV_NEIGHBOURS = Arrays.asList();



    public static List<Neighbour> generateFavNeighbours() {
        return new ArrayList<>(FAV_NEIGHBOURS);
    }
}
