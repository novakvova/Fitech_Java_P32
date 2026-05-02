package org.example.mappers;

import org.example.dtos.song.SongItemDTO;
import org.example.entities.Song;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SongMapper {

    SongItemDTO toDto(Song song);
}
