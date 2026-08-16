package com.fooddelivery.restaurant.service;

import com.fooddelivery.restaurant.dto.RestaurantRequest;
import com.fooddelivery.restaurant.exception.RestaurantNotFoundException;
import com.fooddelivery.restaurant.model.MenuItem;
import com.fooddelivery.restaurant.model.Restaurant;
import com.fooddelivery.restaurant.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    // Constructor injection - Spring wires the repository bean in automatically
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    public Restaurant getById(String id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    public Restaurant create(RestaurantRequest request) {
        Restaurant restaurant = new Restaurant(
                request.getName(),
                request.getCuisine(),
                request.getAddress(),
                request.getRating()
        );
        return restaurantRepository.save(restaurant);
    }

    public List<MenuItem> getMenu(String id) {
        // Confirms the restaurant exists first, so callers get a proper 404
        // instead of a silently empty list for an unknown id.
        return getById(id).getMenu();
    }

    public void delete(String id) {
        if (!restaurantRepository.existsById(id)) {
            throw new RestaurantNotFoundException(id);
        }
        restaurantRepository.deleteById(id);
    }
}
