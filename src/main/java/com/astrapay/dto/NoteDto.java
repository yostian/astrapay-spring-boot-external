package com.astrapay.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoteDto {
    private Long id;

    @NotBlank(message = "Judul tidak boleh kosong")
    private String title;

    @NotBlank(message = "Isi Catatan tidak boleh kosong")
    private String content;
}