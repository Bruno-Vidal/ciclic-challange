package br.com.ciclic.cervejeira.resource.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {

    private Integer status;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date timestamp;

    private String message;
}
