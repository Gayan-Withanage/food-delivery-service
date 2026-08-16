package com.fooddelivery.restaurant.service;

import com.fooddelivery.restaurant.dto.RestaurantRequest;
import com.fooddelivery.restaurant.exception.RestaurantNotFoundException;
import com.fooddelivery.restaurant.model.Restaurant;
import com.fooddelivery.restaurant.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestaurantServiceTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantService restaurantService;

    private Restaurant sample;

    @BeforeEach
    void setUp() {
        sample = new Restaurant("Spice Villa", "Indian", "12 Main St", 4.5);
        sample.setId("abc123");
    }

    @Test
    void getAll_returnsEverythingFromRepository() {
        when(restaurantRepository.findAll()).thenReturn(List.of(sample));

        List<Restaurant> result = restaurantService.getAll();

        assertEquals(1, result.size());
        assertEquals("Spice Villa", result.get(0).getName());
    }

    @Test
    void getById_returnsRestaurant_whenFound() {
        when(restaurantRepository.findById("abc123")).thenReturn(Optional.of(sample));

        Restaurant result = restaurantService.getById("abc123");

        assertEquals("Spice Villa", result.getName());
    }

    @Test
    void getById_throws_whenNotFound() {
        when(restaurantRepository.findById("missing")).thenReturn(Optional.empty());

        assertThrows(RestaurantNotFoundException.class, () -> restaurantService.getById("missing"));
    }

    @Test
    void create_savesAndReturnsRestaurant() {
        RestaurantRequest request = new RestaurantRequest();
        request.setName("Spice Villa");
        request.setCuisine("Indian");
        request.setAddress("12 Main St");
        request.setRating(4.5);

        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(sample);

        Restaurant result = restaurantService.create(request);

        assertEquals("Spice Villa", result.getName());
        verify(restaurantRepository, times(1)).save(any(Restaurant.class));
    }

    @Test
    void delete_removesRestaurant_whenItExists() {
        when(restaurantRepository.existsById("abc123")).thenReturn(true);

        restaurantService.delete("abc123");

        verify(restaurantRepository, times(1)).deleteById("abc123");
    }

    @Test
    void delete_throws_whenRestaurantDoesNotExist() {
        when(restaurantRepository.existsById("missing")).thenReturn(false);

        assertThrows(RestaurantNotFoundException.class, () -> restaurantService.delete("missing"));
        verify(restaurantRepository, never()).deleteById(any());
    }
}
