package com.spring.www.usuarios.service;

import com.spring.www.usuarios.entity.Contacto;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ContactoService {

    Page<Contacto> paginar (int page, int size);

    Optional<Contacto> buscarContactoPorid(Long id);

    Contacto crear (Contacto contacto);

    Contacto editar (Contacto contacto);

    void eliminar (Long id);
}
