package com.lguplus.homeshoppingmoa.common.api;

import com.lguplus.homeshoppingmoa.common.model.dto.broadcastproduct.*;
import com.lguplus.homeshoppingmoa.common.wrapper.CommonModel;
import com.lguplus.homeshoppingmoa.config.openfeign.AuthRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(name = "broadcastProductApiClient", url = "${homeshoppingmoa.subdomain.uri-root.broadcastproduct}", configuration = {AuthRequestInterceptor.class})
public interface BroadcastProductApiClient {

    String MAINPRODUCT = "/v1/mainproduct";
    String SHOPPING_CHANNEL = "/v1/shoppingchannel";
    String CATEGORY = "/v1/category";

    // MainProduct API

    @GetMapping(MAINPRODUCT + "/page")
    CommonModel<Page<MainProductDto>> findMainProduct(
            @RequestParam(required = false) String exposeStartDt,
            @RequestParam(required = false) String exposeEndDt,
            @RequestParam(required = false) MainProductSearchType type,
            @RequestParam(required = false) String value,
            @RequestParam(required = false) String min,
            @RequestParam(required = false) String max,
            @RequestParam(required = false) String depth1,
            @RequestParam(required = false) String depth2,
            @RequestParam(required = false) String depth3,
            @RequestParam(required = false) String depth4,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String sort);

    @GetMapping(MAINPRODUCT + "/{date}")
    CommonModel<List<MainProductDto>> findAllMainProductByDate(@PathVariable String date);

    @GetMapping(MAINPRODUCT)
    CommonModel<List<MainProductDto>> findByMainProductIdList(@RequestParam List<Long> mainProductIdList);


    // ShoppingChannel API

    @PostMapping(SHOPPING_CHANNEL)
    CommonModel<ShoppingChannelDto> createAndAttachFile(@RequestPart("shoppingChannelCreate") String shoppingChannelCreateDto
            , @RequestPart(value = "attachFile", required = false) MultipartFile attachFile);

    @GetMapping(SHOPPING_CHANNEL)
    CommonModel<List<ShoppingChannelDto>> findShoppingChannelByChannelIdList(@RequestParam List<Long> channelIdList);

    @GetMapping(SHOPPING_CHANNEL + "/page")
    CommonModel<Page<ShoppingChannelDto>> findShoppingChannel(
            @RequestParam(required = false) ShoppingChannelSearchType type,
            @RequestParam(required = false) String condition,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String sort);

    @PutMapping(SHOPPING_CHANNEL + "/{channelId}")
    CommonModel<ShoppingChannelDto> modifyShoppingChannel(@PathVariable long channelId, @RequestBody ShoppingChannelModifyDto dto);

    @DeleteMapping(SHOPPING_CHANNEL + "/{channelId}")
    CommonModel<Boolean> deleteShoppingChannel(@PathVariable long channelId);


    // Category API

    @GetMapping(CATEGORY)
    CommonModel<List<TotalCategoryRow>> findCategory(@RequestParam(required = false) Integer level,
                                                     @RequestParam(required = false) Integer parentCategoryId);

    @GetMapping(CATEGORY + "/all")
    CommonModel<HierarchicalCategoryResponse> findAllCategory();
}
