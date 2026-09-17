package com.famrut.farmer_management_api.service;

import com.famrut.farmer_management_api.dto.StateRequest;
import com.famrut.farmer_management_api.dto.StateResponse;
import com.famrut.farmer_management_api.entity.State;
import com.famrut.farmer_management_api.exception.StateNotFoundException;
import com.famrut.farmer_management_api.repository.StateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {

    private final StateRepository stateRepository;

    public StateService(
            StateRepository stateRepository) {

        this.stateRepository = stateRepository;
    }

    public StateResponse create(StateRequest request) {

        if (stateRepository.existsByStateCode(
                request.getStateCode())) {

            throw new IllegalArgumentException(
                    "State code already exists"
            );
        }

        State state = new State();

        state.setStateCode(
                request.getStateCode().trim()
        );

        state.setStateName(
                request.getStateName().trim()
        );

        state.setActive(true);

        State savedState =
                stateRepository.save(state);

        return toResponse(savedState);
    }

    public List<StateResponse> getActiveStates() {

        return stateRepository
                .findAllByActiveTrueOrderByStateNameAsc()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public StateResponse getById(Long id) {

        State state = stateRepository
                .findById(id)
                .orElseThrow(() ->
                        new StateNotFoundException(id)
                );

        return toResponse(state);
    }

    public StateResponse update(
            Long id,
            StateRequest request) {

        State state = stateRepository
                .findById(id)
                .orElseThrow(() ->
                        new StateNotFoundException(id)
                );

        if (!state.getStateCode()
                .equals(request.getStateCode())
                &&
                stateRepository.existsByStateCode(
                        request.getStateCode())) {

            throw new IllegalArgumentException(
                    "State code already exists"
            );
        }

        state.setStateCode(
                request.getStateCode().trim()
        );

        state.setStateName(
                request.getStateName().trim()
        );

        return toResponse(
                stateRepository.save(state)
        );
    }

    private StateResponse toResponse(State state) {

        return new StateResponse(
                state.getId(),
                state.getStateCode(),
                state.getStateName(),
                state.isActive()
        );
    }
}