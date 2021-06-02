package com.lguplus.homeshoppingmoa.common.model.dto.broadcastproduct;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardDto {
    @Schema(name = "cardId", description = "카드 ID")
    private Long cardId;

    @Schema(name = "name", description = "카드 이름")
    private String name;

    @Schema(name = "discount_rate", description = "할인율")
    private float discount_rate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardDto)) return false;
        CardDto that = (CardDto) o;
        return Float.compare(that.discount_rate, discount_rate) == 0 && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, discount_rate);
    }
}
