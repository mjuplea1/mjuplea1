package com.lguplus.homeshoppingmoa.common.model.dto.broadcastproduct;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SubProductDto {

    private Long subProductId;

    private String productName;

    private long price;

    private String productImgUrl;

    private String shareUrl;

    private int month;

    private float discountRate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubProductDto)) return false;
        SubProductDto that = (SubProductDto) o;
        return price == that.price
                && month == that.month
                && Float.compare(that.discountRate, discountRate) == 0
                && Objects.equals(productName, that.productName)
                && Objects.equals(productImgUrl, that.productImgUrl)
                && Objects.equals(shareUrl, that.shareUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                price,
                month,
                discountRate,
                productName,
                productImgUrl,
                shareUrl);
    }
}
