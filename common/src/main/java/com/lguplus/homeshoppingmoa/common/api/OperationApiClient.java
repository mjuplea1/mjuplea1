package com.lguplus.homeshoppingmoa.common.api;

import com.lguplus.homeshoppingmoa.common.model.dto.auth.PasswordUpdateDto;
import com.lguplus.homeshoppingmoa.common.model.dto.operation.AccountCreateDto;
import com.lguplus.homeshoppingmoa.common.model.dto.operation.AccountDto;
import com.lguplus.homeshoppingmoa.common.model.dto.operation.AccountModifyDto;
import com.lguplus.homeshoppingmoa.common.wrapper.CommonModel;
import com.lguplus.homeshoppingmoa.config.openfeign.AuthRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "operationApiClient", url = "${homeshoppingmoa.subdomain.uri-root.operation}", configuration = { AuthRequestInterceptor.class })
public interface OperationApiClient {

    @PutMapping("/v1/accounts/change-password")
    CommonModel changePassword(@RequestBody PasswordUpdateDto dto);

    @PostMapping(value = "/v1/accounts")
    CommonModel create(@RequestBody AccountCreateDto accountCreateDto);


    @GetMapping(value = "/v1/accounts/{id}")
    public ResponseEntity find(@PathVariable long id);

    @SuppressWarnings("rawtypes")
    @GetMapping(value = "/v1/accounts")
    CommonModel find(@RequestParam String searchCondition
            , @RequestParam String searchKeyword
            , @RequestParam String searchType
            , @RequestParam Integer page
            , @RequestParam Integer size
            , @RequestParam String sort
    );

    @PutMapping(value = "/v1/accounts/{id}")
    public <U extends UserDetails> ResponseEntity modify(@PathVariable long id, @RequestBody AccountModifyDto accountModifyDto);

    @DeleteMapping(value = "/{id}")
    public <U extends UserDetails> ResponseEntity remove(@PathVariable long id);

    @GetMapping(value = "/check/{username}")
    public <U extends UserDetails> ResponseEntity checkDuplicate(@PathVariable String username);
}
