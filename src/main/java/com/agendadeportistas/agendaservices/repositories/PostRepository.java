package com.agendadeportistas.agendaservices.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.agendadeportistas.agendaservices.entities.PostEntity;

@Repository
public interface PostRepository extends PagingAndSortingRepository<PostEntity, Long> {
    List<PostEntity> getByUsuarioIdUsuarioOrderByCreadoDesc(Long userId);

    void save(PostEntity nuevoPost);
}
