package com.lguplus.homeshoppingmoa.common.model.dto.broadcastproduct;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class HostDto {

    private Long hostId;

    private String hostName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HostDto)) return false;
        HostDto that = (HostDto) o;
        return Objects.equals(hostName, that.hostName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hostName);
    }

    @Builder
    public HostDto(Long hostId, String hostName) {
        this.hostId = hostId;
        this.hostName = hostName;
    }
}
