package com.spring.www.usuarios.controller;

import com.spring.www.usuarios.entity.Contacto;
import com.spring.www.usuarios.service.ContactoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class ContactoController {

    private final ContactoServiceImpl service;

    public ContactoController(ContactoServiceImpl service) {
        this.service = service;
    }

    @GetMapping({"listar", "/",""})
    public String inicio (@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "5") int size, ModelMap model){
        Page<Contacto> contactos = service.paginar(page, size);
        model.put("contactos", contactos);
        return "index";
    }

    //-------------------- crear -------------------------
    @GetMapping("/agregar")
    public String agregarForm (ModelMap model){
        model.put("contacto", new Contacto());
        return "agregar";
    }

    @PostMapping("/agregar")
    public String agregar(@Valid @ModelAttribute("contactos") Contacto contacto, BindingResult result){
        if (result.hasErrors()){
            return "agregar";
        }
        service.crear(contacto);
        return "redirect:/";
    }
    //----------------------------------------------------------


    //------------------------ editar ---------------------------
    @GetMapping("/editar/{id}")
    public String editarForm (@PathVariable Long id, ModelMap model){
        Contacto contacto = service.buscarContactoPorid(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.put("contactos", contacto);
        return "editar";
    }

    @PostMapping("/editar")
    public String editar (@Valid @ModelAttribute("contacto") Contacto contacto, BindingResult result){
        if (result.hasErrors()){
            return "editar";
        }
        service.editar(contacto);
        return "redirect:/";
    }


    //-------------------------- eliminar ------------------------------------
    @GetMapping("/eliminar/{id}")
    public String eliminar (@PathVariable Long id){
        service.eliminar(id);
        return "redirect:/";
    }

}
