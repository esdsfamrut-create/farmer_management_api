package com.famrut.farmer_management_api.specification;

import com.famrut.farmer_management_api.entity.Farmer;
import org.springframework.data.jpa.domain.Specification;
import com.famrut.farmer_management_api.entity.FarmerStatus;

public class FarmerSpecification {

    public static Specification<Farmer> hasName(String name) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")),
                        "%" + name.toLowerCase() + "%"
                );
    }

    public static Specification<Farmer> hasEmail(String email) {

    return (root, query, criteriaBuilder) ->
            criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("email")),
                    "%" + email.toLowerCase() + "%"
            );
    }

    public static Specification<Farmer> hasPhoneNumber(String phoneNumber) {
    return (root, query, criteriaBuilder) ->
            criteriaBuilder.like(
                    root.get("phoneNumber"),
                    "%" + phoneNumber + "%"
            );
        }

        public static Specification<Farmer> hasStatus(FarmerStatus status) {
    return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(
                    root.get("status"),
                    status
            );
        }       
}