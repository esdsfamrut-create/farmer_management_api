package com.famrut.farmer_management_api.service;

import com.famrut.farmer_management_api.config.PageableConfig;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FarmerSearchService {

    public void validateSort(Pageable pageable) {

        for (Sort.Order order : pageable.getSort()) {

            boolean allowed = false;

            for (String field : PageableConfig.ALLOWED_SORT_FIELDS) {

                if (field.equals(order.getProperty())) {
                    allowed = true;
                    break;
                }
            }

            if (!allowed) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Sorting by field '" +
                                order.getProperty() +
                                "' is not allowed"
                );
            }
        }
    }
}