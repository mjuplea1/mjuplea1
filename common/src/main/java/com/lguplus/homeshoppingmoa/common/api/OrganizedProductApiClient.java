package com.lguplus.homeshoppingmoa.common.api;

import com.lguplus.homeshoppingmoa.common.model.dto.organizedproduct.*;
import com.lguplus.homeshoppingmoa.common.wrapper.CommonModel;
import com.lguplus.homeshoppingmoa.config.openfeign.AuthRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "organizedProductApiClient", url = "${homeshoppingmoa.subdomain.uri-root.organizedproduct}", configuration = { AuthRequestInterceptor.class })
public interface OrganizedProductApiClient {

    @PostMapping(value = "/v1/containers")
    CommonModel<ContainerDto> createContainer(@RequestBody ContainerCreateDto containerCreateDto);

    @PutMapping(value = "/v1/containers/{id}")
    CommonModel<ContainerDto> modifyContainer(@PathVariable long id, @RequestBody ContainerModifyDto containerModifyDto);

    @DeleteMapping(value = "/v1/containers/{id}")
    CommonModel<Boolean> removeContainer(@PathVariable long id);

    @GetMapping(value = "/v1/containers/{id}")
    CommonModel<ContainerDto> findContainer(@PathVariable long id);

    @SuppressWarnings("rawtypes")
    @GetMapping(value = "/v1/containers")
    CommonModel findContainers(@RequestParam String searchCondition
            , @RequestParam String searchKeyword
            , @RequestParam String searchStatus
            , @RequestParam Integer page
            , @RequestParam Integer size
            , @RequestParam String sort
    );

    @SuppressWarnings("rawtypes")
    @GetMapping(value = "/v1/containers/manage-app-menu")
    CommonModel findContainers(@RequestParam String searchKeyword
            , @RequestParam String searchType
            , @RequestParam Integer page
            , @RequestParam Integer size
            , @RequestParam String sort
    );

    @PostMapping(value = "/v1/app-menus")
    CommonModel<AppMenuDto> createAppMenu(@RequestBody AppMenuCreateDto appMenuCreateDto);

    @PutMapping(value = "/v1/app-menus/{id}")
    CommonModel<AppMenuDto> modifyAppMenu(@PathVariable long id, @RequestBody AppMenuModifyDto appMenuModifyDto);

    @DeleteMapping(value = "/v1/app-menus/{id}")
    CommonModel<Boolean> removeAppMenu(@PathVariable long id);

    @GetMapping(value = "/v1/app-menus/{id}")
    CommonModel<AppMenuDto> findAppMenu(@PathVariable long id);

    @SuppressWarnings("rawtypes")
    @GetMapping(value = "/v1/app-menus")
    CommonModel findAppMenu(@RequestParam String searchCondition
            , @RequestParam String searchKeyword
            , @RequestParam String searchStatus
            , @RequestParam Integer page
            , @RequestParam Integer size
            , @RequestParam String sort
    );

    @PutMapping(value = "/v1/app-menus/change-sort-order")
    CommonModel<Boolean> changeAppMenuSortOrder(@RequestBody List<AppMenuSortOrderDto> appMenuSortOrderList);

}
