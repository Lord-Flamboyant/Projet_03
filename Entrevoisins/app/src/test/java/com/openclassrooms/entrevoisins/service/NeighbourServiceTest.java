package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;

import org.hamcrest.collection.IsArrayContaining;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.hamcrest.core.IsNot;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;
import java.util.regex.Matcher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/***test unitaire */

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }


    @Test
    public  void createNewNeighbour() {
        List<Neighbour> neighbours = service.getNeighbours();
        Neighbour neighbour = DummyNeighbourGenerator.generateNeighbours().get(0);
        service.createNeighbour(neighbour);
        assertTrue(service.getNeighbours().contains(neighbour));
    }

     @Test
    public void addNeighbourFavorite() {
         List<Neighbour> neighbours = service.getFavNeighbours();
         Neighbour neighbour = service.getNeighbours().get(0);
         service.addFavNeighbour(neighbour);
         assertTrue(neighbours.contains(neighbour));
    }

    @Test
    public void deleteFavNeighbour() {
        List<Neighbour> neighbours = service.getFavNeighbours();
        Neighbour neighbour = service.getNeighbours().get(0);
        service.addFavNeighbour(neighbour);
        service.deleteFavNeighbour(neighbour);
        assertFalse(neighbours.contains(neighbour));
    }
}
