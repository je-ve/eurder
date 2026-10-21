package org.switchfully.eurder.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.switchfully.eurder.api.dto.CreateCustomerDto;
import org.switchfully.eurder.api.dto.UserDto;
import org.switchfully.eurder.api.mapper.UserMapper;
import org.switchfully.eurder.domain.User;
import org.switchfully.eurder.domain.UserRepository;
import org.switchfully.eurder.exception.CustomerNotFoundException;
import org.switchfully.eurder.exception.EmailAlreadyExistsException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    void createCustomer_happyPath() {
        // GIVEN
        String email = "john@doe.com";
        CreateCustomerDto createDto = mock(CreateCustomerDto.class);
        when(createDto.getEmail()).thenReturn(email);

        User customer = mock(User.class);
        UserDto expectedDto = mock(UserDto.class);

        when(userRepository.existByEmail(email)).thenReturn(false);
        when(userMapper.mapToUser(createDto)).thenReturn(customer);
        when(userRepository.save(any(User.class))).thenReturn(customer);
        when(userMapper.mapToUserDto(customer)).thenReturn(expectedDto);

        // WHEN
        UserDto result = userService.createCustomer(createDto);

        // THEN
        assertThat(result).isEqualTo(expectedDto);
        verify(userRepository, times(1)).save(customer); // Omdat save twee keer in je code staat
    }

    @Test
    void createCustomer_emailAlreadyExists_throwsException() {
        // GIVEN
        String email = "duplicate@test.com";
        CreateCustomerDto createDto = mock(CreateCustomerDto.class);
        when(createDto.getEmail()).thenReturn(email);

        when(userRepository.existByEmail(email)).thenReturn(true);

        // WHEN & THEN
        assertThatThrownBy(() -> userService.createCustomer(createDto))
                .isInstanceOf(EmailAlreadyExistsException.class);

        verifyNoInteractions(userMapper);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void getAllCustomers_happyPath() {
        // GIVEN
        User customer1 = mock(User.class);
        User customer2 = mock(User.class);
        Collection<User> customersList = List.of(customer1, customer2);

        UserDto dto1 = mock(UserDto.class);
        UserDto dto2 = mock(UserDto.class);
        Collection<UserDto> expectedDtos = List.of(dto1, dto2);

        when(userRepository.getAllCustomers()).thenReturn(customersList);
        when(userMapper.mapToUserDto(customersList)).thenReturn(expectedDtos);

        // WHEN
        Collection<UserDto> result = userService.getAllCustomers();

        // THEN
        assertThat(result).containsExactlyInAnyOrder(dto1, dto2);
    }

    @Test
    void getCustomerById_happyPath() {
        // GIVEN
        String customerId = "cust-123";
        User customer = mock(User.class);
        UserDto expectedDto = mock(UserDto.class);

        when(userRepository.findCustomerById(customerId)).thenReturn(Optional.of(customer));
        when(userMapper.mapToUserDto(customer)).thenReturn(expectedDto);

        // WHEN
        UserDto result = userService.getCustomerById(customerId);

        // THEN
        assertThat(result).isEqualTo(expectedDto);
    }

    @Test
    void getCustomerById_customerNotFound_throwsException() {
        // GIVEN
        String unknownId = "unknown-id";
        when(userRepository.findCustomerById(unknownId)).thenReturn(Optional.empty());

        // WHEN & THEN
        assertThatThrownBy(() -> userService.getCustomerById(unknownId))
                .isInstanceOf(CustomerNotFoundException.class);

        verifyNoInteractions(userMapper);
    }
}