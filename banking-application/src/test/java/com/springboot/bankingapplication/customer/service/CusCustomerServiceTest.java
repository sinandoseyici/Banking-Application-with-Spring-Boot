package com.springboot.bankingapplication.customer.service;

import com.springboot.bankingapplication.customer.dto.CusCustomerDto;
import com.springboot.bankingapplication.customer.dto.CusCustomerSaveRequestDto;
import com.springboot.bankingapplication.customer.dto.CusCustomerUpdateRequestDto;
import com.springboot.bankingapplication.customer.entity.CusCustomer;
import com.springboot.bankingapplication.customer.entity.entityservice.CusCustomerEntityService;
import com.springboot.bankingapplication.customer.enums.EnumCusErrorMessage;
import com.springboot.bankingapplication.generic.enums.ErrorMessage;
import com.springboot.bankingapplication.generic.exceptions.BusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CusCustomerServiceTest {

    @Mock
    private CusCustomerEntityService cusCustomerEntityService;

    @InjectMocks
    private CusCustomerService cusCustomerService;

    @Test
    void shouldFindAll(){
        List<CusCustomerDto> cusCustomerDtoList = cusCustomerService.findAll();

        assertEquals(0, cusCustomerDtoList.size());
    }

    @Test
    void shouldFindAllWhenReturnCustomers(){

        CusCustomer cusCustomer = Mockito.mock(CusCustomer.class);
        List<CusCustomer> cusCustomerList = new ArrayList<>();
        cusCustomerList.add(cusCustomer);

        when(cusCustomerEntityService.findAll()).thenReturn(cusCustomerList);

        List<CusCustomerDto> cusCustomerDtoList = cusCustomerService.findAll();

        assertEquals(1, cusCustomerDtoList.size());
    }

    @Test
    void shouldFindAllWhenReturnNull(){

        when(cusCustomerEntityService.findAll()).thenReturn(null);

        List<CusCustomerDto> cusCustomerDtoList = cusCustomerService.findAll();

        assertNull(cusCustomerDtoList);
    }

    @Test
    void shouldFindById(){

        CusCustomer cusCustomer = mock(CusCustomer.class);
        when(cusCustomer.getId()).thenReturn(1L);

        when(cusCustomerEntityService.findById(anyLong())).thenReturn(Optional.of(cusCustomer));

        CusCustomerDto cusCustomerDto = cusCustomerService.findById(1L);

        assertEquals(1L, cusCustomerDto.getId());
    }

    @Test
    void shouldNotFindByIdWhenIdDoesNotExist(){

        when(cusCustomerEntityService.findById(anyLong())).thenReturn(Optional.empty());

        CusCustomerDto cusCustomerDto = cusCustomerService.findById(1L);

        assertNull(cusCustomerDto);
    }

    @Test
    void shouldFindByIdWithControl(){

        CusCustomer cusCustomer = mock(CusCustomer.class);
        when(cusCustomer.getId()).thenReturn(1L);

        when(cusCustomerEntityService.findById(anyLong())).thenReturn(Optional.of(cusCustomer));

        CusCustomerDto cusCustomerDto = cusCustomerService.findByIdWithControl(1L);
        assertEquals(1L, cusCustomerDto.getId());
    }

    @Test
    void shouldNotFindByIdWithControlWhenIdDoesNotExists(){

        when(cusCustomerEntityService.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> cusCustomerService.findByIdWithControl(1L));
    }

    @Test
    void shouldSave(){

        CusCustomerSaveRequestDto cusCustomerSaveRequestDto = mock(CusCustomerSaveRequestDto.class);
        CusCustomer cusCustomer = mock(CusCustomer.class);

        when(cusCustomer.getId()).thenReturn(1L);

        when(cusCustomerEntityService.save(any())).thenReturn(cusCustomer);

        CusCustomerDto cusCustomerDto = cusCustomerService.save(cusCustomerSaveRequestDto);

        assertEquals(1L, cusCustomerDto.getId());
    }

    @Test
    void shouldNotSaveWhenParameterIsNull(){

        BusinessException businessException = assertThrows(BusinessException.class, () -> cusCustomerService.save(null));

        assertEquals(ErrorMessage.PARAMETER_CANNOT_BE_NULL, businessException.getBaseErrorMessage());
    }

    @Test
    void shouldDelete(){
        doNothing().when(cusCustomerEntityService).delete(1L);

        cusCustomerService.delete(1L);

        verify(cusCustomerEntityService).delete(1L);
    }

    @Test
    void shouldUpdate(){

        CusCustomerUpdateRequestDto cusCustomerUpdateRequestDto = mock(CusCustomerUpdateRequestDto.class);

        CusCustomer cusCustomer = mock(CusCustomer.class);
        when(cusCustomer.getId()).thenReturn(1L);

        when(cusCustomerEntityService.isExist(anyLong())).thenReturn(Boolean.TRUE);
        when(cusCustomerEntityService.save(any())).thenReturn(cusCustomer);

        CusCustomerDto cusCustomerDto = cusCustomerService.update(cusCustomerUpdateRequestDto);

        assertEquals(1L, cusCustomer.getId());
    }

    @Test
    void shouldNotUpdateWhenCustomerDoesNotExists(){

        CusCustomerUpdateRequestDto cusCustomerUpdateRequestDto = mock(CusCustomerUpdateRequestDto.class);
        when(cusCustomerEntityService.isExist(anyLong())).thenReturn(Boolean.FALSE);

        BusinessException businessException = assertThrows(BusinessException.class, () -> cusCustomerService.update(cusCustomerUpdateRequestDto));
        assertEquals(EnumCusErrorMessage.CUSTOMER_DOES_NOT_EXIST, businessException.getBaseErrorMessage());

    }


}

